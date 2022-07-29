package bg.hoteltrip.model.entity;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;

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

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public RoomEntity setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public RoomEntity setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }
}
