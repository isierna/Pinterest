import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
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
import java.util.Random;

public class InitialTest {
    WebDriver driver;

    @BeforeMethod
    public void configureBrowser() {
        driver = new FirefoxDriver();
        driver.get("http://www.pinterest.com");
    }

    public String generateEmail() {
        String firstPartOfTheEmail = "trys2015";
        Integer randomIndex = new Random().nextInt(firstPartOfTheEmail.length());
        if (randomIndex == 0) {
            randomIndex = randomIndex + 1;
        }
        String subString = firstPartOfTheEmail.substring(randomIndex);
        String finalRegString = firstPartOfTheEmail.replace(subString,"") + "." + subString;
        return finalRegString;
    }


    @Test
    public void testHome() {
        //WebElement element = driver.findElement(By.xpath("//div[@data-reactid=13]"));
        //element.getText().contains("Welcome to Pinterest");

        //System.out.println(element.getText());

    }

    @Test
    public void login() {
        WebDriverWait wait = new WebDriverWait(driver, 20);
        WebElement buttonToLoginPage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='lightGrey active']")));

        System.out.println(buttonToLoginPage.getText());
        buttonToLoginPage.getText().contains("Log in");
        buttonToLoginPage.click();

        WebElement loginWindow = driver.findElement(By.xpath("//div[@data-test-login]"));
        Assert.assertTrue(loginWindow.getText().contains("Log in to see more"));

        WebElement emailInput = driver.findElement(By.xpath("//fieldset[1]"));
        WebElement passwordInput = driver.findElement(By.xpath("//fieldset[2]"));
        WebElement loginButton = driver.findElement(By.xpath("//button[@class=\'red SignupButton active\']"));

        emailInput.sendKeys("trys2016" + "@gmail.com");
        passwordInput.click();
        passwordInput.sendKeys("123456zz");

        loginButton.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class=\'_su _st _sv _sm _5i _sn _np _nq _nr _ns\']"), "Tom"));

        //Assert.assertTrue(userHeader.getText().contains("Tom"));

        System.out.println();
//        todo: add login button validation


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
        WebElement passwordInput = driver.findElement(By.xpath("//fieldset[2]"));
/*
        RandomStringUtils randomObject = new RandomStringUtils();
        String random = randomObject.randomNumeric(3);*/
        String userEmail = generateEmail() + "@gmail.com";

        emailInput.sendKeys(userEmail);
        passwordInput.click();
        passwordInput.sendKeys("123456zz");

        System.out.println();
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class=\'_su _st _sx _sl _5k _sn _sr _np _nq _nr _ns\']"), userEmail));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label=\'Full name\']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label=\'Age\']")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\'red comeOnInButton active\']")));


    }

    @AfterMethod
    public void takeScreenOnFail(ITestResult testResult) throws IOException {
        if (testResult.getStatus() == ITestResult.FAILURE) {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File("/Users/ira/IdeaProjects/Pinterest/src/test/results/" + testResult.getTestName() + ".jpg"));
        driver.quit();
        }
        driver.quit();
    }

}
