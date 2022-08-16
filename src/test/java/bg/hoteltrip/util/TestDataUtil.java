package bg.hoteltrip.util;

import bg.hoteltrip.model.entity.*;
import bg.hoteltrip.model.entity.enums.RoleEnum;
import bg.hoteltrip.repository.*;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TestDataUtil {

    private UserRepository userRepository;
    private UserRoleRepository userRoleRepository;

    private PictureRepository pictureRepository;

    private TownRepository townRepository;

    public TestDataUtil(UserRepository userRepository,
                        UserRoleRepository userRoleRepository,
                        PictureRepository pictureRepository,
                        TownRepository townRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.pictureRepository = pictureRepository;
        this.townRepository = townRepository;
    }

    private void initRoles() {
        if (userRoleRepository.count() == 0) {
            UserRoleEntity admin = new UserRoleEntity().setRole(RoleEnum.ADMIN);
            UserRoleEntity user = new UserRoleEntity().setRole(RoleEnum.USER);
            userRoleRepository.save(admin);
            userRoleRepository.save(user);
        }
    }

    public PictureEntity initProfilePicture() {

        PictureEntity picture = new PictureEntity();
        picture.setUrl("http//image.com")
                .setPublicId("publicID");

        return pictureRepository.save(picture);
    }


    public TownEntity initTown() {

        TownEntity town = new TownEntity().setTownName("Sofia");

        return townRepository.save(town);
    }


    public UserEntity initAdmin() {

        initRoles();

        UserEntity admin = new UserEntity()
                .setEmail("admin@admin.com")
                .setFirstName("admin")
                .setLastName("adminov")
                .setPassword("123456")
                .setRoles(List.of(userRoleRepository.findByRole(RoleEnum.USER),
                        userRoleRepository.findByRole(RoleEnum.ADMIN)));

        return userRepository.save(admin);
    }

    public UserEntity initUser() {

        initRoles();
        PictureEntity picture = initProfilePicture();

        UserEntity user = new UserEntity()
                .setEmail("user@user.com")
                .setFirstName("user")
                .setLastName("userov")
                .setPassword("123456")
                .setProfilePictureUrl(picture)
                .setRoles(List.of(userRoleRepository.findByRole(RoleEnum.USER)));

        return userRepository.save(user);
    }
//    public HotelEntity initHotel() {
//
//        HotelEntity hotel = new HotelEntity().setHotelName("Hotel Sofia")
//                .setDescription("some description")
//                .setTown(townRepository.findByTownName("Sofia"));
//
//
//        return hotelRepository.save(hotel);
//    }

    public void cleanUp() {
        townRepository.deleteAll();
        userRepository.deleteAll();
        pictureRepository.deleteAll();
        userRoleRepository.deleteAll();
//        hotelRepository.deleteAll();
//        roomRepository.deleteAll();
    }
}
