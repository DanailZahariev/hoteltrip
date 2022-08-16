package bg.hoteltrip.web;

import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.service.impl.HotelServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

        return ResponseEntity.ok().body(hotel);
    }
}
