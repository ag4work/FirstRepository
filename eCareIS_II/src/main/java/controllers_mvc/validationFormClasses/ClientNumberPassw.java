package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 26.07.2015.
 */
public class ClientNumberPassw {


    @Size(min = 10, max = 10, message = "Длина номера должна быть от  {1} цифр")
    @NotNull
    String phoneNumber;


    @Size(min = 1, max = 30, message = "Длина пароля должна быть от  {2} до {1} символов")
    @NotNull
    String password;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
