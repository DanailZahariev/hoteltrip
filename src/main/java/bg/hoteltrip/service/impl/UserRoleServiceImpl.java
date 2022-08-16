package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.repository.UserRoleRepository;
import bg.hoteltrip.service.UserRoleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private final UserRoleRepository userRoleRepository;

    public UserRoleServiceImpl(UserRoleRepository userRoleRepository) {
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public UserRoleEntity findByRole(RoleEnum role) {
        return userRoleRepository.findByRole(role);
    }

    @Override
    public void initRoles() {
        if (this.userRoleRepository.count() == 0) {
            Arrays.stream(RoleEnum.values())
                    .forEach(r -> {
                        UserRoleEntity userRole = new UserRoleEntity();
                        userRole.setRole(r);

                        this.userRoleRepository.save(userRole);
                    });
        }
    }
}