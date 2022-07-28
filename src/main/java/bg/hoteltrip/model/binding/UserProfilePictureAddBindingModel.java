package bg.hoteltrip.model.binding;

import bg.hoteltrip.util.validation.ValidPicture;
import org.springframework.web.multipart.MultipartFile;

public class UserProfilePictureAddBindingModel {

    @ValidPicture
    private MultipartFile profilePictureUrl;


    public UserProfilePictureAddBindingModel() {
    }

    public MultipartFile getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public UserProfilePictureAddBindingModel setProfilePictureUrl(MultipartFile profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }
}
