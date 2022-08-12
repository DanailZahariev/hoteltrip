package bg.hoteltrip.util.validation;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;

public class PictureValidator implements ConstraintValidator<ValidPicture, MultipartFile> {


    @Override
    public boolean isValid(MultipartFile file, ConstraintValidatorContext context) {
        return validateFile(file);
    }

    private boolean validateFile(MultipartFile file) {

        InputStream inputStream = null;

        try {
            inputStream = file.getInputStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}
