package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.repository.PictureRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureService(
            PictureRepository pictureRepository,
            CloudinaryService cloudinaryService) {

        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    public void savePicture(PictureEntity picture) {
        pictureRepository.saveAndFlush(picture);
    }

    public void deletePicture(String id) {
        pictureRepository.deleteByPublicId(id);
    }

    public PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        final CloudinaryImage upload = cloudinaryService.upload(picture);

        PictureEntity image = new PictureEntity();

        image.setPublicId(upload.getPublicId());
        image.setTittle(picture.getName());
        image.setUrl(upload.getUrl());

        return image;
    }
}


