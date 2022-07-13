package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelTripDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public HotelTripDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) {
        UserEntity userEmail = userRepository.findByEmailIgnoreCase(email).orElse(null);

        if (userEmail == null) {
            throw new UsernameNotFoundException("User with email " + email + " not found");
        }

        return mapToUserDetails(userEmail);
    }

    private UserDetails mapToUserDetails(UserEntity userEmail) {

        List<GrantedAuthority> authorities =
                userEmail.getRoles()
                        .stream()
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                        .collect(Collectors.toList());

        return new User(userEmail.getEmail(), userEmail.getPassword(), authorities);
    }
}
