package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    @Query("select h,min (r.price) from HotelEntity h join h.rooms r" +
            " where h.town.townName=:townName group by h.id")
    List<HotelEntity> findHotelEntitiesByTown_TownName(@Param("townName")
                                                       String townName);
}
