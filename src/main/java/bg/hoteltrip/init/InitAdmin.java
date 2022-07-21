package bg.hoteltrip.init;

import bg.hoteltrip.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitAdmin implements CommandLineRunner {

    private final UserService userService;

    public InitAdmin(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.initAdmin();
    }
}
