package checkups.ValidationTest;

/**
 * Created by Alexey on 27.06.2015.
 */

        import java.util.Set;

        import javax.validation.ConstraintViolation;
        import javax.validation.Validation;
        import javax.validation.Validator;
        import javax.validation.ValidatorFactory;
        import javax.validation.constraints.NotNull;
        import javax.validation.constraints.Pattern;
        import javax.validation.constraints.Size;

public class User {

    @NotNull(message="Имя должно быть задано")
    String firstname;

    @NotNull(message="Фамилия должна быть задана")
    @Size(min = 3, message="Длина фамилии должна быть больше трех")
    String lastname;

    @NotNull(message="Имэйл должен быть задан")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "заданный имэйл не может существовать")
    String email;

    @Override
    public String toString() {
        return String.format("firstname: [%s], lastname: [%s], email: [%s]",
                firstname, lastname, email);
    }

//    public static void validate(Object object, Validator validator) {
//        Set<ConstraintViolation<Object>> constraintViolations = validator
//                .validate(object);
//
//        System.out.println(object);
//        System.out.println(String.format("Кол-во ошибок: %d",
//                constraintViolations.size()));
//
//        for (ConstraintViolation<Object> cv : constraintViolations)
//            System.out.println(String.format(
//                    "Внимание, ошибка! property: [%s], value: [%s], message: [%s]",
//                    cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
//    }

    public static String validate(Object object, Validator validator) {
        Set<ConstraintViolation<Object>> constraintViolations = validator
                .validate(object);

        String s = "";
        for (ConstraintViolation<Object> cv : constraintViolations)
            s = s + " " + cv.getMessage();
        return s;
    }


    public static void main(String[] args) {

        ValidatorFactory vf = Validation.buildDefaultValidatorFactory();
        Validator validator = vf.getValidator();

        User user = new User();
      //  System.out.println(validate(user, validator));

        user.firstname = "Вася";
     //   System.out.println(validate(user, validator));

//        user.lastname = "Пу";
//        validate(user, validator);
//
        user.lastname = "Пупкин";
//        validate(user, validator);
//
//        user.email = "вася пупкин@example.com";
//        validate(user, validator);
//
        user.email = "vasya.poupkine@example.com";
        String s = validate(user, validator);
        System.out.println(s);
        if (s==null) System.out.println("the null");
        if (s.isEmpty()) System.out.println("empty");
        int a = 2;

    }

}
