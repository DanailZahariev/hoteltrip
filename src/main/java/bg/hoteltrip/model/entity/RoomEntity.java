package bg.hoteltrip.model.entity;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "rooms")
public class RoomEntity extends BaseEntity {


    @Column(name = "room_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private RoomTypeEnum roomType;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(cascade = CascadeType.ALL)
    private HotelEntity hotel;

    @ManyToMany
    private List<ReservationEntity> reservation;

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

    public HotelEntity getHotel() {
        return hotel;
    }

    public RoomEntity setHotel(HotelEntity hotel) {
        this.hotel = hotel;
        return this;
    }

    public List<ReservationEntity> getReservation() {
        return reservation;
    }

    public RoomEntity setReservation(List<ReservationEntity> reservation) {
        this.reservation = reservation;
        return this;
    }
}
