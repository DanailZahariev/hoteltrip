package bg.hoteltrip.model.entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity extends BaseEntity {

    @Column(name = "hotel_name")
    private String hotelName;

    @ManyToOne(targetEntity = TownEntity.class, optional = false)
    private TownEntity town;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToMany
    private List<RoomEntity> rooms;

//    @Column(name = "total_rooms")
//    private Integer totalRooms;

    @ManyToMany
    private List<PictureEntity> hotelPictures;

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

//    public Integer getTotalRooms() {
//        return totalRooms;
//    }
//
//    public HotelEntity setTotalRooms(Integer totalRooms) {
//        this.totalRooms = totalRooms;
//        return this;
//    }

    public List<PictureEntity> getHotelPictures() {
        return hotelPictures;
    }

    public HotelEntity setHotelPictures(List<PictureEntity> hotelPictures) {
        this.hotelPictures = hotelPictures;
        return this;
    }
}
