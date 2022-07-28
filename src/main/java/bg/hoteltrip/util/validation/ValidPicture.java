package bg.hoteltrip.util.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Target({FIELD,ANNOTATION_TYPE, TYPE})
@Constraint(validatedBy = PictureValidator.class)
public @interface ValidPicture {

    String message() default "Please upload a profile picture!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
