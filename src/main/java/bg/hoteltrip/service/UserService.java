package bg.hoteltrip.service;

import bg.hoteltrip.config.AdminProperties;
import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.model.view.UserViewModel;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleService userRoleService;
    private final AdminProperties adminProperties;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService,
                       ModelMapper modelMapper,
                       UserRoleService userRoleService, AdminProperties adminProperties) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.adminProperties = adminProperties;
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

    public UserProfileViewModel getUserProfile(String name) {
        Optional<UserEntity> user = userRepository.findUserEntitiesByEmailIgnoreCase(name);

        return modelMapper.map(user, UserProfileViewModel.class);
    }

    public void initAdmin() throws RoleNotFoundException {
        if (userRepository.count() != 0) {
            return;
        }
        UserEntity admin = new UserEntity();
        admin.setEmail(adminProperties.getEmail())
                .setPassword(passwordEncoder.encode(adminProperties.getPassword()))
                .setFirstName("admin").setLastName("admin").setUsername("admin")
                .setRoles(List.of(userRoleService.findByRole(RoleEnum.ADMIN)));

        userRepository.save(admin);
    }

    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findUserEntitiesByEmailIgnoreCase(email);
    }

    public Optional<UserEntity> findByUsername(String username) {
        return userRepository.findUserEntitiesByUsernameIgnoreCase(username);
    }

    public List<UserViewModel> findAllUsers() {
        return userRepository.findAllUsersByRoleUser()
                .stream()
                .map(user ->
                        modelMapper.map(user, UserViewModel.class)).
                collect(Collectors.toList());
    }

    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }
}
