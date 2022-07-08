package bg.hoteltrip.util.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmail, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return false;
    }
}
