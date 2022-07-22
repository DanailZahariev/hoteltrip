package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.user.HotelTripUser;
import bg.hoteltrip.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class HotelTripDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public HotelTripDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        return userRepository.
                findUserEntitiesByEmailIgnoreCase(email).
                map(this::map).
                orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not found!"));
    }

    private UserDetails map(UserEntity userEntity) {

        return new HotelTripUser(
                userEntity.getPassword(),
                userEntity.getEmail(),
                userEntity.getFirstName(),
                userEntity.getLastName(),
                userEntity.
                        getRoles().
                        stream().
                        map(this::mapRoles).
                        toList()
        );
    }

    private GrantedAuthority mapRoles(UserRoleEntity userRole) {
        return new SimpleGrantedAuthority("ROLE_" +
                userRole.
                        getRole().name());
    }
}
