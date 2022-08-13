package bg.hoteltrip.model.service;

import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.view.UserRoleViewModel;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class UsersAllServiceModel {

    private Long id;
    private String email;
    private String firstName;
    private String lastName;
    private List<UserRoleViewModel> roles;

    public UsersAllServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public UsersAllServiceModel setId(Long id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public UsersAllServiceModel setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getFirstName() {
        return firstName;
    }

    public UsersAllServiceModel setFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public UsersAllServiceModel setLastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public List<UserRoleViewModel> getRoles() {
        return roles;
    }

    public UsersAllServiceModel setRoles(List<UserRoleViewModel> roles) {
        this.roles = roles;
        return this;
    }
}
