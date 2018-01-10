package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegistrationPage;
import pages.UserHomePage;
import utils.User;

public class RegistrationTest extends BaseTest {
    static RegistrationPage registrationPage;
    static LoginPage loginPage;
    static UserHomePage userHomePage;

    @Test
    public void registration() {
        loginPage = new LoginPage(driver);
        loginPage.at(loginPage.buttonToLoginPage);
        Assert.assertTrue(loginPage.buttonToLoginPage.getText().contains("Log in"));
        loginPage.buttonToLoginPage.click();

        registrationPage = new RegistrationPage(driver);
        registrationPage.at(registrationPage.signUpLink);
        registrationPage.signUpLink.click();
        Assert.assertTrue(registrationPage.loginWindow.getText().contains("Sign up to see more"));

        registrationPage.emailInput.sendKeys(User.userEmail);
        registrationPage.passwordInput.click();
        registrationPage.passwordInput.sendKeys("123456zz");
        registrationPage.signUpButton.click();

        registrationPage.at(registrationPage.registrationStage2Container);
        registrationPage.fullNameInput.sendKeys("Tom");
        registrationPage.age.sendKeys("16");
        registrationPage.gender.click(); //TODO:gender is not always there, we need to make an enhancement
        registrationPage.submitButton.click();

        userHomePage = new UserHomePage(driver); //TODO: add waiter
        userHomePage.at(userHomePage.userHomeHeader);
        Assert.assertTrue(userHomePage.userHomeHeader.getText().contains("Tom"));
    }
}
