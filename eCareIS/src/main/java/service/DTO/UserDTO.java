package service.DTO;


import entity.User;

import java.util.Date;
import java.util.Set;

/**
 * Created by Alexey on 23.06.2015.
 */
public class UserDTO {
    private Integer userId;
    private String name;
    private String lastname;
    private Date birthday;
    private String passport;
    private String address;
    private String email;
    private String password;
    private Integer role;
//    private Set<Contract> contracts;


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

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                ", lastname='" + lastname + '\'' +
                '}';
    }
}
