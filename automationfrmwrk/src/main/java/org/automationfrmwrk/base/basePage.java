package org.automationfrmwrk.base;

import org.openqa.selenium.WebDriver;

public class basePage extends CustomerDriver {

    WebDriver driver;
    public basePage(WebDriver driver){
        super(driver);
        this.driver=driver;

    }

    public boolean verifyTitle(String expectedTitle){
        return driver.getTitle().equalsIgnoreCase(expectedTitle);
    }
}
