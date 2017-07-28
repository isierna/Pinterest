import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

public class InitialTest {
    WebDriver driver;

    @BeforeMethod
    public void configureBrowser() {
        driver = new FirefoxDriver();
        driver.get("http://www.pinterest.com");
    }


    @Test
    public void testHome() {
        WebElement element = driver.findElement(By.xpath("//div[@data-reactid=13]"));
        element.getText().contains("Welcome to Pinterest");

        System.out.println(element.getText());

    }

    @Test
    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='lightGrey active']")));

        System.out.println(loginButton.getText());
        loginButton.getText().contains("Log in");
        loginButton.click();

        WebElement loginWindow = driver.findElement(By.xpath("//div[@data-reactid=4]/div/div/h3"));
        loginWindow.getText().contains("Log in to see more");
        Assert.assertTrue(loginWindow.getText().contains("Log in to see more"));
        //System.out.println(loginWindow.getText());
        //System.out.println("done");

        WebElement emailInput = driver.findElement(By.xpath("//fieldset[1]"));
        WebElement passwordInput = driver.findElement(By.xpath("//fieldset[2][@data-reactid=9]"));

        RandomStringUtils randomObject = new RandomStringUtils();
        String random = randomObject.randomNumeric(3);

        emailInput.sendKeys("trys2015+" + random + "@gmail.com");
        passwordInput.click();
        passwordInput.sendKeys("123456zz");

        System.out.println();
//        todo: add login button

    }

    @Test
    public void registration() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='lightGrey active']")));

        System.out.println(loginButton.getText());
        loginButton.getText().contains("Log in");
        loginButton.click();

        WebElement signUpLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='FullPageSignup__signupToggle']")));
        signUpLink.click();

        WebElement loginWindow = driver.findElement(By.xpath("//div[@class='DenzelReactBridge Module']"));
        loginWindow.getText().contains("Sign up to see more");
        Assert.assertTrue(loginWindow.getText().contains("Sign up to see more"));

        WebElement emailInput = driver.findElement(By.xpath("//fieldset[1]"));
        WebElement passwordInput = driver.findElement(By.xpath("//fieldset[2][@data-reactid=9]"));

        RandomStringUtils randomObject = new RandomStringUtils();
        String random = randomObject.randomNumeric(3);
        String userEmail = "trys2015+" + random + "@gmail.com";

        emailInput.sendKeys(userEmail);
        passwordInput.click();
        passwordInput.sendKeys("123456zz");

        System.out.println();
    }

    @AfterMethod
    public void takeScreenOnFail(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/vm/IdeaProjects/pinteresttests/src/test/results/" + testResult.getTestName() + ".jpg"));
        driver.quit();
        }
        driver.quit();
    }

}
