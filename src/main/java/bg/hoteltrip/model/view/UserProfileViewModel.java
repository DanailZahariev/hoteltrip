package bg.hoteltrip.model.view;

public class UserProfileViewModel {

    private String firstName;
    private String lastName;
    private String email;

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
}
