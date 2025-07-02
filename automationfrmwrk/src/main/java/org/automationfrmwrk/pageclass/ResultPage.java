package org.automationfrmwrk.pageclass;

import org.automationfrmwrk.base.basePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ResultPage extends basePage {


    public ResultPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    WebDriver driver;
    private String URL = "?query=";
    private String COURSES_LIST = "xpath=>//div[@class='col-lg-4 col-md-4 col-sm-6 col-xs-12']";


    public boolean isOpen() {
        /*return driver.getCurrentUrl().contains(URL);*/
        return getCurrentUrl().contains(URL);

    }


    public int coursesCount() {
        List<WebElement> coursesList =getElementslist(COURSES_LIST,"Course List",10);

        System.out.println("size   " + coursesList.size());
        int searchcoursecount = coursesList.size();
        return searchcoursecount;
    }

    public boolean verifyFilterCourseCount() {
      CategoryFilterPage resultdropdown=new CategoryFilterPage(driver);
      long expeectedCount= resultdropdown.CategorrycoursesCount();
      int actualCount=resultdropdown.findCoursesCount();
      boolean result=false;
        if (expeectedCount==(actualCount));
        result = true;

        return result;
    }


    public boolean verifySearchResult() {
        boolean result = false;
        if (coursesCount() > 0) {
            result = true;

        }
        System.out.println("testclass:->" + result);
        return result;
    }
}



