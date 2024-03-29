package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.binding.HotelAddBindingModel;
import bg.hoteltrip.model.entity.HotelEntity;
import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.model.entity.RoomEntity;
import bg.hoteltrip.model.entity.TownEntity;
import bg.hoteltrip.model.entity.enums.RoomTypeEnum;
import bg.hoteltrip.model.view.HotelViewModel;
import bg.hoteltrip.repository.HotelRepository;
import bg.hoteltrip.service.HotelService;
import bg.hoteltrip.service.PictureService;
import bg.hoteltrip.service.RoomService;
import bg.hoteltrip.service.TownService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelServiceImpl implements HotelService {

    private final HotelRepository hotelRepository;
    private final ModelMapper modelMapper;
    private final TownService townService;
    private final RoomService roomService;
    private final PictureService pictureService;

    public HotelServiceImpl(HotelRepository hotelRepository,
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

    @Override
    public List<HotelViewModel> findHotelByTownName(String townName) {
        return hotelRepository.findHotelEntitiesByTown_TownName(townName)
                .stream()
                .map(hotelEntity -> modelMapper.map(hotelEntity, HotelViewModel.class))
                .collect(Collectors.toList());
    }

    public HotelEntity findByHotelName(String name) {
        return hotelRepository.findByHotelName(name);
    }

    @Override
    public HotelEntity findHotelByName(String name) {
        return hotelRepository.findByHotelName(name);
    }

    @Override
    public void addHotelEntity(HotelAddBindingModel hotelAddBindingModel) throws IOException {

        HotelEntity hotel = modelMapper.map(hotelAddBindingModel, HotelEntity.class);

        boolean townExist = townService.existTown(hotelAddBindingModel.getTownName());
        TownEntity existingTown = townService.findByTownName(hotelAddBindingModel.getTownName());

        if (townExist) {
            hotel.setTown(existingTown);
        } else {
            TownEntity town = new TownEntity();
            town.setTownName(hotelAddBindingModel.getTownName());
            TownEntity newTown = townService.saveTown(town);
            hotel.setTown(newTown);
        }

        List<PictureEntity> pictureEntities = new ArrayList<>();
        for (MultipartFile hotelPicture : hotelAddBindingModel.getHotelPictures()) {
            PictureEntity hotelPic = pictureService.createPictureEntity(hotelPicture);
            pictureService.savePicture(hotelPic);
            pictureEntities.add(hotelPic);
            hotel.setHotelPictures(pictureEntities);
        }


        List<RoomEntity> rooms = new ArrayList<>();

        for (int i = 0; i < hotelAddBindingModel.getApartments(); i++) {

            RoomEntity apartment = new RoomEntity().setHotel(hotel)
                    .setPrice(BigDecimal.valueOf(199, 0))
                    .setRoomType(RoomTypeEnum.APARTMENT);

            apartment = roomService.saveRoom(apartment);

            rooms.add(apartment);
            hotel.setRooms(rooms);
            hotelRepository.save(hotel);

        }

        for (int i = 0; i < hotelAddBindingModel.getStudioRooms(); i++) {

            RoomEntity studio = new RoomEntity().setHotel(hotel)
                    .setPrice(BigDecimal.valueOf(99, 0))
                    .setRoomType(RoomTypeEnum.STUDIO);

            studio = roomService.saveRoom(studio);

            rooms.add(studio);
            hotel.setRooms(rooms);
            hotelRepository.save(hotel);


        }

        for (int i = 0; i < hotelAddBindingModel.getDoubleRooms(); i++) {

            RoomEntity doubleRoom = new RoomEntity().setHotel(hotel)
                    .setPrice(BigDecimal.valueOf(129, 0))
                    .setRoomType(RoomTypeEnum.DOUBLE_ROOM);

            doubleRoom = roomService.saveRoom(doubleRoom);

            rooms.add(doubleRoom);
            hotel.setRooms(rooms);
            hotelRepository.save(hotel);

        }

        hotelRepository.save(hotel);
    }
}