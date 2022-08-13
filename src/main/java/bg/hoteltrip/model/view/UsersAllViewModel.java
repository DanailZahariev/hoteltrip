package bg.hoteltrip.model.view;

import java.util.List;
import java.util.Set;

public class UsersAllViewModel {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<UserRoleViewModel> roles;

    public UsersAllViewModel() {
    }

    public Long getId() {
        return id;
    }

    public UsersAllViewModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersAllViewModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersAllViewModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersAllViewModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleViewModel> getRoles() {
        return roles;
    }

    public UsersAllViewModel setRoles(List<UserRoleViewModel> roles) {
        this.roles = roles;
        return this;
    }
}
