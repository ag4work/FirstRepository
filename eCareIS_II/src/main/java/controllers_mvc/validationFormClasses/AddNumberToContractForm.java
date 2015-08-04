package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 05.08.2015.
 */
public class AddNumberToContractForm {

    @NotNull
    @Size(min = 10, max = 10, message = "Длина номера должна быть {1} цифр")
    private String phoneNumber;

    @NotNull
    @Pattern(regexp = "[1-9][0-9]{0,3}", message = "Начальный баланс должен"
            +"быть целым положительным цислом от 1 до 9999")
    private String initialBalance;

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getInitialBalance() {
        return initialBalance;
    }

    public void setInitialBalance(String initialBalance) {
        this.initialBalance = initialBalance;
    }
}
