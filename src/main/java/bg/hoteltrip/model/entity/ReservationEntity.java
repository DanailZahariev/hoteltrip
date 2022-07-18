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

    @ManyToOne
    private HotelEntity hotelReservation;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(name = "is_active", nullable = false)
    private boolean isActive;

    public ReservationEntity() {
    }
}

