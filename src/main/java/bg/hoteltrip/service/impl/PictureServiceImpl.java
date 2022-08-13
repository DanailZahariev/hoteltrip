package bg.hoteltrip.service.impl;

import bg.hoteltrip.model.entity.PictureEntity;
import bg.hoteltrip.repository.PictureRepository;
import bg.hoteltrip.service.CloudinaryImage;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class PictureServiceImpl implements PictureService {

    private final PictureRepository pictureRepository;
    private final CloudinaryServiceImpl cloudinaryServiceImpl;

    public PictureServiceImpl(
            PictureRepository pictureRepository,
            CloudinaryServiceImpl cloudinaryServiceImpl) {

        this.pictureRepository = pictureRepository;
        this.cloudinaryServiceImpl = cloudinaryServiceImpl;
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
        final CloudinaryImage upload = cloudinaryServiceImpl.upload(picture);

        PictureEntity image = new PictureEntity();

        image.setPublicId(upload.getPublicId());
        image.setUrl(upload.getUrl());

        return image;
    }
}


