package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelRoomViewModel;
import bg.hoteltrip.repository.RoomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
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


    public void initRooms() {
        if (roomRepository.count() != 0) {
            return;
        }

        Arrays.stream(RoomTypeEnum.values()).forEach(roomTypeEnum -> {
            RoomEntity rooms = new RoomEntity();
            switch (roomTypeEnum) {
                case STUDIO -> rooms.setRoomType(RoomTypeEnum.STUDIO).
                        setPrice(BigDecimal.valueOf(99.00));
                case DOUBLE_ROOM -> rooms.setRoomType(RoomTypeEnum.DOUBLE_ROOM).
                        setPrice(BigDecimal.valueOf(129.00));
                case APARTMENT -> rooms.setRoomType(RoomTypeEnum.APARTMENT).
                        setPrice(BigDecimal.valueOf(199.00));
            }
            roomRepository.save(rooms);
        });
    }

    public RoomEntity findByRoomType(RoomTypeEnum roomTypeEnum) {
        return roomRepository.findByRoomType(roomTypeEnum);
    }

    public List<HotelRoomViewModel> findAllRoomsByHotelName(RoomTypeEnum roomTypeEnum, String name) {
        return roomRepository.findAllByRoomTypeAndHotelEntityHotelName(roomTypeEnum, name).
                stream().map(roomEntity -> modelMapper.map(roomEntity, HotelRoomViewModel.class)).
                collect(Collectors.toList());
    }

}
