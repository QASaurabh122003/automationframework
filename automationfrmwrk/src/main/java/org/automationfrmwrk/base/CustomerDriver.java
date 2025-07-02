package org.automationfrmwrk.base;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.automationfrmwrk.utilities.Util;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.automationfrmwrk.utilities.Util.sleep;

public class CustomerDriver {
    private static final Logger log = LogManager.getLogger(CustomerDriver.class.getName());
    public WebDriver    driver;
    private JavascriptExecutor js;

    public CustomerDriver(WebDriver driver){
        this.driver=driver;
        js=(JavascriptExecutor)driver;
    }

    /**
     * This method rerfersh the current window
     */
    public void refresh(){
        driver.navigate().refresh();
        log.info("The current browser was refreshed");

    }

    /**
     *
     * @return
     * This Method return the title of the current page
     */
    public String  getTitle(){
        String title= driver.getTitle();
        log.info("The current title of the page is :  "+ title);
        return title;
    }

    /**
     * This Method the return the  URL of the Current Page
     * @return
     */

    public String getCurrentUrl(){
        String url= driver.getCurrentUrl();
        log.info("The Current Page URL is  "+ url);

        return url;
    }

    /**
     *This method will navigate you to pervious window of yours or will naviagate you back
     *
     */

    public void navigateBack(){
        driver.navigate().back();
        log.info("Navigate Back");
    }

    /**
     * Navigate Browser forward
     *
     */

    public void navigateForward(){
        driver.navigate().forward();
        log.info("Navigate Forward");
    }

    /***
     * This bytype will be used for the find the element by the given locator stratergy
     * @param locator-locator strategy  id=>example,css=>example xpath=>
     *
     *and returns the By Type
     */

    public By getBytpe(String locator){
        By by =null;
        String locatorType=locator.split("=>")[0];
        locator=locator.split("=>")[1];
        try{
            if(locatorType.contains("id")){
                by=By.id(locator);
            }
            else if(locatorType.contains("name")){
                    by=By.name(locator);
            } else if (locatorType.contains("xpath")) {
                    by=By.xpath(locator);
            } else if (locatorType.contains("css")) {
                    by=By.cssSelector(locator);
            } else if (locatorType.contains("class")) {
                    by=By.className(locator);
            }else if (locatorType.contains("tag")){
                    by=By.tagName(locator);
            } else if (locatorType.contains("link")) {
                    by=By.linkText(locator);
            } else if (locatorType.contains("partialLink")) {
                    by=By.partialLinkText(locator);
            }else{
                log.info("Locator Type is not supported");
            }
        } catch (Exception e) {
            log.error("By type not found with "+locatorType);
        }
        return by;
    }

    /**
     * Use by method and return element
     * @param locator
     * @param info
     * @return
     */

    public WebElement getElement(String locator,String info){
        WebElement element =null;
        By bytype=getBytpe(locator);
        try{
            element= driver.findElement(bytype);
        } catch (Exception e) {
            log.error("Element not found with locator"+locator);
            e.printStackTrace();
        }
        return element;
    }

    /**
     *This element will find the list using locator BYbtype methhod
     * @param locator
     * @param info
     * @return
     */

    public List<WebElement> getElementslist(String locator,String info,long timeToWait){
        List<WebElement> elementsList=null;
        By bytype=getBytpe(locator);
        try{
            elementsList=driver.findElements(bytype);
            if(elementsList.size()>0){
                log.info("Elements list found with :  "+locator);
            }else{
                log.info("Elements list not found with :  "+locator);
            }
        }
        catch (Exception e){
            log.error("Elements list not found with :  "+locator);
            e.printStackTrace();
        }
        return elementsList;
    }

    /**
     * Verifies if at least one element is present on the page using the specified locator and additional information.
     *
     * @param locator The locator strategy to find the element (e.g., id=>example, xpath=>example).
     * @param info Additional context or description about the locator.
     * @return true if one or more elements are found; false otherwise.
     */
    public List<WebElement> getCurrentElementslist(String locator,String info){
        List<WebElement> elementsList=null;
        By bytype=getBytpe(locator);
        try{
            elementsList=driver.findElements(bytype);
            if(elementsList.size()>0){
                log.info("Elements list found with :  "+locator);
            }else{
                log.info("Elements list not found with :  "+locator);
            }
        }
        catch (Exception e){
            log.error("Elements list not found with :  "+locator);
            e.printStackTrace();
        }
        return elementsList;
    }


