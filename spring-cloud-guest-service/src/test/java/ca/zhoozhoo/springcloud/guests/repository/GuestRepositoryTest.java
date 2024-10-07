package ca.zhoozhoo.springcloud.guests.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ca.zhoozhoo.springcloud.guests.model.Guest;
import reactor.test.StepVerifier;

@SpringBootTest(properties = { "eureka.client.enabled=false", "spring.cloud.config.enabled=false" })
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class GuestRepositoryTest {

    @Autowired
    private GuestRepository guestRepository;

    @Test
    @Order(1)
    public void testDeleteAll() {
        insertGuests();

        guestRepository.deleteAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void testFindAll() {
        insertGuests();

        guestRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }

    public void insertGuests() {
        List<Guest> guests = Arrays.asList(
                new Guest(null, "Adams", "Roy", "radams1v@xinhuanet.com", "United States", "2872 Marquette Street",
                        "NY", "1-(235)314-9823"),
                new Guest(null, "Adams", "Martin", "madams2b@msu.edu", "China", "4 Mandrake Plaza", "",
                        "9-(401)660-9813"),
                new Guest(null, "Alvarez", "Roger", "ralvarezk@blogs.com", "United States", "3 Green Plaza", "FL",
                        "6-(980)036-6105"),
                new Guest(null, "Alvarez", "Anne", "aalvarez1y@mlb.com", "United States", "6 Glendale Parkway", "FL",
                        "7-(967)349-7237"));

        guestRepository.saveAll(guests).subscribe();
    }
}
