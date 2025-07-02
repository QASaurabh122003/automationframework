package com.automationfrmwrk.testclass;


import com.automationfrmwrk.testclass.base.BaseTest;
import com.automationfrmwrk.testclass.base.CheckPoint;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {


    @BeforeClass
    public void setup() {

    }
    @AfterMethod
    public void afterMethod(){
       if(nav.isLoggedIn()) {
           nav.logOut();
           nav.loginPage();
       }

    }
    @Test
    public void signWithValidCredentials(){
        nav = login.signWith("test@email.com", "abcabc");
        boolean headerResult = nav.verifyHeadder();
        CheckPoint.mark("test01",headerResult,"HeaderVerification");
        boolean result=nav.isLoggedIn();
        CheckPoint.markFinal("test01",result,"login verification");



//        if (result) {
//            System.out.println("This test case passed");
//        } else {
//            System.out.println("This test case failed");
//        }
//
//        // Assert to ensure the test fails when `result` is false
//        Assert.assertTrue(result, "Verification of the header failed. User might not be logged in.");



    }
    @Test(enabled = false)
    public void signWithInvalidCred(){
        nav = login.signWith("test@email", "abcabc");
        boolean result= nav.isLoggedIn();
        if (result==false) {
            System.out.println("This test case passed");
        }
        else {
            System.out.println("This test case failed");
        }

        // Assert to ensure the test fails when `result` is false
        Assert.assertFalse(result, "Verification of the header failed. User might not be logged in.");


    }
    @AfterClass
    public void teardownScript(){
        driver.quit();
    }
}

