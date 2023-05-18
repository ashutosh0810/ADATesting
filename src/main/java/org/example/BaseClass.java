package org.example;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

public class BaseClass {
    protected WebDriver driver;
    protected ExtentReports extent;
    protected ExtentTest test;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/Spark/Spark.html");
        extent.attachReporter(spark);
    }

    @BeforeMethod
    public void startReport(Method method) {
        test = extent.createTest(method.getName());
    }

    @AfterMethod
    public void endReport() {
        extent.flush();
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
