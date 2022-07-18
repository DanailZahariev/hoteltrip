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

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToMany
    private List<RoomEntity> rooms;

    @Column(name = "total_rooms", nullable = false)
    private Integer totalRooms;

    @ManyToOne
    private PictureEntity hotelPictures;

    public HotelEntity() {
        this.totalRooms = 50;
        this.rooms = new ArrayList<>(totalRooms);
    }
}
