package utils;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

/**
 * Created by Alexey on 28.06.2015.
 */

//class for date format validation check
public class DateValidationUtilString {

    @NotNull(message="Дата рождения должна быть задана.")
    @Pattern(regexp = "^[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])$",
            message = "Дата введена неверно.")
    private String date;

    public DateValidationUtilString(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
