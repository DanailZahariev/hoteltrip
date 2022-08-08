package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelRoomViewModel;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.HotelService;
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

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    @GetMapping("{townName}")
    public String hotelViewByTown(@PathVariable String townName, Model model) {

        List<HotelViewModel> hotel = hotelService.findHotelByTownName(townName);
        if (hotel.isEmpty()) {
            throw new HotelsNotFoundException(townName);
        }

        model.addAttribute("hotel", hotel);

        return "all-hotels";
    }

    @GetMapping("/hotel-details/{name}")
    public String hotelDetailView(@PathVariable String name, Model model) {

        HotelViewModel hotelDetail = hotelService.findHotelByName(name);
        model.addAttribute("hotelDetail", hotelDetail);


        List<HotelRoomViewModel> studio = hotelService.findAvailabilityByRoomType(name, RoomTypeEnum.STUDIO);
        List<HotelRoomViewModel> apartment = hotelService.findAvailabilityByRoomType(name, RoomTypeEnum.APARTMENT);
        List<HotelRoomViewModel> doubleRoom = hotelService.findAvailabilityByRoomType(name, RoomTypeEnum.DOUBLE_ROOM);

        model.addAttribute("studioRooms", studio);
        model.addAttribute("doubleRooms", doubleRoom);
        model.addAttribute("apartmentRooms", apartment);

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