    public boolean isElementPresent(String locator,String info){
        List<WebElement> elementList=getElementslist(locator,info,10);
        int size= elementList.size();
        if(size>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * Attempts to click on the specified WebElement. If a delay time is provided, the method will sleep for the specified
     * duration after clicking. Captures a screenshot in case of an exception.
     *
     * @param element The WebElement that needs to be clicked.
     * @param info Descriptive information about the WebElement, used for logging purposes.
     * @param timeToWait The time in milliseconds to wait after the click action. If 0, no waiting will occur.
     */
    public void clickelement(WebElement element ,String info,long timeToWait){
        try {
            element.click();
            if (timeToWait == 10) {
                log.info("Clicked on ::" + info);
            } else {
               sleep(timeToWait, "Clicked on ::" + info);
            }
        } catch (Exception e) {
            log.error("Cannot clicked on  ::"+info);
            takeScreenshot("Click Error"," ");
        }

       }

    /**
     * Attempts to click on the specified WebElement.
     * Uses the provided element and descriptive information to perform a click action.
     * This method delegates to another overloaded clickelement method with a default wait time of 0 milliseconds.
     *
     * @param element The WebElement that needs to be clicked.
     * @param info Descriptive information about the WebElement, used for logging purposes.
     */

       public void elementClick(WebElement element ,String info){
         clickelement(element,info,0);
       }

    /**
     * Attempts to click on an element located via the specified locator.
     * Delegates to an overloaded clickelement method that takes a WebElement as a parameter.
     *
     * @param locator The locator strategy to identify the element (e.g., id=>example, xpath=>example).
     * @param info Descriptive information about the element, used for logging purposes.
     * @param timeToWait The time in milliseconds to wait after the click action. If 0, no waiting will occur.
     */
       public void elementClick(String locator,String info,long timeToWait) {
           WebElement element = this.getElement(locator, info);
           clickelement(element, info, timeToWait);
       }

    /**
     * Attempts to find an element using the specified locator and descriptive information,
     * then clicks on it. Delegates to an overloaded clickelement method that takes a WebElement
     * as a parameter and specifies a default wait time of 0 milliseconds.
     *
     * @param locator The locator strategy to identify the element (e.g., id=>example, xpath=>example).
     * @param info Descriptive information about the element, used for logging purposes.
     */
       public void elementClick(String locator,String info) {
           WebElement element=this.getElement(locator,info);
           clickelement(element,info,0);

       }

    /**
     *  Clicked on the element using the javascriptexecutor m
     * @param element
     * @param info
     * @param timeToWait
     */
       public void javascriptClick(WebElement element,String info,long timeToWait){
           try{
               js.executeScript("arguments[0].click();",element);
               log.info("Clicked on :: "+info);
           }catch (Exception e){
               log.error("Cannot Clicked on ::"+info);
           }

       }

    /**
     * Performs a click action on a web element identified by the given locator using JavaScriptExecutor.
     * This method attempts to locate the element and execute a JavaScript click action on it.
     *
     * @param locator The locator strategy to identify the element (e.g., id=>example, xpath=>example).
     * @param info Additional context or description about the element, used for logging purposes.
     */
       public void javascriptClick(String locator,String info){
           WebElement element=getElement(locator,info);
           try{
               js.executeScript("arguments[0].click();",element);
               log.info("Clicked on :: "+info);
           }catch (Exception e){
               log.error("Cannot Clicked on ::"+info);
           }

       }

    public void actionsClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        try {
            Actions actions = new Actions(driver);
            actions.moveToElement(element).click().perform();
            log.info("Clicked on :: " + info + " using Actions class");
        } catch (Exception e) {
            log.error("Cannot Click on :: " + info + " - Exception: " + e.getMessage());
        }
    }

    /**
     * Waits for an element to be clickable within the specified timeout and performs a click action on it.
     * Temporarily disables implicit wait while waiting for the element to ensure accurate behavior.
     *
     * @param loactor The locator used to identify the web element on the page.
     * @param timeOut The maximum time (in seconds) to wait for the element to become clickable.
     */
       public void clickWhenReady(By loactor,int timeOut){
           try{
               driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
               WebElement element =null;
               log.info("Waiting for max ::"+timeOut+"second to be clickable");

               WebDriverWait wait =new WebDriverWait(driver,15);
               element= wait.until(ExpectedConditions.elementToBeClickable(loactor));
               element.click();
               log.info("Element clicked on the WebPage");
               driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
           }catch (Exception e){
               log.error("Element not apppeared on the WebPage");
               driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
           }
       }

    /**
     * Sends data to the specified WebElement. The method optionally clears the field before entering data,
     * logs the operation, and handles any exceptions encountered during the process.
     *
     * @param element The WebElement where the data needs to be sent.
     * @param data The data to be sent to the WebElement.
     * @param info Additional information or context about the WebElement for debugging or logging purposes.
     * @param clear A boolean flag indicating whether to clear the existing data from the WebElement before sending the new data.
     */

       public void sendData(WebElement element,String data,String info,Boolean clear){
           try{
               if(clear){
                   element.clear();
               }
              // Util.sleep(1000,"Waiting Before Entering the Data");
               element.sendKeys(data);
               log.info("SendKeys on element::"+element+info+"with data:: "+data);

           } catch (Exception e) {
               log.error("Cannot send keys on the element ::"+"  "+info+" "+element+" "+"with data:: "+data);
           }
       }




    public void sendDataUsingJS(WebElement element, String data, String info, Boolean clear) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 10);
            element = wait.until(ExpectedConditions.elementToBeClickable(element)); // Ensure interactability
            JavascriptExecutor js = (JavascriptExecutor) driver;

