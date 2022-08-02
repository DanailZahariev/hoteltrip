package bg.hoteltrip.service;

import bg.hoteltrip.model.binding.HotelAddBindingModel;
import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.repository.HotelRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final RoomService roomService;
    private final PictureService pictureService;

    public HotelService(HotelRepository hotelRepository,
                        ModelMapper modelMapper,
                        TownService townService,
                        RoomService roomService,
                        PictureService pictureService) {
        this.hotelRepository = hotelRepository;
        this.modelMapper = modelMapper;
        this.townService = townService;
        this.roomService = roomService;
        this.pictureService = pictureService;
    }

    public List<HotelViewModel> findHotelByTownName(String townName) {
        return hotelRepository.findHotelEntitiesByTown_TownName(townName)
                .stream()
                .map(hotelEntity -> modelMapper.map(hotelEntity, HotelViewModel.class))
                .collect(Collectors.toList());
    }

    public void addHotelEntity(HotelAddBindingModel hotelAddBindingModel) throws IOException {

        HotelEntity hotel = modelMapper.map(hotelAddBindingModel, HotelEntity.class);

        boolean townExist = townService.existTown(hotelAddBindingModel.getTownName());
        TownEntity existingHotel = townService.findBytownName(hotelAddBindingModel.getTownName());

        if (townExist) {
            hotel.setTown(existingHotel);
        } else {
            TownEntity town = new TownEntity();
            town.setTownName(hotelAddBindingModel.getTownName());
            TownEntity newTown = townService.saveTown(town);
            hotel.setTown(newTown);
        }

        RoomEntity apartment = roomService.findByRoomType(RoomTypeEnum.APARTMENT);
        RoomEntity studio = roomService.findByRoomType(RoomTypeEnum.STUDIO);
        RoomEntity doubleRoom = roomService.findByRoomType(RoomTypeEnum.DOUBLE_ROOM);
        List<RoomEntity> rooms = new ArrayList<>();

        for (int i = 0; i < hotelAddBindingModel.getApartments(); i++) {
            rooms.add(apartment);
        }
        for (int i = 0; i < hotelAddBindingModel.getStudioRooms(); i++) {
            rooms.add(studio);
        }
        for (int i = 0; i < hotelAddBindingModel.getDoubleRooms(); i++) {
            rooms.add(doubleRoom);
        }

        hotel.setRooms(rooms);

        List<PictureEntity> pictureEntities = new ArrayList<>();
        for (MultipartFile hotelPicture : hotelAddBindingModel.getHotelPictures()) {
            PictureEntity hotelPic = pictureService.createPictureEntity(hotelPicture);
            pictureService.savePicture(hotelPic);
            pictureEntities.add(hotelPic);
            hotel.setHotelPictures(pictureEntities);
        }

        hotelRepository.save(hotel);
    }
}
