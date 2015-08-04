package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Created by Alexey on 28.07.2015.
 */
public class NewTariff {

    @Size(min = 1, max = 40, message = "Название тарифа должно состоять из от {2} до {1}  букв")
    @NotNull()
    String title;

    @Pattern(regexp = "[1-9][0-9]{0,3}", message = "Ежемесячная плата должна "
            +"быть целым положительным цислом от 1 до 9999")
    String price;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
