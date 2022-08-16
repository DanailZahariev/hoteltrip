package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.HotelService;
import bg.hoteltrip.service.RoomService;
import bg.hoteltrip.service.impl.HotelServiceImpl;
import bg.hoteltrip.service.impl.RoomServiceImpl;
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
    private final RoomService roomService;

    public HotelController(HotelService hotelService,
                           ModelMapper modelMapper,
                           RoomService roomService) {
        this.hotelService = hotelService;
        this.modelMapper = modelMapper;
        this.roomService = roomService;
    }

    @GetMapping("{townName}")
    public String hotelViewByTown(@PathVariable String townName,
                                  Model model) {

        List<HotelViewModel> hotel = hotelService.findHotelByTownName(townName);
        if (hotel.isEmpty()) {
            throw new HotelsNotFoundException(townName);
        }

        model.addAttribute("hotel", hotel);

        return "all-hotels";
    }

    @GetMapping("/hotel-details/{name}")
    public String hotelDetailView(@PathVariable String name,
                                  Model model) {


        HotelViewModel hotelDetail = modelMapper.map(hotelService.findHotelByName(name), HotelViewModel.class);

        if (hotelDetail == null) {
            throw new HotelsNotFoundException(name);
        }


        model.addAttribute("hotelDetail", hotelDetail);


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
