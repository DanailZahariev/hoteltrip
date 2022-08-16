package bg.hoteltrip.service;

import bg.hoteltrip.model.entity.PictureEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface PictureService {

    void savePicture(PictureEntity picture);

    void deletePicture(String id);

    PictureEntity createPictureEntity(MultipartFile picture) throws IOException;
}
