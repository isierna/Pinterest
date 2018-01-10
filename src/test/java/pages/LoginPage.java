package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;

public class LoginPage extends BasePage{
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*public void at(WebElement element) {
        Helpers.waitUntil(element, this.driver);
    }*/

    @FindBy(xpath = "//button[@class='lightGrey active']")
    public WebElement buttonToLoginPage;
}
