package utils.photoDownload;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.NoSuchElementException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImagingResourceNew {
    static WebDriver driver;

    static String maker = "Sony";
    static String model = "A99M2";
    static String linkToFiles = "http://www.imaging-resource.com/PRODS/sony-a99-ii/sony-a99-iiTHMB.HTM";

    @BeforeMethod
    public static void openBrowser() {
        driver = new FirefoxDriver();
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }

    public static void fileDownload(String link, String folder) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        String userAgent = "Mozilla/5.0";
        String myCommand = "wget"+" --user-agent="+userAgent+" -r '" + link + "' -P " + folder + " -nH " + " --cut-dirs=3";

        runtime.exec(new String[] {"bash", "-c", myCommand});

    }

    @Test
    public static void photoGet() throws IOException {
        String main_directory = "/Users/Ira/temp/111/" + maker + "_" + model;

        new File(main_directory).mkdir();

        driver.get(linkToFiles);
        driver.manage().window().maximize();

        List<WebElement> photo_links = driver.findElements(By.xpath("//table[@id=\"thumbs-table\"]/tbody/tr/td/a[not(contains(@href,'EXIF'))][not(./img)]"));
        ArrayList<String> photo_links_urls = new ArrayList<>();

        for (WebElement y : photo_links) {
            String url = y.getAttribute("href");
            photo_links_urls.add(url);
        }

        for (int i = 0; i < photo_links.size(); i++) {
            driver.get(photo_links_urls.get(i));
            try {
                driver.findElement(By.linkText("Full Size Image")).click();
                WebElement link_element = driver.findElement(By.xpath("//img[contains(@src,'FULLRES')]"));

                String link = link_element.getAttribute("src");

                System.out.println("Phoro URL => " + link);
                fileDownload(link, main_directory);
            }catch (NoSuchElementException e) {
                System.out.println(e);
                WebElement raw_element = driver.findElement(By.xpath("//a[@class='caption']"));
                String raw_url = raw_element.getAttribute("href");
                fileDownload(raw_url,main_directory);
            }
        }

        System.out.println("DONE");
    }

}
