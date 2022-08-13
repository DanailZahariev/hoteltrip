package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.repository.RoomRepository;
import bg.hoteltrip.service.RoomService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;
    private final ModelMapper modelMapper;

    public RoomServiceImpl(RoomRepository roomRepository, ModelMapper modelMapper) {
        this.roomRepository = roomRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public RoomEntity findByRoomId(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public RoomEntity saveRoom(RoomEntity room) {
        return roomRepository.save(room);
    }
}
