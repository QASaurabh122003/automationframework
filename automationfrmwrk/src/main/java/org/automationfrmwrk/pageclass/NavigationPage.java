package org.automationfrmwrk.pageclass;

import org.automationfrmwrk.base.basePage;
import org.automationfrmwrk.utilities.Util;
import org.openqa.selenium.WebDriver;

public class NavigationPage extends basePage {



    public NavigationPage(WebDriver driver){
        super(driver);
        this.driver=driver;
    }

public WebDriver driver;
private String URL="https://www.letskodeit.com/courses";
private String allCoursesLink="xpath=>//a[@href='/courses']";
private String myCoursesLink="xpath=>//a[@href='/mycourses']";
private String ACCOUNT_DROPDOWN="id=>dropdownMenu1";
private String LOG_OUTLINK="xpath=>//a[@href='/logout']";
private String LOGIN_LINK = "xpath=>//a[@href='/login']";



public void allCourses(){
    //WebElement allCourses= driver.findElement(By.xpath(allCoursesLink));
    //allCourses.click();
    elementClick(allCoursesLink,"All Courses Link",10);


}
public void myCourses(){
//    WebElement myCourses=driver.findElement(By.xpath(myCoursesLink));
//    myCourses.click();

    elementClick(myCoursesLink,"My Course Link",10);

}
    public boolean isLoggedIn() {
        return isElementPresent(ACCOUNT_DROPDOWN, "DropDown");
    }

        public void logOut(){

//      driver.findElement(By.id(ACCOUNT_DROPDOWN)).click();
        elementClick(ACCOUNT_DROPDOWN,"Account Dropdown",10);
//        WebDriverWait wait =new WebDriverWait(driver,10);
//        WebElement logoutlink= wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.xpath(LOG_OUTLINK))));
//        logoutlink.click();
        waitForElement(LOG_OUTLINK,10);
        elementClick(LOG_OUTLINK,"LogoutLink",10);

    }

    public LoginPage loginPage() {
//        driver.findElement(By.xpath(LOGIN_LINK)).click();
        elementClick(LOGIN_LINK,"Login",20);
        return  new LoginPage(driver);
    }

    public boolean verifyHeadder(){
  /*   WebElement Link =driver.findElement(By.xpath(allCoursesLink));
     String text=Link.getText();
      return   Util.verifyTextContains(text,"All Courses");


*/
        String text=getText(allCoursesLink,"All Courses Link");
        return   Util.verifyTextContains(text,"All Courses");
    }

}

