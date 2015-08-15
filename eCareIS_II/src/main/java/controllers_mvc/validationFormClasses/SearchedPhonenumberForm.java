package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 04.08.2015.
 */
public class SearchedPhonenumberForm {

    @Size(min = 10, max = 10, message = "Номер должен состоять из {1} цифр")
    @NotNull
    private String number;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}