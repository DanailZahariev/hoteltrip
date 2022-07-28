package bg.hoteltrip.model.binding;

import org.springframework.web.multipart.MultipartFile;

public class UserProfilePictureAddBindingModel {

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
