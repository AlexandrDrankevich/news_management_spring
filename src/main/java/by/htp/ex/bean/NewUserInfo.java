package by.htp.ex.bean;

import java.io.Serializable;
import java.util.Objects;

public class NewUserInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private String surname;
    private String login;
    private String password;
    private String birthday;

    public NewUserInfo() {
    }

    public NewUserInfo(String name, String surname, String login, String password, String birthday) {
        this.name = name;
        this.surname = surname;
        this.login = login;
        this.password = password;
        this.birthday = birthday;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewUserInfo that = (NewUserInfo) o;
        return Objects.equals(name, that.name) && Objects.equals(surname, that.surname) && Objects.equals(login, that.login)
                && Objects.equals(password, that.password) && Objects.equals(birthday, that.birthday);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, login, password, birthday);
    }

    @Override
    public String toString() {
        return "NewUserInfo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", birthday='" + birthday + '\'' +
                '}';
    }
}

