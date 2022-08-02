package bg.hoteltrip.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table(name = "reservation")
public class ReservationEntity extends BaseEntity {

    @ManyToOne
    private UserEntity guestReservation;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public ReservationEntity() {
    }

    public UserEntity getGuestReservation() {
        return guestReservation;
    }

    public ReservationEntity setGuestReservation(UserEntity guestReservation) {
        this.guestReservation = guestReservation;
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
}

