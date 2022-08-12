package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.dto.SearchAvailabilityHotelDTO;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelReservationViewModel;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.HotelService;
import bg.hoteltrip.service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/hotels")
public class HotelController {

    private final HotelService hotelService;
    private final ReservationService reservationService;

    public HotelController(HotelService hotelService,
                           ReservationService reservationService) {
        this.hotelService = hotelService;
        this.reservationService = reservationService;
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
                                  SearchAvailabilityHotelDTO searchAvailabilityHotelDTO,
                                  Model model) {

        LocalDate startDate = searchAvailabilityHotelDTO.getStartDate();
        LocalDate endDate = searchAvailabilityHotelDTO.getEndDate();





        HotelViewModel hotelDetail = hotelService.findHotelByName(name);
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

    @ModelAttribute
    public SearchAvailabilityHotelDTO searchAvailabilityHotelDTO() {
        return new SearchAvailabilityHotelDTO();
    }
}
