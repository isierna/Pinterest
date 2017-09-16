package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.User;

import java.util.Random;

public class BaseTest {
    WebDriver driver;

    @BeforeMethod
    public void configureBrowser() {
        driver = new FirefoxDriver();
        driver.get("http://www.pinterest.com");
    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }
}
