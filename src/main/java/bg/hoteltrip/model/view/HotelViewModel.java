package bg.hoteltrip.model.view;


import java.util.List;

public class HotelViewModel {

    private Long id;
    private String hotelName;
    private String description;

    private List<PictureViewModel> hotelPictures;

    private List<HotelRoomViewModel> rooms;

    public String getHotelName() {
        return hotelName;
    }

    public HotelViewModel() {
    }

    public Long getId() {
        return id;
    }

    public HotelViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public HotelViewModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HotelViewModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public List<PictureViewModel> getHotelPictures() {
        return hotelPictures;
    }

    public HotelViewModel setHotelPictures(List<PictureViewModel> hotelPictures) {
        this.hotelPictures = hotelPictures;
        return this;
    }

    public List<HotelRoomViewModel> getRooms() {
        return rooms;
    }

    public HotelViewModel setRooms(List<HotelRoomViewModel> rooms) {
        this.rooms = rooms;
        return this;
    }
}
