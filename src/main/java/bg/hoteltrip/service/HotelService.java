package bg.hoteltrip.service;

import bg.hoteltrip.model.binding.HotelAddBindingModel;
import bg.hoteltrip.model.view.HotelViewModel;

import java.io.IOException;
import java.util.List;

public interface HotelService {

    List<HotelViewModel> findHotelByTownName(String townName);

    HotelViewModel findHotelByName(String name);

    void addHotelEntity(HotelAddBindingModel hotelAddBindingModel) throws IOException;
}
