package bg.hoteltrip.service;

import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;

    public HotelService(HotelRepository hotelRepository,
                        ModelMapper modelMapper) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
    }

    public List<HotelViewModel> findHotelByTownId(Long id) {
        return hotelRepository.findHotelEntitiesByTownId(id).stream().
        map(hotel -> modelMapper.map(hotel, HotelViewModel.class))
                .collect(Collectors.toList());
    }
}
