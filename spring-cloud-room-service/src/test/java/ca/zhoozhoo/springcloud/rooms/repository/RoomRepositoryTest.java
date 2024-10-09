package ca.zhoozhoo.springcloud.rooms.repository;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ca.zhoozhoo.springcloud.rooms.model.Room;
import reactor.test.StepVerifier;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(OrderAnnotation.class)
public class RoomRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;

    @Test
    @Order(1)
    public void testDeleteAll() {
        insertRooms();

        roomRepository.deleteAll()
                .as(StepVerifier::create)
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    @Order(2)
    public void testFindAll() {
        insertRooms();

        roomRepository.findAll()
                .as(StepVerifier::create)
                .expectNextCount(4)
                .verifyComplete();
    }


    public void insertRooms() {
        List<Room> rooms = Arrays.asList(new Room(null, "Room 1", "101", "King Bed"),
                new Room(null, "Room 2", "102", "Queen Bed"),
                new Room(null, "Room 3", "103", "Single Bed"),
                new Room(null, "Room 4", "104", "Single Bed"));

        roomRepository.saveAll(rooms).subscribe();
    }
}