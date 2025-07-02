package org.automationfrmwrk.utilities;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.io.File;
public class ExtentManager {

    private static ExtentReports extent;
    private  static final Logger log= LogManager.getLogger(ExtentManager.class.getName());

    public static ExtentReports getInstance(){
        if(extent==null){
            createInstance();

        }
        return extent;
    }

    public static synchronized ExtentReports createInstance(){
        String fileName=Util.getReportName();
        String reportsDirectory=Constants.REPORTS_DIRECTORY;
        new File(reportsDirectory).mkdirs();
        String path=reportsDirectory+fileName;
        log.info("*********** Report Path ***********");
        log.info(path);
        log.info("*********** Report Path ***********");
        ExtentSparkReporter sparkReporter=new ExtentSparkReporter(path);
        sparkReporter.config().setTheme(Theme.DARK);
        sparkReporter.config().setDocumentTitle("AutomationRun");
        sparkReporter.config().setEncoding("utf-8");
        sparkReporter.config().setReportName(fileName);
        extent=new ExtentReports();
        extent.setSystemInfo("Organization","Jio");
        extent.setSystemInfo("AutomationFrameWork","Selenium WebDriver");
        extent.attachReporter(sparkReporter);
        return extent;
    }
}
