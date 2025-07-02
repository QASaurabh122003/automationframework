package com.automationfrmwrk.testclass.base;

import org.automationfrmwrk.pageclass.*;
import org.automationfrmwrk.utilities.Constants;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;

public class BaseTest {
// Implement common steps for other test classes and provide methods to be inherited

    public WebDriver driver;
    protected String  baseURL;
    protected CategoryFilterPage category;
    protected SearchBarPage search;
    protected LoginPage login;
    protected NavigationPage nav;
    protected ResultPage result;

    @BeforeClass
    public void commonsetup(){
        /*ChromeOptions option = new ChromeOptions();
        option.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "/Users/saurabh16.pandey/Library/CloudStorage/OneDrive-RelianceCorporateITParkLimited/Desktop/Selenium/chromedriver-mac-arm64/chromedriver");
        driver = new ChromeDriver();*/
//        driver = WebDriverFactory.getInstance().getDriver("chrome");
//        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
//        driver.manage().window().maximize();
//        baseURL = Constants.BASE_URL;
//        driver.get(baseURL);
//        nav = new NavigationPage(driver);
//        login = nav.loginPage();

        driver = WebDriverFactory.getInstance().getDriver("chrome");

        baseURL = Constants.BASE_URL;
        driver.get(baseURL);
        nav = new NavigationPage(driver);
        login = nav.loginPage();

    }

    @BeforeMethod
    public void methodSetUp() {
        CheckPoint.clearHashMap();
    }
    @AfterClass
    public void commontearDown(){
        WebDriverFactory.getInstance().quitDriver();
    }


}
