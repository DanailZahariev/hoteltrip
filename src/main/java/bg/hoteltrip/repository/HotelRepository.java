package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    @Query("select h from HotelEntity h where h.town.townName=:townName")
    List<HotelEntity> findHotelEntitiesByHotelName(@Param("townName")
                                                   String townName);
}
