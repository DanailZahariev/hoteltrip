package bg.hoteltrip.web;

import bg.hoteltrip.model.view.UserViewModel;
import bg.hoteltrip.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
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
}
