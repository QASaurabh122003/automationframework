package org.automationfrmwrk.pageclass;

import org.automationfrmwrk.base.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CategoryFilterPage extends basePage {

    public CategoryFilterPage(WebDriver driver) {
        super(driver);
        this.driver = driver;

    }

    public WebDriver driver;
    private String CATEGORY_LIST = "xpath=>//select[@name='categories']/option[@value]";
    private String CATERGORY_COURSES_LIST = "xpath=>//div[@class='col-lg-4 col-md-4 col-sm-6 col-xs-12']";
    private String CATEGOR_YNAME="xpath=>//h1[contains(@class, 'dynamic-heading') and contains(text(), 'Category :')]";


    public ResultPage select(int categoryindexvalue) {
        //List<WebElement> categorylist = driver.findElements(By.xpath(CATEGORY_LIST));
        List<WebElement> categorylist=getElementslist(CATEGORY_LIST,"Category List",10);
        if(categoryindexvalue>=0 && categoryindexvalue<categorylist.size()){
            categorylist.get(categoryindexvalue).click();
        } else{
            throw new IndexOutOfBoundsException("Invaild Category Index"+categoryindexvalue);
        }
        return new ResultPage(driver);
    }

    public void verifycategoryResult(){
//        WebElement catergoryPage= driver.findElement(By.xpath(CATEGOR_YNAME));
       String fullText= getText(CATEGOR_YNAME,"category Text");
//        String fullText=catergoryPage.getText();
       String actualcategory=fullText.replace("Category :","").trim();
      System.out.println("ActualCategory "+actualcategory);


    }
    public int findCoursesCount(){
        List<WebElement> coursesCount= getElementslist(CATERGORY_COURSES_LIST,"Category Courses List",10);
        // List<WebElement> coursesCount=driver.findElements(By.xpath(CATERGORY_COURSES_LIST));
        int Countcousre=coursesCount.size();
        return Countcousre;
    }
    public long CategorrycoursesCount(){

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Long totalCoursesValue = (Long) jsExecutor.executeScript("return total_courses;");

        System.out.println("Total Courses: " + totalCoursesValue);
        return totalCoursesValue;


    }

}

