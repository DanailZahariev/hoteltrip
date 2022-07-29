package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.repository.TownRepository;
import org.springframework.stereotype.Service;

@Service
public class TownService {

    private final TownRepository townRepository;

    public TownService(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    public boolean existTown(String townName) {
        return townRepository.existsByTownName(townName);
    }

    public TownEntity findBytownName(String townName) {
        return townRepository.findByTownName(townName);
    }

    public TownEntity saveTown(TownEntity town) {
        return townRepository.save(town);
    }
}
