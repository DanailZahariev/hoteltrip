package bg.hoteltrip.model.view;


import java.time.LocalDate;

public class HotelReservationViewModel {

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean isActive;

    private RoomViewByID rooms;

    private HotelViewByID hotel;


    public HotelReservationViewModel() {
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public HotelReservationViewModel setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public HotelReservationViewModel setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public HotelReservationViewModel setActive(boolean active) {
        isActive = active;
        return this;
    }

    public RoomViewByID getRooms() {
        return rooms;
    }

    public HotelReservationViewModel setRooms(RoomViewByID rooms) {
        this.rooms = rooms;
        return this;
    }

    public HotelViewByID getHotel() {
        return hotel;
    }

    public HotelReservationViewModel setHotel(HotelViewByID hotel) {
        this.hotel = hotel;
        return this;
    }
}
