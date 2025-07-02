import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.sql.Driver;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Practice{

    public static void main(String[] args) throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","/Users/saurabh16.pandey/Library/CloudStorage/OneDrive-RelianceCorporateITParkLimited/AutomationFrameWork/automationfrmwrk/drivers/chromedriver");

        WebDriver driver =new ChromeDriver();
        Actions actions=new Actions(driver);

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
        driver.get("https://www.letskodeit.com/practice");
        driver.manage().window().maximize();

        //Calender Selection in  Makemytrip
        /*driver.findElement(By.xpath("//li[@data-cy='menu_Flights']")).click();
        WebElement from=driver.findElement(By.xpath("//label[@for='fromCity']"));
        from.click();
        WebElement fromcityinput=driver.findElement(By.xpath("//input[@placeholder='From']"));
        fromcityinput.sendKeys("BOM");
        WebElement autosuggest= driver.findElement(By.id("react-autowhatever-1-section-0-item-0"));
        autosuggest.click();
        WebElement depaturecalender=driver.findElement(By.xpath("//label[@for='departure']"));
        depaturecalender.click();
        List<WebElement> fromdate=driver.findElements(By.xpath("//div[@class='DayPicker-Month'][1]//div[@class='DayPicker-Day']/div/p"));

        for(WebElement date:fromdate){
            if(date.getText().equals("28")){
                date.click();
                break;
            }
        }*/
        Thread.sleep(2000);
        //swtich to widow
        /*          String parenthandle= driver.getWindowHandle();

                WebElement newwindow=driver.findElement(By.id("openwindow"));
                newwindow.click();



                Set<String>handles=driver.getWindowHandles();

                for(String handle:handles){
                    System.out.println(handle);
                    if(!handle.equals(parenthandle)){
                        driver.switchTo().window(handle);
                        Thread.sleep(2000);
                       WebElement searchbox= driver.findElement(By.name("course"));
                       searchbox.click();
                       searchbox.sendKeys("python");
                       WebElement searchbutton= driver.findElement(By.xpath("//button[@class='find-course search-course']"));
                       searchbutton.click();
                       driver.close();
                       break;
                    }
                }

                driver.switchTo().window(parenthandle);
                driver.findElement(By.xpath("//input[@name='enter-name']")).sendKeys("Saurabh");*/

        Thread.sleep(2000);

        // Iframe

    /*    driver.switchTo().frame("courses-iframe");
        WebElement searchbox= driver.findElement(By.name("course"));
        searchbox.click();
        searchbox.sendKeys("python");
        WebElement searchbutton= driver.findElement(By.xpath("//button[@class='find-course search-course']"));
        searchbutton.click();

        Thread.sleep(10000);

        driver.switchTo().defaultContent();
        driver.findElement(By.xpath("//input[@name='enter-name']")).sendKeys("Saurabh");*/



        driver.switchTo().frame("courses-iframe");
        driver.findElement(By.xpath("//li[@data-id='562175']")).click();
        driver.findElement(By.xpath("//a[@title='No Such Element Exception']")).sendKeys(Keys.RETURN);
        List<WebElement> Pretag= driver.findElements(By.tagName("pre"));
        WebElement mainpre =Pretag.get(0);
        String text =mainpre.getText();
        System.out.println(text);

        Thread.sleep(10000);
        driver.quit();



    }
}