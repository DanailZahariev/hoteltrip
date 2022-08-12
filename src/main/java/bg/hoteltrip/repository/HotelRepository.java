package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    List<HotelEntity> findHotelEntitiesByTown_TownName(@Param("townName")
                                                       String townName);

    List<HotelEntity> findAllByHotelName(String hotelName);

    Optional<HotelEntity> findHotelEntitiesByHotelName(String name);

    HotelEntity findByHotelName(String hotelName);
}
