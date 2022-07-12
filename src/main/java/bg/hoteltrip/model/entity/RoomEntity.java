package bg.hoteltrip.model.entity;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {


    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @ManyToOne(optional = false)
    private UserEntity guestName;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_reserved", nullable = false)
    private boolean isReserved;


    public RoomEntity() {
    }


}
