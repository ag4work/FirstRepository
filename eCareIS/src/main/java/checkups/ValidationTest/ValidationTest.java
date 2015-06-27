
package checkups.ValidationTest;
import service.DTO.UserDTO;
import utils.ValidationUtil;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
/**
 * Created by Alexey on 27.06.2015.
 */
public class ValidationTest {
    public static void main(String[] args) {
        TestBean testBean = new TestBean();
        testBean.setA(null);

        ValidatorFactory factory;
        Validator validator;


        factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        System.out.println(validator.validate(testBean));
        System.out.println(ValidationUtil.validateResult(new UserDTO()));




    }
}

