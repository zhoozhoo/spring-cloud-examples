package ca.zhoozhoo.springcloud.roomreservation.model;

import java.time.LocalDate;

import lombok.Data;

@Data
public class RoomReservation {

    private long roomId;

    private long guestId;

    private String roomName;

    private String roomNumber;

    private String firstName;

    private String lastName;

    private LocalDate date;
}
