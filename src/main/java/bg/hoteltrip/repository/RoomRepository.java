package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {

    List<RoomEntity> findAllByHotelAndRoomType(HotelEntity hotel, RoomTypeEnum roomType);

}
