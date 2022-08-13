package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.binding.UserProfilePictureAddBindingModel;
import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.UserRoleEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.service.UsersAllServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.repository.UserRepository;
import bg.hoteltrip.service.impl.CloudinaryServiceImpl;
import bg.hoteltrip.service.impl.PictureServiceImpl;
import bg.hoteltrip.service.impl.UserRoleServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsService userDetailsService;
    private final ModelMapper modelMapper;
    private final UserRoleServiceImpl userRoleServiceImpl;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;
    private final PictureServiceImpl pictureServiceImpl;

    public UserServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           UserDetailsService userDetailsService,
                           ModelMapper modelMapper,
                           UserRoleServiceImpl userRoleServiceImpl,
                           CloudinaryServiceImpl cloudinaryServiceImpl, PictureServiceImpl pictureServiceImpl) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRoleServiceImpl = userRoleServiceImpl;
        this.cloudinaryServiceImpl = cloudinaryServiceImpl;
        this.pictureServiceImpl = pictureServiceImpl;
    }

    @Override
    public void registerUser(UserServiceModel registerUser) throws RoleNotFoundException {

        UserEntity newUser = modelMapper.map(registerUser, UserEntity.class);
        newUser.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        newUser.setRoles(List.of(userRoleServiceImpl.findByRole(RoleEnum.USER)));

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

    @Override
    public UserProfileViewModel getUserProfile(String name) {
        UserEntity user = userRepository.findUserEntityByEmail(name).
                orElseThrow(() -> new UsernameNotFoundException("User not found."));

        return modelMapper.map(user, UserProfileViewModel.class);
    }

    @Override
    public void initAdmin() throws RoleNotFoundException {
        if (userRepository.count() != 0) {
            return;
        }
        UserRoleEntity adminRole = userRoleServiceImpl.findByRole(RoleEnum.ADMIN);
        UserRoleEntity userRole = userRoleServiceImpl.findByRole(RoleEnum.USER);

        UserEntity admin = new UserEntity();
        admin.setEmail("admin@admin.com")
                .setPassword(passwordEncoder.encode("123456"))
                .setFirstName("admin").setLastName("admin")
                .setRoles(List.of(adminRole, userRole));

        userRepository.save(admin);
    }

    @Override
    public Optional<UserEntity> findByEmail(String email) {
        return userRepository.findUserEntityByEmail(email);
    }

    @Override
    public List<UsersAllServiceModel> findAllUsers() {
        return this.userRepository.findAll()
                .stream()
                .map(user -> modelMapper.map(user, UsersAllServiceModel.class)
                )
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void addNewProfilePicture(String name,
                                     UserProfilePictureAddBindingModel userProfilePictureAddBindingModel) throws IOException {

        var user = userRepository.findUserEntityByEmail(name).orElseThrow(()
                -> new UsernameNotFoundException("User not found."));

        var picture = pictureServiceImpl.
                createPictureEntity(userProfilePictureAddBindingModel.getProfilePictureUrl());

        pictureServiceImpl.savePicture(picture);

        user.setProfilePictureUrl(picture);
        userRepository.save(user);
    }

    @Override
    public void deleteProfilePicture(String name) {
        UserEntity user = userRepository.findUserEntityByEmail(name).orElseThrow(() ->
                new UsernameNotFoundException("User not found."));

        cloudinaryServiceImpl.delete(user.getProfilePictureUrl().getPublicId());
        String publicId = user.getProfilePictureUrl().getPublicId();
        pictureServiceImpl.deletePicture(publicId);
        user.setProfilePictureUrl(null);
        userRepository.save(user);
    }

    @Override
    public UserServiceModel findUser(String email) {
        return this.userRepository
                .findByEmailIgnoreCase(email)
                .map(userEntity -> modelMapper.map(userEntity, UserServiceModel.class))
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not exists."));
    }

    @Override
    public void updateUserProfile(UserServiceModel userServiceModel, String email) {

        UserEntity user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " not exists."));


        user.setEmail(userServiceModel.getEmail())
                .setFirstName(userServiceModel.getFirstName())
                .setLastName(userServiceModel.getLastName());

        userRepository.save(user);
    }

    @Override
    public void makeUserAdmin(Long id) throws RoleNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(()
                -> new UsernameNotFoundException("User with id " + id + " not exists."));
        user.getRoles().add(userRoleServiceImpl.findByRole(RoleEnum.ADMIN));
        userRepository.save(user);
    }

    @Override
    public void removeAdminRole(Long id) throws RoleNotFoundException {
        UserEntity user = userRepository.findById(id).orElseThrow(()
                -> new UsernameNotFoundException("User with id " + id + " not exists."));
        if (user.getId() != 1) {
            user.getRoles().remove(this.userRoleServiceImpl.findByRole(RoleEnum.ADMIN));
            userRepository.save(user);
        }

    }
}
