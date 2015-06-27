package utils;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * Created by Alexey on 28.06.2015.
 */
public class ValidationUtil {
    private static ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
    private static Validator validator = vf.getValidator();

    private ValidationUtil(){

    }
    public static Validator getValidatorInstance(){
        return validator;
    }

    public static String validateResult(Object object) {
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(object);

        String s = "";
        for (ConstraintViolation<Object> cv : constraintViolations)
            s = s + " " + cv.getMessage();
        return s;
    }

}
