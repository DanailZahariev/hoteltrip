package bg.hoteltrip.web;

import bg.hoteltrip.model.binding.UserProfilePictureAddBindingModel;
import bg.hoteltrip.model.user.HotelTripUserDetails;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.service.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }


    @PostMapping("/login-error")
    public String onFailedLogin(@ModelAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                                String username, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY, username);
        redirectAttributes.addFlashAttribute("bad_credentials", true);

        return "redirect:login";
    }

    @GetMapping("/profile/")
    public String getUserProfile(@AuthenticationPrincipal HotelTripUserDetails hotelTripUserDetails,
                                 Model model) {

        UserProfileViewModel userProfile = userService.getUserProfile(hotelTripUserDetails.getUsername());

        model.addAttribute("user", userProfile);

        return "user-profile";
    }

    @GetMapping("/profile/update")
    public String updateProfilePicture() {
        return "update";
    }

    @PostMapping("/profile/update")
    public String updateProfilePicture(@Valid UserProfilePictureAddBindingModel userProfilePictureAddBindingModel,
                                       RedirectAttributes redirectAttributes,
                                       Principal principal) throws IOException {

        if (userProfilePictureAddBindingModel.getProfilePictureUrl().isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    "Please select a picture!");
            return "redirect:update";
        }

        userService.addNewProfilePicture(principal.getName(), userProfilePictureAddBindingModel);

        return "redirect:";
    }

    @Transactional
    @GetMapping("/profile/delete")
    public String deleteProfilePicture(Principal principal) {

        userService.deleteProfilePicture(principal.getName());

        return "redirect:";
    }

    @ModelAttribute
    public UserProfilePictureAddBindingModel userProfilePictureAddBindingModel() {
        return new UserProfilePictureAddBindingModel();
    }
}
