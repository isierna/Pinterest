package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//button[@class='lightGrey active']")
    public WebElement buttonToLoginPage;

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

    @FindBy(xpath = "//input[@aria-label=\'Full name\']")
    public WebElement fullNameInput;

    @FindBy(xpath = "//input[@aria-label=\'Age\']")
    public WebElement age;

    @FindBy(xpath = "//button[@class=\'red comeOnInButton active\']")
    public WebElement submitButton;

    @FindBy(xpath = "//label[@class=\'Gender__tooltip\'][2]")
    public WebElement gender;

    @FindBy(xpath = "//div[@class=\'_su _st _sv _sm _5i _sn _np _nq _nr _ns\']")
    public WebElement userHomeHeader;

}
