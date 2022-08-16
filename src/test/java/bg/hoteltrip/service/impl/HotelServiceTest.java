package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.repository.HotelRepository;
import bg.hoteltrip.repository.PictureRepository;
import bg.hoteltrip.repository.RoomRepository;
import bg.hoteltrip.repository.TownRepository;
import bg.hoteltrip.service.*;
import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class HotelServiceTest {

    @Mock
    private HotelRepository hotelRepository;

    @Mock
    private RoomRepository roomRepository;

    @Mock
    private PictureService pictureService;

    @Mock
    private HotelService hotelService;

    @Mock
    private RoomService roomService;

    @Mock
    private PictureRepository pictureRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private TownService townService;

    @Mock
    private CloudinaryService cloudinaryService;
    @Mock
    private Cloudinary cloudinary;

    @Mock
    private TownRepository townRepository;


    @BeforeEach
    void setUp() {
        modelMapper = new ModelMapper();
        roomService = new RoomServiceImpl(roomRepository, modelMapper);
        townService = new TownServiceImpl(townRepository);
        cloudinaryService = new CloudinaryServiceImpl(cloudinary);
        pictureService = new PictureServiceImpl(pictureRepository, cloudinaryService);
        hotelService = new HotelServiceImpl(hotelRepository, modelMapper, townService, roomService,
                pictureService);
    }


    @Test
    void saveHotel() {

        TownEntity town = new TownEntity()
                .setTownName("Sofia");
        townRepository.save(town);

        HotelEntity hotel = new HotelEntity()
                .setHotelName("Hotel Sofia")
                .setTown(town)
                .setDescription("some description");

        when(hotelService.findHotelByName("Hotel Sofia")).thenReturn(hotel);

        HotelEntity toTest = hotelRepository.findByHotelName(hotel.getHotelName());

        Assertions.assertEquals(toTest.getHotelName(), hotel.getHotelName());

    }
}
