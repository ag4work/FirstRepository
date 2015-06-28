package checkups.ValidationTest;

import utils.DateValidationUtilString;
import utils.ValidationUtil;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

/**
 * Created by Alexey on 28.06.2015.
 */
public class DateFormatCheckTest {

    public static void main(String[] args) {

        ValidatorFactory factory;
        Validator validator;


        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        String s = "1982-12-21";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "1982-12-1";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "198-11-33";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "198-33-21";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "1989-3-21";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "198-03-21";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));
        s = "1985-003-21";
        System.out.println(s + ValidationUtil.validateResult(new DateValidationUtilString(s)));




    }
}