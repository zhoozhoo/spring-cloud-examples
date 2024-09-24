package ca.zhoozhoo.springcloud.rooms.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.springcloud.rooms.model.Room;

@Repository
public interface RoomRepository extends ReactiveCrudRepository<Room, Long> {
}
