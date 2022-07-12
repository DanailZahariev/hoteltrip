package bg.hoteltrip.model.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "hotels")
public class HotelEntity extends BaseEntity {

    @Column(name = "hotel_name", nullable = false)
    private String hotelName;

    @ManyToOne(optional = false)
    private TownEntity town;

    @ManyToOne(optional = false)
    private RoomEntity rooms;


    @ManyToOne
    private PictureEntity hotelPictures;

    public HotelEntity() {
    }
}
