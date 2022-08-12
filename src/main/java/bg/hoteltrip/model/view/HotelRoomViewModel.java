package bg.hoteltrip.model.view;

import bg.hoteltrip.model.entity.enums.RoomTypeEnum;

import java.math.BigDecimal;
import java.util.List;

public class HotelRoomViewModel {

    private Long id;
    private RoomTypeEnum roomType;

    private BigDecimal price;

    private List<HotelReservationViewModel> reservation;

    public HotelRoomViewModel() {
    }

    public Long getId() {
        return id;
    }

    public HotelRoomViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public RoomTypeEnum getRoomType() {
        return roomType;
    }

    public HotelRoomViewModel setRoomType(RoomTypeEnum roomType) {
        this.roomType = roomType;
        return this;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public HotelRoomViewModel setPrice(BigDecimal price) {
        this.price = price;
        return this;
    }

    public List<HotelReservationViewModel> getReservation() {
        return reservation;
    }

    public HotelRoomViewModel setReservation(List<HotelReservationViewModel> reservation) {
        this.reservation = reservation;
        return this;
    }
}
