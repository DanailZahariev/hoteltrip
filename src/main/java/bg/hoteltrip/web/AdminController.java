package bg.hoteltrip.web;

import bg.hoteltrip.model.binding.HotelAddBindingModel;
import bg.hoteltrip.model.view.UsersAllViewModel;
import bg.hoteltrip.service.HotelService;
import bg.hoteltrip.service.UserService;
import bg.hoteltrip.service.impl.HotelServiceImpl;
import bg.hoteltrip.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.management.relation.RoleNotFoundException;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;
    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    public AdminController(UserService userService,
                           HotelService hotelService,
                           ModelMapper modelMapper) {
        this.userService = userService;
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public String adminPage() {
        return "admin";
    }

    @GetMapping("/all-users")
    public String viewAndDeleteUsers(Model model) {

        List<UsersAllViewModel> users = this.userService.findAllUsers()
                .stream()
                .map(usersAllServiceModel -> this.modelMapper.map(usersAllServiceModel, UsersAllViewModel.class))
                .collect(Collectors.toList());

        model.addAttribute("allUsers", users);
        return "all-users";
    }

    @PatchMapping("/all-users/add-admin")
    public String makeUserAdmin(@RequestParam Long id) throws RoleNotFoundException {

        userService.makeUserAdmin(id);
        return "redirect:";
    }

    @PatchMapping("/all-users/remove-admin")
    public String removeUserAdmin(@RequestParam Long id) throws RoleNotFoundException {
        userService.removeAdminRole(id);
        return "redirect:";
    }

    @DeleteMapping("/all-users/remove-user")
    public String deleteUser(@RequestParam Long id) {
        userService.deleteUserById(id);
        return "redirect:";
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
