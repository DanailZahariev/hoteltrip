package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelViewModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface HotelRepository extends JpaRepository<HotelEntity, Long> {

    @Query("select h,min (r.price) from HotelEntity h join h.rooms r" +
            " where h.town.townName=:townName group by h.id")
    List<HotelEntity> findHotelEntitiesByTown_TownName(@Param("townName")
                                                       String townName);

    Optional<HotelEntity> findHotelEntitiesByHotelName(String name);

}
