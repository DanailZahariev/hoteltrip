package bg.hoteltrip.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity extends BaseEntity {

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @ManyToOne(optional = false)
    private TownEntity town;

    @Column(nullable = false)
    private String description;

    @ManyToMany
    private List<RoomEntity> rooms;

    @Column(name = "total_rooms", nullable = false)
    private Integer totalRooms;

    @ManyToOne
    private PictureEntity hotelPictures;

    public HotelEntity() {
        this.rooms = new ArrayList<>();
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

    public Integer getTotalRooms() {
        return totalRooms;
    }

    public HotelEntity setTotalRooms(Integer totalRooms) {
        this.totalRooms = totalRooms;
        return this;
    }

    public PictureEntity getHotelPictures() {
        return hotelPictures;
    }

    public HotelEntity setHotelPictures(PictureEntity hotelPictures) {
        this.hotelPictures = hotelPictures;
        return this;
    }
}
