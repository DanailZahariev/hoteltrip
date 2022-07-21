package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {


    List<HotelEntity> findHotelEntitiesByTownId(Long id);
}
