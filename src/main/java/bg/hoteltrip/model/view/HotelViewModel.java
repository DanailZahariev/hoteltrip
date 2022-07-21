package bg.hoteltrip.model.view;

public class HotelViewModel {

    private String hotelName;
    private String description;

    public String getHotelName() {
        return hotelName;
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
}
