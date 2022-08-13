package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.RoomEntity;

public interface RoomService {

    RoomEntity saveRoom(RoomEntity room);

    RoomEntity findByRoomId(Long id);
}
