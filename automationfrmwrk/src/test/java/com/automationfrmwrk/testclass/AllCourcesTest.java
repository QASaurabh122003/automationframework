package com.automationfrmwrk.testclass;

import com.automationfrmwrk.testclass.base.BaseTest;
import org.automationfrmwrk.pageclass.CategoryFilterPage;
import org.automationfrmwrk.pageclass.NavigationPage;
import org.automationfrmwrk.pageclass.SearchBarPage;
import org.automationfrmwrk.utilities.Constants;
import org.automationfrmwrk.utilities.ExcelUtiliy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AllCourcesTest extends BaseTest {


    @DataProvider(name="verifySearchCourseData")
    public Object[][] getVerifySearchCourseData(){
        Object[][] testData= ExcelUtiliy.getTestData("verify_search_course");
        System.out.println(testData);
        return testData;
    }

    @BeforeClass
    public void setup() throws InterruptedException {

        nav=login.signWith(Constants.DEFAULT_USERNAME,Constants.DEFAULT_PASSWORD);
        wait(200);
        ExcelUtiliy.setExcelFile(Constants.EXCEL_FILE,"AllCoursesTests");
    }

    @Test(dataProvider = "verifySearchCourseData")
    public void  verifySearchCourse(String courseName) throws InterruptedException {

        if (!nav.isLoggedIn()) {
            // Login with default credentials if not logged in
            nav = login.signWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        }
        nav.allCourses();
        search = new SearchBarPage(driver);
        System.out.println("ExcelDAta"+courseName);
        result=search.course(courseName);
        boolean searchResult=result.verifySearchResult();
        System.out.println("TestPage "+searchResult);
        Assert.assertTrue(searchResult);
    }
    @Test(enabled = false)
    public void verifyfilterByCategory(){
        nav =new NavigationPage(driver);
        nav.allCourses();
        category=new CategoryFilterPage(driver);
        result =category.select(2);
        category.verifycategoryResult();
        int count =category.findCoursesCount();
        System.out.println("element count"+count);
        boolean filterResult=result.verifyFilterCourseCount();
        Assert.assertTrue(filterResult);


    }

    @AfterClass
    public void tearDown(){
    }


}
