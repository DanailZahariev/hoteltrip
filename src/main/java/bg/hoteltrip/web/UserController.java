package bg.hoteltrip.web;

import bg.hoteltrip.model.binding.UserProfilePictureAddBindingModel;
import bg.hoteltrip.model.binding.UserProfileUpdateBindingModel;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.model.view.UserProfileViewModel;
import bg.hoteltrip.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserController(UserService userService,
                          ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
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
    public String getUserProfile(Principal principal,
                                 Model model) {

        UserProfileViewModel userProfile = userService.getUserProfile(principal.getName());

        model.addAttribute("user", userProfile);

        return "user-profile";
    }


    @GetMapping("/profile/edit")
    public String userProfileUpdate(Principal principal,
                                    Model model) {
        UserProfileViewModel userProfile = userService.getUserProfile(principal.getName());

        model.addAttribute("user", userProfile);

        return "user-update";
    }

    @PostMapping("/profile/edit/")
    public String userProfileEdit(@Valid UserProfileUpdateBindingModel userProfileUpdateBindingModel,
                                  BindingResult bindingResult,
                                  RedirectAttributes redirectAttributes,
                                  Principal principal) {


        UserServiceModel user = userService.findUser(principal.getName());


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userProfileUpdateBindingModel", userProfileUpdateBindingModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.userProfileUpdateBindingModel",
                    bindingResult);

            return "redirect:/users/profile/edit";
        }

        userService.updateUserProfile(modelMapper.map(userProfileUpdateBindingModel, UserServiceModel.class) ,user.getEmail());

        return "redirect:/users/profile/";
    }


    @PostMapping("/profile/edit/picture")
    public String updateProfilePicture(@Valid UserProfilePictureAddBindingModel userProfilePictureAddBindingModel,
                                       RedirectAttributes redirectAttributes,
                                       Principal principal) throws IOException {

        if (userProfilePictureAddBindingModel.getProfilePictureUrl().isEmpty()) {
            redirectAttributes.addFlashAttribute("error",
                    "Please select a picture!");
            return "redirect:";
        }

        userService.addNewProfilePicture(principal.getName(), userProfilePictureAddBindingModel);

        return "redirect:/users/profile/";
    }

    @Transactional
    @GetMapping("/profile/edit/picture/delete")
    public String deleteProfilePicture(Principal principal) {

        userService.deleteProfilePicture(principal.getName());

        return "redirect:/users/profile/";
    }

    @ModelAttribute
    public UserProfilePictureAddBindingModel userProfilePictureAddBindingModel() {
        return new UserProfilePictureAddBindingModel();
    }

    @ModelAttribute
    public UserProfileUpdateBindingModel userProfileUpdateBindingModel() {
        return new UserProfileUpdateBindingModel();
    }
}
