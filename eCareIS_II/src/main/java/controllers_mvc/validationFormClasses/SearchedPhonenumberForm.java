package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 04.08.2015.
 */
public class SearchedPhonenumberForm {

    @Size(min = 10, max = 10, message = "Длина номера должна быть {1} цифр")
    @NotNull
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}