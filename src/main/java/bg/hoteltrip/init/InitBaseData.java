package bg.hoteltrip.init;

import bg.hoteltrip.service.impl.UserRoleServiceImpl;
import bg.hoteltrip.service.impl.UserServiceImpl;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitBaseData implements CommandLineRunner {

    private final UserServiceImpl userServiceImpl;
    private final UserRoleServiceImpl userRoleServiceImpl;

    public InitBaseData(UserServiceImpl userServiceImpl,
                        UserRoleServiceImpl userRoleServiceImpl) {
        this.userServiceImpl = userServiceImpl;
        this.userRoleServiceImpl = userRoleServiceImpl;
    }

    @Override
    public void run(String... args) throws Exception {
        userRoleServiceImpl.initRoles();
        userServiceImpl.initAdmin();
    }
}
