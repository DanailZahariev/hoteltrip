package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.HotelService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final ModelMapper modelMapper;

    public HotelController(HotelService hotelService,
                           ModelMapper modelMapper) {
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("{id}")
    public String hotelView(@PathVariable Long id, Model model) {


        List<HotelViewModel> hotel = hotelService.findHotelByTownId(id);

        if (hotel.isEmpty()) {
            throw new HotelsNotFoundException(id);
        }

        model.addAttribute("hotel", hotel);

        return "hotel-view";
    }


    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({HotelsNotFoundException.class})
    public ModelAndView onProductNotFound(HotelsNotFoundException hnfe) {
        ModelAndView modelAndView = new ModelAndView("hotel-not-found");
        modelAndView.addObject("productId", hnfe.getObjectId());

        return modelAndView;
    }
}