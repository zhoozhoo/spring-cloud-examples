package ca.zhoozhoo.springcloud.reservations.repository;

import java.time.LocalDate;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.springcloud.reservations.model.Reservation;
import reactor.core.publisher.Flux;

@Repository
public interface ReservationRepository extends ReactiveCrudRepository<Reservation, Long> {

    public Flux<Reservation> findAllByDate(LocalDate date);
}
