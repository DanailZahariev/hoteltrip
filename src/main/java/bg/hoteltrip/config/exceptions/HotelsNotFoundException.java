package bg.hoteltrip.config.exceptions;

public class HotelsNotFoundException extends RuntimeException {

    private final Long objectId;

    public HotelsNotFoundException(Long objectId) {
        super("Hotel with town id " + objectId + " not found!");
        this.objectId = objectId;
    }

    public Long getObjectId() {
        return objectId;
    }
}
