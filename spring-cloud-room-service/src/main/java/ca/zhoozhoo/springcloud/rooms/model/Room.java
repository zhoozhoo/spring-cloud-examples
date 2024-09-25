package ca.zhoozhoo.springcloud.rooms.model;

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
public class Room {

    @Id
    @Column("ROOM_ID")
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String roomNumber;

    @NonNull
    private String bedInfo;
}
