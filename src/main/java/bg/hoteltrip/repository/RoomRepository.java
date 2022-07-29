package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<RoomEntity, Long> {
    RoomEntity findByRoomType(RoomTypeEnum roomTypeEnum);
}
