package bg.hoteltrip.model.entity.enums;

public enum RoomTypeEnum {

    STUDIO("Studio"), DOUBLE_ROOM("Double room"), APARTMENT("Apartment");

    private final String name;

    RoomTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
