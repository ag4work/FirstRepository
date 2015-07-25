package service.DTO;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Set;

/**
 * Created by Alexey on 23.06.2015.
 */
public class UserDTO {

    private Integer userId;

    @NotNull(message = "Имя должно быть задано.")
    @Size(min = 2, max = 30, message = "Длина имени должна быть от 2 до 30 букв.")
    private String name;

    @NotNull(message= "Фамилия должна быть задана.")
    @Size(min = 2, max = 30, message="Длина фамилии должна быть от 2 до 30 букв.")
    private String lastname;

    @NotNull(message="Дата рождения должна быть задана.")
    private Date birthday;

    @NotNull(message= "Паспортные данные должны быть заданы.")
    @Size(min = 6, max = 110, message="Длина паспортных данных должна быть от 6 до 110 букв.")
    private String passport;

    @NotNull(message= "Адрес должен быть задан.")
    @Size(min = 2, max = 110, message="Длина адреса должна быть от 2 до 110 букв.")
    private String address;
    @NotNull(message="Имэйл должен быть задан")
    @Pattern(regexp = "^(?:[a-zA-Z0-9_'^&/+-])+(?:\\.(?:[a-zA-Z0-9_'^&/+-])+)" +
            "*@(?:(?:\\[?(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?))\\.)" +
            "{3}(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\]?)|(?:[a-zA-Z0-9-]+\\.)" +
            "+(?:[a-zA-Z]){2,}\\.?)$",
            message = "Заданный email не может существовать.")
    private String email;

    @NotNull(message= "Пароль должен быть задан.")
    @Size(min = 3, max = 25, message="Длина пароля должна быть от 3 до 40 букв.")
    private String password;

//    @NotNull(message= "Категория пользователя должна быть задана")
//    @Size(min = 1, max = 1, message="Категория ползователя: либо 0, либо 1")
    private int role;

    private Set<ContractDTO> contracts;


    public UserDTO() {

    }

    public UserDTO(Integer userId, String name, String lastname, Date birthday,
                   String passport, String address, String email,
                   String password, Integer role) {
        this.userId = userId;
        this.name = name;
        this.lastname = lastname;
        this.birthday = birthday;
        this.passport = passport;
        this.address = address;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getLastname() {
        return lastname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public String getPassport() {
        return passport;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Integer getRole() {
        return role;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public void setContracts(Set<ContractDTO> contracts) {
        this.contracts = contracts;
    }

    public Set<ContractDTO> getContracts() {
        return contracts;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", lastname='" + lastname + '\'' +
                '}';
    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        UserDTO userDTO = (UserDTO) o;
//
//        if (!birthday.equals(userDTO.birthday)) return false;
//        if (!lastname.equals(userDTO.lastname)) return false;
//        if (!name.equals(userDTO.name)) return false;
//
//        return true;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = name.hashCode();
//        result = 31 * result + lastname.hashCode();
//        result = 31 * result + birthday.hashCode();
//        return result;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserDTO userDTO = (UserDTO) o;

        if (birthday != null ? !birthday.equals(userDTO.birthday) : userDTO.birthday != null) return false;
        if (lastname != null ? !lastname.equals(userDTO.lastname) : userDTO.lastname != null) return false;
        if (name != null ? !name.equals(userDTO.name) : userDTO.name != null) return false;
        if (passport != null ? !passport.equals(userDTO.passport) : userDTO.passport != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (lastname != null ? lastname.hashCode() : 0);
        result = 31 * result + (birthday != null ? birthday.hashCode() : 0);
        result = 31 * result + (passport != null ? passport.hashCode() : 0);
        return result;
    }
}
