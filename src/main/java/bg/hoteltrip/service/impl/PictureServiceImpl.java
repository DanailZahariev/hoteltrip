package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.repository.PictureRepository;
import bg.hoteltrip.service.CloudinaryImage;
import bg.hoteltrip.service.CloudinaryService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements bg.hoteltrip.service.PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryService cloudinaryService;

    public PictureServiceImpl(
            PictureRepository pictureRepository,
            CloudinaryService cloudinaryService) {

        this.pictureRepository = pictureRepository;
        this.cloudinaryService = cloudinaryService;
    }

    @Override
    public void savePicture(PictureEntity picture) {
        pictureRepository.saveAndFlush(picture);
    }

    @Override
    public void deletePicture(String id) {
        pictureRepository.deleteByPublicId(id);
    }

    @Override
    public PictureEntity createPictureEntity(MultipartFile picture) throws IOException {
        final CloudinaryImage upload = cloudinaryService.upload(picture);

        PictureEntity image = new PictureEntity();

        image.setPublicId(upload.getPublicId());
        image.setUrl(upload.getUrl());

        return image;
    }
}