            // Scroll to the element
            js.executeScript("arguments[0].scrollIntoView(true);", element);

            // Click the button if necessary
            js.executeScript("arguments[0].click();", element);

            // Set value if it's an input field
            js.executeScript("arguments[0].value=arguments[1];", element, data);

            log.info("JavaScript sendKeys on element:: " + element + " " + info + " with data:: " + data);

        } catch (Exception e) {
            log.error("Cannot send keys using JavaScript on element:: " + info + " " + element + " with data:: " + data);
        }
    }



    public void sendDataUsingJS(String loactor,String data,String info){

           WebElement element=getElement(loactor,info);
           if(element !=null) {
               sendDataUsingJS(element, data, info, true);
           }else{
               log.error("Element not found"+element);
           }

    }




    public void sendDataUsingActions(WebElement element, String data, String info, Boolean clear) {
        try {


            Actions actions = new Actions(driver);



            // Perform Action
            actions.moveToElement(element).click().sendKeys(data).perform();

            log.info("Actions sendKeys on element:: " + element + " " + info + " with data:: " + data);
        } catch (Exception e) {
            log.error("Cannot send keys using Actions on element:: " + info + " " + element + " with data:: " + data, e);
        }
    }


    public void sendDataUsingaction(String locator,String data,String info){
        WebElement element=getElement(locator,info);
        if (element != null) {
            sendDataUsingActions(element, data, info, true);
        } else {
            log.error("Element not found for locator: " + locator);
        }

    }


    /**
     * Sends data to a web element located by the specified locator.
     * This method identifies the target web element using the locator and
     * performs an operation to send the provided data. It also logs information
     * about the operation and optionally clears the field before sending the data.
     *
     * @param locator The locator strategy to identify the target web element
     *                (e.g., id=>example, xpath=>example).
     * @param data The data to be sent to the identified web element.
     * @param info Additional context or description about the operation,
     *             typically used for logging purposes.
     * @param clear A boolean flag*/
       public  void sendData(String locator,String data,String info,Boolean clear){
           WebElement element=getElement(locator,info);
           sendData(element, data, info, clear);
       }




    /**
     * Sends data to a web element identified by a given locator.
     * This method retrieves the target element using the provided
     * locator and additional information, then sends the specified data
     * to the element. The action is always performed with the assumption
     * to clear the field before entering data.
     *
     * @param element The WebElement where the data needs to be sent.et web element
     *
     * @param data The data to be sent to the identified web element.
     * @param info Additional descriptive information about the locator or operation
     *             for debugging or logging purposes.
     */
       public void sendData(WebElement element,String data,String info){
           sendData(element,data,info,true);



       }

    /**
     * Sends data to the specified WebElement. This method is a simplified version of the overloaded
     * sendData method that always clears the field before entering data. It delegates the operation
     * to another method with a default behavior.
     *  @param locator The locator strategy to identify the target web element
     *                 (e.g., id=>example, xpath=>example).
     * @param data The data to be sent to the WebElement.
     * @param info Additional information or context about the WebElement for debugging or logging purposes.
     */
       public void sendData(String locator,String data,String info){
           sendData(locator,data,info,true);
       }


