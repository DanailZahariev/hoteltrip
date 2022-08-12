package bg.hoteltrip.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity extends BaseEntity {

    @Column(name = "hotel_name")
    private String hotelName;

    @ManyToOne(targetEntity = TownEntity.class, optional = false)
    private TownEntity town;

    @Lob
    @Column(nullable = false)
    private String description;

    @OneToMany(targetEntity = RoomEntity.class)
//    @JoinTable(name = "hotels_rooms", joinColumns = @JoinColumn(name = "hotel_id"),
//            inverseJoinColumns = @JoinColumn(name = "room_id"))
    private List<RoomEntity> rooms;

    @OneToMany
    @JoinTable(name = "hotels_pictures", joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "picture_id"))
    private List<PictureEntity> hotelPictures;

    @OneToMany(targetEntity = ReservationEntity.class)
    private List<ReservationEntity> reservation;

    public HotelEntity() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public HotelEntity setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public TownEntity getTown() {
        return town;
    }

    public HotelEntity setTown(TownEntity town) {
        this.town = town;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HotelEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<RoomEntity> getRooms() {
        return rooms;
    }

    public HotelEntity setRooms(List<RoomEntity> rooms) {
        this.rooms = rooms;
        return this;
    }

    public List<PictureEntity> getHotelPictures() {
        return hotelPictures;
    }

    public HotelEntity setHotelPictures(List<PictureEntity> hotelPictures) {
        this.hotelPictures = hotelPictures;
        return this;
    }

    public List<ReservationEntity> getReservation() {
        return reservation;
    }

    public HotelEntity setReservation(List<ReservationEntity> reservation) {
        this.reservation = reservation;
        return this;
    }
}
