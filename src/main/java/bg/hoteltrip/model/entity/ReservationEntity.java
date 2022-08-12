package bg.hoteltrip.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @ManyToOne
    private UserEntity user;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    @ManyToOne
    private HotelEntity hotel;

    @ManyToOne
    private RoomEntity rooms;

    public ReservationEntity() {
    }

    public UserEntity getUser() {
        return user;
    }

    public ReservationEntity setUser(UserEntity user) {
        this.user = user;
        return this;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public ReservationEntity setStartDate(LocalDate startDate) {
        this.startDate = startDate;
        return this;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public ReservationEntity setEndDate(LocalDate endDate) {
        this.endDate = endDate;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public ReservationEntity setActive(boolean active) {
        isActive = active;
        return this;
    }


    public HotelEntity getHotel() {
        return hotel;
    }

    public ReservationEntity setHotel(HotelEntity hotel) {
        this.hotel = hotel;
        return this;
    }

    public RoomEntity getRooms() {
        return rooms;
    }

    public ReservationEntity setRooms(RoomEntity rooms) {
        this.rooms = rooms;
        return this;
    }
}

