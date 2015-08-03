package controllers_mvc.validationFormClasses;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * Created by Alexey on 29.07.2015.
 */
public class NewUserForm {

    @NotNull(message = "Имя должно быть задано.")
    @Size(min = 2, max = 30, message = "Длина имени должна быть от 2 до 30 букв.")
    private String name;

    @NotNull(message = "Фамилия должна быть задана.")
    @Size(min = 2, max = 30, message = "Длина фамилии должна быть от 2 до 30 букв.")
    private String lastname;

    @NotNull(message = "Дата рождения должна быть задана.")
    @Pattern(regexp = "^\\d{2}-\\d{2}-\\d{4}$", message = "Формат даты: dd-mm-yyyy")
    private String birthday;

    @NotNull(message = "Паспортные данные должны быть заданы.")
    @Size(min = 6, max = 110, message = "Длина паспортных данных должна быть от 6 до 110 букв.")
    private String passport;

    @NotNull(message = "Адрес должен быть задан.")
    @Size(min = 2, max = 110, message = "Длина адреса должна быть от 2 до 110 букв.")
    private String address;
    @NotNull(message = "Имэйл должен быть задан")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "Заданный email не может существовать.")
    private String email;

    @NotNull(message = "Пароль должен быть задан.")
    @Size(min = 3, max = 25, message = "Длина пароля должна быть от 3 до 40 символов.")
    private String password;

    @NotNull(message = "Категория пользователя должна быть задана")
    @Pattern(regexp = "[0-1]", message = "Категория пользователя может быть либо 0, либо 1.")
    private String role;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}

