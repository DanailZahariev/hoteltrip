package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.repository.TownRepository;
import bg.hoteltrip.service.TownService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TownServiceImplTest {

    @Mock
    private TownRepository townRepository;

    private TownService townService;


    @BeforeEach
    void setUp() {
        townService = new TownServiceImpl(townRepository);
    }

    @AfterEach
    void tearDown() {
        townRepository.deleteAll();
    }


    @Test
    void findByTownName_ShouldReturnCorrect() {

        TownEntity town = new TownEntity();
        town.setTownName("Sofia").setId(1L);

        when(townRepository.findByTownName("Sofia")).thenReturn(town);

        TownEntity toTest = townService.findByTownName(town.getTownName());

        Assertions.assertEquals(toTest.getTownName(), town.getTownName());

    }
}
