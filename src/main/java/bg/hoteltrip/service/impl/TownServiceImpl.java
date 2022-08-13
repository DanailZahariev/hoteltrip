package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.repository.TownRepository;
import bg.hoteltrip.service.TownService;
import org.springframework.stereotype.Service;

@Service
public class TownServiceImpl implements TownService {

    private final TownRepository townRepository;

    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public boolean existTown(String townName) {
        return townRepository.existsByTownName(townName);
    }

    @Override
    public TownEntity findByTownName(String townName) {
        return townRepository.findByTownName(townName);
    }

    @Override
    public TownEntity saveTown(TownEntity town) {
        return townRepository.save(town);
    }
}
