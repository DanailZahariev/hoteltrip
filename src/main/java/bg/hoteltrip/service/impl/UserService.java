package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.binding.UserProfilePictureAddBindingModel;
import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.service.UsersAllServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;

import javax.management.relation.RoleNotFoundException;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserService {


    void registerUser(UserServiceModel registerUser) throws RoleNotFoundException;

    UserProfileViewModel getUserProfile(String name);

    void initAdmin() throws RoleNotFoundException;

    Optional<UserEntity> findByEmail(String email);

    List<UsersAllServiceModel> findAllUsers();

    void deleteUserById(Long id);

    void addNewProfilePicture(String name, UserProfilePictureAddBindingModel userProfilePictureAddBindingModel) throws IOException;

    void deleteProfilePicture(String name);

    UserServiceModel findUser(String email);

    void updateUserProfile(UserServiceModel userServiceModel, String email);

    void makeUserAdmin(Long id) throws RoleNotFoundException;

    void removeAdminRole(Long id) throws RoleNotFoundException;
}
