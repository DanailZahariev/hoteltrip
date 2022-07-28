package bg.hoteltrip.util.validation;


import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.io.IOException;
import java.io.InputStream;

public class PictureValidator implements ConstraintValidator<ValidPicture, MultipartFile> {


    @Override
    public void initialize(ValidPicture constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile picture, ConstraintValidatorContext context) {
        return validatePicture(picture);
    }

    private boolean validatePicture(MultipartFile picture) {
        InputStream inputStream = null;

        try {
            inputStream = picture.getInputStream();
        } catch (IOException e) {
            return false;
        }
        return true;
    }
}