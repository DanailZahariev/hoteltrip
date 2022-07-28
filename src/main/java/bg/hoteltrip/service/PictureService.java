package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.repository.PictureRepository;
import org.springframework.stereotype.Service;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;

    public PictureService(
            PictureRepository pictureRepository) {

        this.pictureRepository = pictureRepository;
    }

    public void savePicture(PictureEntity picture) {
        pictureRepository.saveAndFlush(picture);
    }

    public void deletePicture(String id) {
        pictureRepository.deleteByPublicId(id);
    }
}


