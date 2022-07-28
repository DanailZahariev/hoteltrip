package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.user.HotelTripUserDetails;
import bg.hoteltrip.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HotelTripDetailsServiceTest {

    @Mock
    private UserRepository mockUserRepo;

    private HotelTripDetailsService toTest;

    @BeforeEach
    void setUp() {
        toTest = new HotelTripDetailsService(mockUserRepo);
    }

    @Test
    public void testLoadUserByUsername_UserExist() {
        UserEntity testUser = new UserEntity().
                setEmail("test@example.com").
                setPassword("testpassword").
                setFirstName("Test").
                setLastName("Testov").
                setRoles(List.of(new UserRoleEntity().setRole(RoleEnum.ADMIN),
                        new UserRoleEntity().setRole(RoleEnum.USER)));


        when(mockUserRepo.existsByEmail(testUser.getEmail())).thenReturn(true);
        when(mockUserRepo.findByEmail(testUser.getEmail())).thenReturn(testUser);

        HotelTripUserDetails userDetails = (HotelTripUserDetails) toTest.loadUserByUsername(testUser.getEmail());

        Assertions.assertEquals(testUser.getEmail(), userDetails.getUsername());
        Assertions.assertEquals(testUser.getFirstName(), userDetails.getFirstName());
        Assertions.assertEquals(testUser.getLastName(), userDetails.getLastName());
        Assertions.assertEquals(testUser.getPassword(), userDetails.getPassword());
        Assertions.assertEquals(testUser.getFirstName() + " " + testUser.getLastName(),
                userDetails.getFullName());

        var authorities = userDetails.getAuthorities();
        Assertions.assertEquals(2, authorities.size());

        var authIter = authorities.iterator();

        Assertions.assertEquals("ROLE_" + RoleEnum.ADMIN.name(),
                authIter.next().getAuthority());
        Assertions.assertEquals("ROLE_" + RoleEnum.USER.name(),
                authIter.next().getAuthority());

    }

    @Test
    public void testLoadByUserByUsername_NotExist() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> toTest.loadUserByUsername("not-found@example.com"));
    }
}
