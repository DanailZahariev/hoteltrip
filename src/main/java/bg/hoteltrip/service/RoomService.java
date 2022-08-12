package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelRoomViewModel;
import bg.hoteltrip.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomService(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }


    public RoomEntity findByRoomId(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    public RoomEntity saveRoom(RoomEntity room) {
        return roomRepository.save(room);
    }

    public List<HotelRoomViewModel> findAllRoomsByHotelAndType(HotelEntity hotel, String roomType) {

        return roomRepository.findAllByHotelAndRoomType(hotel, RoomTypeEnum.valueOf(roomType))
                .stream()
                .map(room -> modelMapper
                        .map(room, HotelRoomViewModel.class))
                .collect(Collectors.toList());
    }
}
