package bg.hoteltrip.service;

import bg.hoteltrip.model.binding.UserProfilePictureAddBindingModel;
import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.model.view.UserViewModel;
import bg.hoteltrip.repository.UserRepository;
import com.fasterxml.jackson.databind.JsonNode;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.management.relation.RoleNotFoundException;
import java.io.IOException;
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
    private final CloudinaryService cloudinaryService;
    private final PictureService pictureService;

    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserDetailsService userDetailsService,
                       ModelMapper modelMapper,
                       UserRoleService userRoleService,
                       CloudinaryService cloudinaryService, PictureService pictureService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.modelMapper = modelMapper;
        this.userRoleService = userRoleService;
        this.cloudinaryService = cloudinaryService;
        this.pictureService = pictureService;
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
        UserEntity user = userRepository.findByEmail(name);

        return modelMapper.map(user, UserProfileViewModel.class);
    }

    public void initAdmin() throws RoleNotFoundException {
        if (userRepository.count() != 0) {
            return;
        }
        UserEntity admin = new UserEntity();
        admin.setEmail("admin@admin.com")
                .setPassword(passwordEncoder.encode("123456"))
                .setFirstName("admin").setLastName("admin")
                .setRoles(List.of(userRoleService.findByRole(RoleEnum.ADMIN)));

        userRepository.save(admin);
    }

    public UserEntity findByEmail(String email) {
        return userRepository.findByEmail(email);
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

    private PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        final CloudinaryImage upload = cloudinaryService.upload(picture);

        PictureEntity image = new PictureEntity();

        image.setPublicId(upload.getPublicId());
        image.setTittle(picture.getName());
        image.setUrl(upload.getUrl());

        return image;
    }

    public void addNewProfilePicture(String name,
                                     UserProfilePictureAddBindingModel userProfilePictureAddBindingModel) throws IOException {

        var user = userRepository.findByEmail(name);

        var picture = createPictureEntity(userProfilePictureAddBindingModel.getProfilePictureUrl());

        pictureService.savePicture(picture);

        user.setProfilePictureUrl(picture);
        userRepository.save(user);
    }

    public void deleteProfilePicture(String name) {
        UserEntity user = userRepository.findByEmail(name);

        cloudinaryService.delete(user.getProfilePictureUrl().getPublicId());
        String publicId = user.getProfilePictureUrl().getPublicId();
        pictureService.deletePicture(publicId);
        user.setProfilePictureUrl(null);
        userRepository.save(user);
    }
}
