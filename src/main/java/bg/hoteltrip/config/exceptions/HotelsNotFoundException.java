package bg.hoteltrip.config.exceptions;

public class HotelsNotFoundException extends RuntimeException {

    private final String hotelName;

    public HotelsNotFoundException(String hotelName) {
        super("Hotel with town id " + hotelName + " not found!");
        this.hotelName = hotelName;
    }

    public String getHotelName() {
        return hotelName;
    }
}
