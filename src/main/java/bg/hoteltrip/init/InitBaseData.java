package bg.hoteltrip.init;

import bg.hoteltrip.service.UserRoleService;
import bg.hoteltrip.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitBaseData implements CommandLineRunner {

    private final UserService userService;
    private final UserRoleService userRoleService;

    public InitBaseData(UserService userService,
                        UserRoleService userRoleService) {
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleService.initRoles();
        userService.initAdmin();
    }
}
