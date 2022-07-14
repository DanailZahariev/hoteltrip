package bg.hoteltrip.web;

import bg.hoteltrip.model.entity.UserEntity;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.service.UserService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final UserService userService;

    public ProfileController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public String getUserProfile(Model model, Principal principal) {

        UserProfileViewModel userProfile = userService.getUserProfile(principal.getName());

        model.addAttribute("user", userProfile);

        return "user-profile";
    }
}
