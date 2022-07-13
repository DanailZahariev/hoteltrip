package bg.hoteltrip.model.service;

import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.repository.UserRoleRepository;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleService(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    public UserRoleEntity findByRole(RoleEnum role) throws RoleNotFoundException {
        return userRoleRepository.findAllByRole(role)
                .orElseThrow(() -> new RoleNotFoundException("Role " + role + " does not exist!"));
    }
}