//    public void sendDataUsingActions(String locator,String data,String info){
//        sendDataUsingActions(locator,data,info,true);
//
//    }



    public String getText(WebElement element, String info) {
        log.info("Getting Text on element :: " + info);
        String text = null;
        text = element.getText();
        if (text.length() == 0) {
            text = element.getAttribute("innerText");
        }
        if (!text.isEmpty()) {
            log.info(" The text is : " + text);
        } else {
            text = "NOT_FOUND";
        }
        return text.trim();
    }

    /**
     * Get text of a web element with locator
     * @param locator
     * @param info
     * @return
     */
    public String getText(String locator, String info) {
        WebElement element = this.getElement(locator, info);
        return this.getText(element, info);
    }

    /**
     * Check if element is enabled
     * @param element
     * @param info
     * @return
     */
    public Boolean isEnabled(WebElement element, String info) {
        Boolean enabled = false;
        if (element != null) {
            enabled = element.isEnabled();
            if (enabled)
                log.info("Element :: " + info + " is Enabled");
            else
                log.info("Element :: " + info + " is Disabled");
        }
        return enabled;
    }

    /***
     * Check if element is enabled with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isEnabled(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isEnabled(element, info);
    }

    /**
     * Check if element is displayed
     * @param element
     * @param info
     * @return
     */
    public Boolean isDisplayed(WebElement element, String info) {
        Boolean displayed = false;
        if (element != null) {
            displayed = element.isDisplayed();
            if (displayed)
                log.info("Element :: " + info + " is displayed");
            else
                log.info("Element :: " + info + " is NOT displayed");
        }
        return displayed;
    }

    /***
     * Check if element is displayed with locator
     * @param locator
     * @param info
     * @return
     */
    public Boolean isDisplayed(String locator, String info) {
        WebElement element = getElement(locator, info);
        return this.isDisplayed(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean isSelected(WebElement element, String info) {
        Boolean selected = false;
        if (element != null) {
            selected = element.isSelected();
            if (selected)
                log.info("Element :: " + info + " is selected");
            else
                log.info("Element :: " + info + " is already selected");
        }
        return selected;
    }

    /**
     * @param locator
     * @param info
     * @return
     */
    public Boolean isSelected(String locator, String info) {
        WebElement element = getElement(locator, info);
        return isSelected(element, info);
    }

    /**
     * Selects a check box irrespective of its state
     *
     * @param element
     * @param info
     */
    public void Check(WebElement element, String info) {
        if (!isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is checked");
        } else
            log.info("Element :: " + info + " is already checked");
    }

    /**
     * Selects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void Check(String locator, String info) {
        WebElement element = getElement(locator, info);
        Check(element, info);
    }

    /**
     * UnSelects a check box irrespective of its state
     *
     * @param element
     * @param info
     * @return
     */
    public void UnCheck(WebElement element, String info) {
        if (isSelected(element, info)) {
            elementClick(element, info);
            log.info("Element :: " + info + " is unchecked");
        } else
            log.info("Element :: " + info + " is already unchecked");
    }

    /**
     * UnSelects a check box irrespective of its state, using locator
     *
     * @param locator
     * @param info
     * @return
     */
    public void UnCheck(String locator, String info) {
        WebElement element = getElement(locator, info);
        UnCheck(element, info);
    }

    /**
     * @param element
     * @param info
     * @return
     */
    public Boolean Submit(WebElement element, String info) {
        if (element != null) {
            element.submit();
            log.info("Element :: " + info + " is submitted");
            return true;
        } else
            return false;
    }

    /**
     * @param locator
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(String locator, String attribute) {
        WebElement element = getElement(locator, "info");
        return element.getAttribute(attribute);
    }

    /**
     * @param element
     * @param attribute
     * @return
     */
    public String getElementAttributeValue(WebElement element, String attribute) {
        return element.getAttribute(attribute);
    }

    /**
     * @param locator
     * @param timeout
     * @return
     */
    public WebElement waitForElement(String locator, int timeout) {
        By byType = getBytpe(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    /***
     * Wait for element to be clickable
     * @param locator - locator strategy, id=>example, name=>example, css=>#example,
     *      *                tag=>example, xpath=>//example, link=>example
     * @param timeout - Duration to try before timeout
     */
    public WebElement waitForElementToBeClickable(String locator, int timeout) {
        By byType = getBytpe(locator);
        WebElement element = null;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be clickable");

            WebDriverWait wait = new WebDriverWait(driver, 15);
            element = wait.until(
                    ExpectedConditions.elementToBeClickable(byType));
            log.info("Element is clickable on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return element;
    }

    /**
     *
     */
    public boolean waitForLoading(String locator, long timeout) {
        By byType = getBytpe(locator);
        boolean elementInvisible = false;
        try {
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            log.info("Waiting for max:: " + timeout + " seconds for element to be available");
            WebDriverWait wait = new WebDriverWait(driver, timeout);
            elementInvisible = wait.until(
                    ExpectedConditions.invisibilityOfElementLocated(byType));
            log.info("Element appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        } catch (Exception e) {
            log.error("Element not appeared on the web page");
            driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        }
        return elementInvisible;
    }

    /**
     * Mouse Hovers to an element
     *
     * @param locator
     */
    public void mouseHover(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.moveToElement(element).perform();
        //Util.sleep(5000);
    }

    /**
     * @param element
     * @param optionToSelect
     */
    public void selectOption(WebElement element, String optionToSelect) {
        Select sel = new Select(element);
        sel.selectByVisibleText(optionToSelect);
        log.info("Selected option : " + optionToSelect);
    }

    /**
     * Selects a given option in list box
     *
     * @param locator
     * @param optionToSelect
     */
    public void selectOption(String locator, String optionToSelect, String info) {
        WebElement element = getElement(locator, info);
        this.selectOption(element, optionToSelect);
    }

    /**
     * get Selected drop down value
     *
     * @param element
     * @return
     */
    public String getSelectDropDownValue(WebElement element) {
        Select sel = new Select(element);
        return sel.getFirstSelectedOption().getText();
    }

    /**
     * @param element
     * @param optionToVerify
     */
    public boolean isOptionExists(WebElement element, String optionToVerify) {
        Select sel = new Select(element);
        boolean exists = false;
        List<WebElement> optList = sel.getOptions();
        for (int i = 0; i < optList.size(); i++) {
            String text = getText(optList.get(i), "Option Text");
            if (text.matches(optionToVerify)) {
                exists = true;
                break;
            }
        }
        if (exists) {
            log.info("Selected Option : " + optionToVerify + " exist");
        } else {
            log.info("Selected Option : " + optionToVerify + " does not exist");
        }
        return exists;
    }

    /***
     *
     * @param methodName
     * @param browserName
     * @return
     */
    public String takeScreenshot(String methodName, String browserName) {
        String fileName = Util.getScreenshotName(methodName, browserName);
        String screenshotDir = System.getProperty("user.dir") + "//" + "test-output/screenshots";
        new File(screenshotDir).mkdirs();
        String path = screenshotDir + "//" + fileName;

        try {
            File screenshot = ((TakesScreenshot)driver).
                    getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot, new File(path));
            log.info("Screen Shot Was Stored at: "+ path);
        } catch(Exception e) {
            e.printStackTrace();
        }
        return path;
    }

    public void DoubleClick(WebElement element, String info) {
        Actions action = new Actions(driver);
        action.doubleClick(element);
        log.info("Double Clicked on :: " + info);
        action.perform();
    }

    /**
     * Right Click a WebElement
     *
     * @param locator
     */
    public void rightClick(String locator, String info) {
        WebElement element = getElement(locator, info);
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        log.info("Double Clicked on :: " + info);
    }

    /**
     * Right click a WebElement and select the option
     *
     * @param elementLocator
     * @param itemLocator
     */
    public void selectItemRightClick(String elementLocator, String itemLocator) {
        WebElement element = getElement(elementLocator, "info");
        Actions action = new Actions(driver);
        action.contextClick(element).build().perform();
        WebElement itemElement = getElement(itemLocator, "info");
        elementClick(itemElement, "Selected Item");
    }

    /**
     * @param key
     */
    public void keyPress(Keys key, String info) {
        Actions action = new Actions(driver);
        action.keyDown(key).build().perform();
        log.info("Key Pressed :: " + info);
    }
}


