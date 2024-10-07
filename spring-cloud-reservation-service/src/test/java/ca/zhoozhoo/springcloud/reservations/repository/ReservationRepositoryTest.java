package ca.zhoozhoo.springcloud.reservations.repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ca.zhoozhoo.springcloud.reservations.model.Reservation;
import reactor.test.StepVerifier;

@SpringBootTest(properties = { "eureka.client.enabled=false", "spring.cloud.config.enabled=false" })
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class ReservationRepositoryTest {

    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @Order(1)
    public void testDeleteAll() {
        insertReservations();

        reservationRepository.deleteAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void testFindAll() {
        insertReservations();

        reservationRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(2)
                .verifyComplete();
    }

    public void insertReservations() {
        List<Reservation> reservations = Arrays.asList(
                new Reservation(null, 1L, 1L, LocalDate.now()),
                new Reservation(null, 2L, 2L, LocalDate.now()));

        reservationRepository.saveAll(reservations).subscribe();
    }
}
