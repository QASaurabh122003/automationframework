package org.automationfrmwrk.pageclass;

import org.automationfrmwrk.base.basePage;
import org.openqa.selenium.WebDriver;

public class LoginPage extends basePage {

    public LoginPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }


    public WebDriver driver;

    private String EMAIL_FIELD = "id=>email";
    private String PASSWORD_FIELD = "id=>login-password";
    private String LOGIN_BUTTON = "id=>login";

    public NavigationPage signWith(String email, String password) {
        sendData(EMAIL_FIELD,email,"Usernamefield");
        sendData(PASSWORD_FIELD,password,"Passwordfield");
        elementClick(LOGIN_BUTTON,"LoginButton");
        return new NavigationPage(driver);

    }
}

