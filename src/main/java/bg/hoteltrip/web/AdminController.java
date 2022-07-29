package bg.hoteltrip.web;

import bg.hoteltrip.model.binding.HotelAddBindingModel;
import bg.hoteltrip.model.view.UserViewModel;
import bg.hoteltrip.service.HotelService;
import bg.hoteltrip.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final HotelService hotelService;

    public AdminController(UserService userService, HotelService hotelService) {
        this.userService = userService;
        this.hotelService = hotelService;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/all-users")
    public String viewAndDeleteUsers(Model model) {

        List<UserViewModel> users = userService.findAllUsers();

        model.addAttribute("allUsers", users);
        return "all-users";
    }

    @GetMapping("/all-users/{id}")
    public ModelAndView deleteUser(@PathVariable Long id) {
        userService.deleteUserById(id);

        return new ModelAndView("admin");
    }


    @GetMapping("/add-hotel")
    public String hotel() {
        return "add-hotel";
    }


    @PostMapping("/add-hotel")
    public String addHotel(@Valid HotelAddBindingModel hotelAddBindingModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) throws IOException {


        if (bindingResult.hasErrors()) {
            redirectAttributes.
                    addFlashAttribute("hotelAddBindingModel",
                            hotelAddBindingModel);
            redirectAttributes.
                    addFlashAttribute("org.springframework.validation.BindingResult.hotelAddBindingModel",
                            bindingResult);
            
            return "redirect:add-hotel";
        }

        hotelService.addHotelEntity(hotelAddBindingModel);

        return "admin";
    }


    @ModelAttribute
    public HotelAddBindingModel hotelAddBindingModel() {
        return new HotelAddBindingModel();
    }
}
