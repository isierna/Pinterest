import org.apache.commons.io.FileUtils;
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

    public void waitUntil(String elementXpath){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(elementXpath)));
    }


    @Test
    public void testHome() {
       WebDriverWait wait = new WebDriverWait(driver,10);
       wait.until(ExpectedConditions.textToBePresentInElement(By.xpath("//*"),"Welcome to Pinterest"));
       System.out.println("Success");
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
        WebElement signUpButton = driver.findElement(By.xpath("//button[@class=\'red SignupButton active\']"));

        String userEmail = generateEmail() + "@gmail.com";

        emailInput.sendKeys(userEmail);
        passwordInput.click();
        passwordInput.sendKeys("123456zz");
        signUpButton.click();

        System.out.println();
        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class=\'_su _st _sx _sl _5k _sn _sr _np _nq _nr _ns\']"), userEmail));
        WebElement fullNameInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label=\'Full name\']")));
        WebElement age = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//input[@aria-label=\'Age\']")));
        WebElement submitButton = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@class=\'red comeOnInButton active\']")));
        WebElement gender = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class=\'Gender__tooltip\'][2]")));


        fullNameInput.sendKeys("Tom");
        age.sendKeys("18");
        gender.click();
        submitButton.click();

        wait.until(ExpectedConditions.textToBe(By.xpath("//div[@class=\'_su _st _sv _sm _5i _sn _np _nq _nr _ns\']"), "Tom"));
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
