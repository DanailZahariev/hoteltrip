package bg.hoteltrip.repository;

import bg.hoteltrip.model.entity.TownEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface TownRepository extends JpaRepository<TownEntity, Long> {

    boolean existsByTownName(String townName);

    TownEntity findByTownName(String townName);
}
