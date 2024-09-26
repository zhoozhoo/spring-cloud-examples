package ca.zhoozhoo.springcloud.reservations.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.util.annotation.NonNull;

@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    @Column("RESERVATION_ID")
    private Long id;

    @NonNull
    private Long roomId;

    @NonNull
    private Long guestId;

    @Column("RES_DATE")
    private LocalDate date;
}
