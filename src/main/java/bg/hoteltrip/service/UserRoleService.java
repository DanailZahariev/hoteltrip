package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;

import javax.management.relation.RoleNotFoundException;

public interface UserRoleService {

    UserRoleEntity findByRole(RoleEnum role);

    void initRoles();
}
