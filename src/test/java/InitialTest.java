import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class InitialTest {
    WebDriver driver;

    @Test
    public void test() {
        System.setProperty("webdriver.gecko.driver", "/Users/vm/Documents/geckodriver");
        driver = new FirefoxDriver();
        driver.get("http://www.pinterest.com");
        WebElement element = driver.findElement(By.linkText("Welcome to Pinterest"));


    }

    @AfterMethod
    public void takeScreenOnFail(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/vm/IdeaProjects/pinteresttests/src/test/results/" + testResult.getTestName() + ".jpg"));
        }
    }



    @AfterMethod
    public void browserClose() {
        driver.quit();

    }

}
