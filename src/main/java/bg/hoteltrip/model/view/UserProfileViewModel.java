package bg.hoteltrip.model.view;

public class UserProfileViewModel {

    private Long id;
    private String firstName;
    private String lastName;
    private String profilePictureUrl;
    private String email;

    private PictureViewModel picture;

    public UserProfileViewModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public PictureViewModel getPicture() {
        return picture;
    }

    public UserProfileViewModel setPicture(PictureViewModel picture) {
        this.picture = picture;
        return this;
    }

    public Long getId() {
        return id;
    }

    public UserProfileViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getProfilePictureUrl() {
        return profilePictureUrl;
    }

    public UserProfileViewModel setProfilePictureUrl(String profilePictureUrl) {
        this.profilePictureUrl = profilePictureUrl;
        return this;
    }
}
