package org.automationfrmwrk.pageclass;

import org.automationfrmwrk.base.basePage;
import org.automationfrmwrk.utilities.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class SearchBarPage extends basePage {

    public WebDriver driver;
    private String SearchIcon = "xpath=>//button[@class='find-course search-course']";
    private String SearchBar="id=>search";
    //private String SearchIcon = "//button[@class='find-course search-course']";
    //private String SearchBar="search";

    public SearchBarPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }


    public ResultPage course(String courseName) throws InterruptedException {
        Actions actions=new Actions(driver);

       /* WebElement searchBar= driver.findElement(By.id(SearchBar));
        actions.moveToElement(searchBar).click().sendKeys(courseName).perform();
        WebElement searchIcon= driver.findElement(By.xpath(SearchIcon));
        searchIcon.click();*/
//        actionsClick(SearchBar,"searchbarclick");
//        sendDataUsingJS(SearchBar,courseName,"CourseName");
//        waitForElement(SearchIcon,10);
//        Util.sleep(200);
//        javascriptClick(SearchIcon,"SearchBar Icon");
//        actionsClick(SearchIcon,"SearchIconClick");
//        Thread.sleep(200);
       // clickelement(SearchBar,"searchbar",10);
        actionsClick(SearchBar,"searchbar");
//        sendDataUsingaction(SearchBar, courseName, "Search Course Field");
        sendDataUsingaction(SearchBar,courseName,"Coursename");

        actionsClick(SearchIcon,"SearchIcon");


        return new ResultPage(driver);
        }
    }






