package bg.hoteltrip.web;

import org.springframework.stereotype.Controller;

@Controller
public class HomeController {


    public String index() {
        return "index";
    }
}
