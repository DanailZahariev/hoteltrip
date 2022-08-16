package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.repository.UserRoleRepository;
import bg.hoteltrip.service.UserRoleService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.relation.RoleNotFoundException;


import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {


    @Mock
    private UserRoleRepository userRoleRepository;

    private UserRoleService userRoleService;

    @BeforeEach
    void setUp() {
        userRoleService = new UserRoleServiceImpl(userRoleRepository);
    }

    @AfterEach
    void tearDown() {
        userRoleRepository.deleteAll();
    }

    @Test
    void findByRole_Success() throws RoleNotFoundException {

        UserRoleEntity test = new UserRoleEntity();
        test.setRole(RoleEnum.ADMIN).setId(1L);

        when(userRoleRepository.findByRole(RoleEnum.ADMIN))
                .thenReturn(test);

        UserRoleEntity role = userRoleService.findByRole(test.getRole());

        Assertions.assertEquals("ADMIN", role.getRole().name());
    }
}
