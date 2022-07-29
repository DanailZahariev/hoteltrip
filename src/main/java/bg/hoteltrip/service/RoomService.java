package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.repository.RoomRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
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
}
