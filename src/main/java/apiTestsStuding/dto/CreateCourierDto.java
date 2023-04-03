package apiTestsStuding.dto;



import java.util.ArrayList;
import java.util.List;

import static apiTestsStuding.constants.Constants.BASE_URL;
import static apiTestsStuding.constants.Constants.PATH_COURIER_CREATION;



public class CreateCourierDto {

    private String login;
    private int password;
    private String firstName;

    public CreateCourierDto(String login, int password, String firstName) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierDto(int password, String firstName) {
        this.password = password;
        this.firstName = firstName;
    }

    public CreateCourierDto(String login, String firstName) {
        this.login = login;
        this.firstName = firstName;
    }

    public CreateCourierDto(String login, int password) {
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    //методы
}
