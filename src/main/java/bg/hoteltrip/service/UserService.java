package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.service.UserRoleService;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService,
                       ModelMapper modelMapper,
                       UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
    }


    public boolean findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email).isEmpty();
    }

    public void registerUser(UserServiceModel registerUser) throws RoleNotFoundException {

        UserEntity newUser = modelMapper.map(registerUser, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setRoles(List.of(userRoleService.findByRole(RoleEnum.USER)));

        userRepository.save(newUser);
        loginUser(newUser);
    }

    private void loginUser(UserEntity user) {

        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        Authentication authentication =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities());

        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public UserProfileViewModel getProfileViewByEmail(String email) {
        UserEntity user = userRepository.findByEmailIgnoreCase(email).orElseThrow();
        return modelMapper.map(user, UserProfileViewModel.class);
    }
}
