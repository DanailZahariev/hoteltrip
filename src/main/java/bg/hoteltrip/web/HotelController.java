package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.impl.HotelServiceImpl;
import bg.hoteltrip.service.impl.RoomServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelServiceImpl hotelServiceImpl;
    private final RoomServiceImpl roomServiceImpl;

    public HotelController(HotelServiceImpl hotelServiceImpl, RoomServiceImpl roomServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;

        this.roomServiceImpl = roomServiceImpl;
    }

    @GetMapping("{townName}")
    public String hotelViewByTown(@PathVariable String townName,
                                  Model model) {

        List<HotelViewModel> hotel = hotelServiceImpl.findHotelByTownName(townName);
        if (hotel.isEmpty()) {
            throw new HotelsNotFoundException(townName);
        }

        model.addAttribute("hotel", hotel);

        return "all-hotels";
    }

    @GetMapping("/hotel-details/{name}")
    public String hotelDetailView(@PathVariable String name,
                                  Model model) {


        HotelViewModel hotelDetail = hotelServiceImpl.findHotelByName(name);

        if (hotelDetail == null) {
            throw new HotelsNotFoundException(name);
        }


        model.addAttribute("hotelDetail", hotelDetail);

        var hotel = hotelServiceImpl.findHotelByName(name);


//        model.addAttribute("studio", studio);
//        model.addAttribute("dRoom", dRoom);
//        model.addAttribute("apartment", apartment);

        return "hotel-details";
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({HotelsNotFoundException.class})
    public ModelAndView onHotelNotFound(HotelsNotFoundException hnfe) {
        ModelAndView modelAndView = new ModelAndView("hotel-not-found");
        modelAndView.addObject("hotel", hnfe.getHotelName());

        return modelAndView;
    }
}
