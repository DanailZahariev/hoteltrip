package bg.hoteltrip.model.entity;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {


    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @Column(nullable = false)
    private BigDecimal price;

    public RoomEntity() {
    }

}
