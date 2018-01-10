package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserHomePage extends BasePage {
    public  UserHomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//div[@class=\'_su _st _sv _sm _5i _sn _np _nq _nr _ns\']")
    public WebElement userHomeHeader;
}
