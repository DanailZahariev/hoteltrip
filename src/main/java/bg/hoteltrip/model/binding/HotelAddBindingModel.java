package bg.hoteltrip.model.binding;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

public class HotelAddBindingModel {


    @NotBlank(message = "Please add a hotel name.")
    @Size(min = 3, message = "Hotel name must have at least 3 characters.")
    private String hotelName;

    @NotBlank(message = "Please add a town name.")
    @Size(min = 3, message = "Town name must have at least 3 characters")
    private String townName;

    @NotBlank(message = "Please add a description.")
    @Size(min = 30, message = "Hotel description must have at least 30 characters")
    private String description;

    @Min(value = 1,message = "Add at least one room")
    @NotNull
    private Integer doubleRooms;
    @Min(value = 1,message = "Add at least one room")
    @NotNull
    private Integer studioRooms;
    @Min(value = 1,message = "Add at least one room")
    @NotNull
    private Integer apartments;

    private List<MultipartFile> hotelPictures;

    public HotelAddBindingModel() {
    }

    public String getHotelName() {
        return hotelName;
    }

    public HotelAddBindingModel setHotelName(String hotelName) {
        this.hotelName = hotelName;
        return this;
    }

    public String getTownName() {
        return townName;
    }

    public HotelAddBindingModel setTownName(String townName) {
        this.townName = townName;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public HotelAddBindingModel setDescription(String description) {
        this.description = description;
        return this;
    }

    public Integer getDoubleRooms() {
        return doubleRooms;
    }

    public HotelAddBindingModel setDoubleRooms(Integer doubleRooms) {
        this.doubleRooms = doubleRooms;
        return this;
    }

    public Integer getStudioRooms() {
        return studioRooms;
    }

    public HotelAddBindingModel setStudioRooms(Integer studioRooms) {
        this.studioRooms = studioRooms;
        return this;
    }

    public Integer getApartments() {
        return apartments;
    }

    public HotelAddBindingModel setApartments(Integer apartments) {
        this.apartments = apartments;
        return this;
    }

    public List<MultipartFile> getHotelPictures() {
        return hotelPictures;
    }

    public HotelAddBindingModel setHotelPictures(List<MultipartFile> hotelPictures) {
        this.hotelPictures = hotelPictures;
        return this;
    }
}
