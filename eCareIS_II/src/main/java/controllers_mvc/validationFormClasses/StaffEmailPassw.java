package controllers_mvc.validationFormClasses;

import org.hibernate.validator.constraints.Email;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 26.07.2015.
 */
public class StaffEmailPassw {
    @NotNull(message = "Email должен быть задан")
    @Size(min = 5, max = 30, message = "Длина email должна быть от  {2} до {1} символов")
    String email;
    @Size(min = 1, max = 30, message = "Длина пароля должна быть от  {2} до {1} символов")
    @NotNull
    String pass;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
