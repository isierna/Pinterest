package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import utils.Helpers;

public class BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void configureBrowser() {
        driver = new FirefoxDriver();
        driver.get("http://www.pinterest.com");
    }

    @AfterMethod
    public void takeScreenOnFail(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(file, new File("/Users/ira/IdeaProjects/Pinterest/src/test/results/" + testResult.getInstanceName() + "__" + Helpers.currentDateAndTime() + ".jpg"));
            driver.quit();
        }
        driver.quit();
    }
}
