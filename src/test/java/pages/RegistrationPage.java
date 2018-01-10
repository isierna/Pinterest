package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.Helpers;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /*public void at(WebElement element) {
        Helpers.waitUntil(element, this.driver);
    }*/

    @FindBy(xpath = "//button[@class='FullPageSignup__signupToggle']")
    public WebElement signUpLink;

    @FindBy(xpath = "//div[@class='DenzelReactBridge Module']")
    public WebElement loginWindow;

    @FindBy(xpath = "//fieldset[1]")
    public WebElement emailInput;

    @FindBy(xpath = "//fieldset[2]")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@class=\'red SignupButton active\']")
    public WebElement signUpButton;

    @FindBy(xpath = "//input[@name=\'full_name\']")
    public WebElement fullNameInput;

    @FindBy(xpath = "//input[@aria-label=\'Age\']")
    public WebElement age;

    @FindBy(xpath = "//button[@class=\'red comeOnInButton active\']")
    public WebElement submitButton;

    @FindBy(xpath = "//input[@name=\'gender\'][@value=\'male\']")
    public WebElement gender;

    @FindBy(xpath = "//div[@class=\'_st _ss _sw _sk _5j _sm _sq _no _np _nq _nr\']")
    public WebElement registrationStage2Container;

}
