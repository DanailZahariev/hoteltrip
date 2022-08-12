package bg.hoteltrip.model.binding;

import bg.hoteltrip.util.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class UserProfileUpdateBindingModel {
    @NotBlank(message = "Please, enter your first name!")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Please, enter your last name!")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;
    @Email
    @UniqueEmail(message = "Email already exist, please enter a different email.")
    @NotBlank(message = "Please, enter your email!")
    private String email;


    public UserProfileUpdateBindingModel() {
    }

    public String getFirstName() {
        return firstName;
    }

    public UserProfileUpdateBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserProfileUpdateBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UserProfileUpdateBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }
}
