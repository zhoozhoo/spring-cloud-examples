package ca.zhoozhoo.springcloud.roomreservation.controller;

import java.time.LocalDate;
import java.util.Map;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.oauth2.server.resource.web.reactive.function.client.ServerBearerExchangeFilterFunction;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import ca.zhoozhoo.springcloud.roomreservation.model.Guest;
import ca.zhoozhoo.springcloud.roomreservation.model.Reservation;
import ca.zhoozhoo.springcloud.roomreservation.model.Room;
import ca.zhoozhoo.springcloud.roomreservation.model.RoomReservation;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping("/room-reservations")
public class RoomReservationController {

    private WebClient webClient;

    public RoomReservationController(
            ReactorLoadBalancerExchangeFilterFunction reactorLoadBalancerExchangeFilterFunction) {
        webClient = WebClient.builder().filter(new ServerBearerExchangeFilterFunction())
                .filter(reactorLoadBalancerExchangeFilterFunction).build();
    }

    @GetMapping
    public Flux<RoomReservation> getRoomReservations(
            @RequestParam(name = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            @RequestHeader Map<String, String> headers) {
        var roomReservations = webClient.get().uri("http://reservation-service/reservations", date).retrieve()
                .bodyToFlux(Reservation.class).publishOn(Schedulers.elastic()).map(reservation -> {
                    return webClient.get().uri("http://room-service/rooms/{id}", reservation.getRoomId())
                            .headers(httpHeaders -> httpHeaders.setAll(headers)).retrieve().bodyToMono(Room.class)
                            .zipWith(webClient.get().uri("http://guest-service/guests/{id}", reservation.getGuestId())
                                    .headers(httpHeaders -> httpHeaders.setAll(headers)).retrieve()
                                    .bodyToMono(Guest.class), (room, guest) -> {
                                        var roomReservation = new RoomReservation();
                                        roomReservation.setDate(date);
                                        roomReservation.setRoomId(room.getId());
                                        roomReservation.setRoomName(room.getName());
                                        roomReservation.setRoomNumber(room.getRoomNumber());
                                        roomReservation.setGuestId(guest.getId());
                                        roomReservation.setFirstName(guest.getFirstName());
                                        roomReservation.setLastName(guest.getLastName());

                                        return roomReservation;
                                    })
                            .block();
                });

        return roomReservations;
    }
}
