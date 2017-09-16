package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.RegistrationPage;
import utils.User;

public class RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;

    @Test
    public void registration() {
        registrationPage = new RegistrationPage(driver);
        registrationPage.waitUntil(registrationPage.buttonToLoginPage);

        Assert.assertTrue(registrationPage.buttonToLoginPage.getText().contains("Log in"));
        registrationPage.buttonToLoginPage.click();

        registrationPage.waitUntil(registrationPage.signUpLink);
        registrationPage.signUpLink.click();

        Assert.assertTrue(registrationPage.loginWindow.getText().contains("Sign up to see more"));

        registrationPage.emailInput.sendKeys(User.userEmail);
        registrationPage.passwordInput.click();
        registrationPage.passwordInput.sendKeys("123456zz");
        registrationPage.signUpButton.click();

        registrationPage.fullNameInput.sendKeys("Tom");
        registrationPage.age.sendKeys("16");
        registrationPage.gender.click();
        registrationPage.signUpButton.click();

        Assert.assertTrue(registrationPage.userHomeHeader.getText().contains("Tom"));
    }
}
