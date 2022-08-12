package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.ReservationEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {


    Optional<ReservationEntity> findByRooms(RoomEntity room);
}
