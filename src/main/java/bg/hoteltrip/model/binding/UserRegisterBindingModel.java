package bg.hoteltrip.model.binding;

import bg.hoteltrip.util.validation.FieldMatch;
import bg.hoteltrip.util.validation.UniqueEmail;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@FieldMatch(
        first = "password",
        second = "confirmPassword",
        message = "Passwords do not match.")
public class UserRegisterBindingModel {


    @Email
    @UniqueEmail(message = "Email already exist, please enter a different email.")
    @NotBlank(message = "Please, enter your email!")
    private String email;

    @NotBlank(message = "Please, enter your first name!")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 20 characters!")
    private String firstName;

    @NotBlank(message = "Please, enter your last name!")
    @Size(min = 2, max = 20, message = "Last name must be between 2 and 20 characters!")
    private String lastName;

    @NotBlank(message = "Please, enter password!")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters!")
    private String password;

    @NotBlank(message = "Please, enter password!")
    @Size(min = 6, max = 25, message = "Password must be between 6 and 25 characters!")
    private String confirmPassword;

    public UserRegisterBindingModel() {
    }

    public String getEmail() {
        return email;
    }

    public UserRegisterBindingModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UserRegisterBindingModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UserRegisterBindingModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public UserRegisterBindingModel setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public UserRegisterBindingModel setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
        return this;
    }
}
