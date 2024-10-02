package ca.zhoozhoo.springcloud.guests.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import ca.zhoozhoo.springcloud.guests.model.Guest;

@Repository
public interface GuestRepository extends ReactiveCrudRepository<Guest, Long> {
}
