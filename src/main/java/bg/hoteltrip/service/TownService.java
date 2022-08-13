package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.TownEntity;

public interface TownService {

    boolean existTown(String townName);

    TownEntity findByTownName(String townName);

    TownEntity saveTown(TownEntity town);
}
