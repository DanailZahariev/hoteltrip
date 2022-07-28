package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.user.HotelTripUserDetails;
import bg.hoteltrip.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

public class HotelTripDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public HotelTripDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        if (!userRepository.existsByEmail(email)) {
            throw new UsernameNotFoundException("User not found");
        }
        UserEntity user = userRepository.findByEmail(email);

        List<GrantedAuthority> authorities = user.getRoles()
                .stream().map(u -> "ROLE_" + u.getRole().name())
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        return new HotelTripUserDetails(user, authorities);
    }

//    private UserDetails map(UserEntity userEntity) {
//        return new HotelTripUserDetails(
//                userEntity.getId(),
//                userEntity.getPassword(),
//                userEntity.getEmail(),
//                userEntity.getFirstName(),
//                userEntity.getLastName(),
//                userEntity.
//                        getRoles().
//                        stream().
//                        map(this::mapRoles).
//                        toList()
//        );
//    }
//
//    private GrantedAuthority mapRoles(UserRoleEntity userRole) {
//        return new SimpleGrantedAuthority("ROLE_" +
//                userRole.
//                        getRole().name());
//    }
}
