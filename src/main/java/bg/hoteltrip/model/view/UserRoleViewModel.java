package bg.hoteltrip.model.view;

import bg.hoteltrip.model.entity.enums.RoleEnum;

public class UserRoleViewModel {

    private RoleEnum role;


    public RoleEnum getRole() {
        return role;
    }

    public UserRoleViewModel setRole(RoleEnum role) {
        this.role = role;
        return this;
    }
}
