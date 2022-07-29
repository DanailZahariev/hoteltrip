package bg.hoteltrip.init;

import bg.hoteltrip.service.RoomService;
import bg.hoteltrip.service.UserRoleService;
import bg.hoteltrip.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitBaseData implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;
    private final RoomService roomService;

    public InitBaseData(UserService userService,
                        UserRoleService userRoleService,
                        RoomService roomService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.roomService = roomService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.initRoles();
        userService.initAdmin();
        roomService.initRooms();
    }
}
