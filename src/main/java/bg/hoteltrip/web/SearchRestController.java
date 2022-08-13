package bg.hoteltrip.web;

import bg.hoteltrip.config.exceptions.HotelsNotFoundException;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.impl.HotelServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
@RequestMapping("/api/search/")
public class SearchRestController {


    private final HotelServiceImpl hotelServiceImpl;

    public SearchRestController(HotelServiceImpl hotelServiceImpl) {
        this.hotelServiceImpl = hotelServiceImpl;
    }


    @GetMapping(value = "{townName}")
    public ResponseEntity<List<HotelViewModel>> findAllByTown(
            @PathVariable("townName") String townName) {


        List<HotelViewModel> hotel = hotelServiceImpl.findHotelByTownName(townName);
        if (hotel.isEmpty()) {
            throw new HotelsNotFoundException(townName);
        }

        return ResponseEntity.ok().body(hotel);
    }



    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler({HotelsNotFoundException.class})
    public ModelAndView onHotelNotFound(HotelsNotFoundException hnfe) {
        ModelAndView modelAndView = new ModelAndView("hotel-not-found");
        modelAndView.addObject("hotel", hnfe.getHotelName());

        return modelAndView;
    }
}
