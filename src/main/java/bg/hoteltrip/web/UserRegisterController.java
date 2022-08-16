package bg.hoteltrip.web;

import bg.hoteltrip.model.binding.UserRegisterBindingModel;
import bg.hoteltrip.model.service.UserServiceModel;
import bg.hoteltrip.service.UserService;
import bg.hoteltrip.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@RequestMapping("/users")
public class UserRegisterController {

    private final UserService userService;
    private final ModelMapper modelMapper;

    public UserRegisterController(UserService userService,
                                  ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }


    @GetMapping("/register")
    public String register() {
        return "register";
    }


    @PostMapping("/register")
    public String registerUser(@Valid UserRegisterBindingModel userRegisterBindingModel,
                               BindingResult bindingResult,
                               RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("userRegisterBindingModel", userRegisterBindingModel)
                    .addFlashAttribute("org.springframework.validation.BindingResult.userRegisterBindingModel", bindingResult);
            return "redirect:register";
        }

        UserServiceModel newUser = modelMapper.map(userRegisterBindingModel, UserServiceModel.class);
        userService.registerUser(newUser);

        return "redirect:/";
    }

    @ModelAttribute
    public UserRegisterBindingModel userRegisterBindingModel() {
        return new UserRegisterBindingModel();
    }
}
