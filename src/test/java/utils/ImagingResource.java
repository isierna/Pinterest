package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImagingResource {
    static WebDriver driver;
    static String maker = "Olympus";
    static String model = "Stylus 1s";
    static String linkToFiles = "http://www.imaging-resource.com/PRODS/olympus-stylus-1/olympus-stylus-1THMB.HTM";


    @BeforeMethod
    public static void openBrowser() {
        FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.download.manager.showWhenStarting", false);
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", "/Users/Ira/Pictures/1");
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "image/x-cr2");
        driver = new FirefoxDriver(profile);
    }

    private static WebElement waitUntil(ExpectedCondition<WebElement> condition) {
        return (new WebDriverWait(driver, 10)).until(condition);
    }

    private static void checkForAd() {
        try {
            driver.findElement(By.linkText("//*[@id=\"AdFloater\"]/a[1]")).click();
        } catch (NoSuchElementException e) {
            System.out.println("Error " + e);
        }
    }


    @Test
    public static void photoGet() throws IOException, AWTException {
        String main_directory = "/Users/Ira/temp/" + maker + "_" + model;
        String directory = main_directory + "/JPG&RAW";

        new File(main_directory).mkdir();
        new File(directory).mkdir();

        driver.get(linkToFiles);
        //driver.manage().window().maximize(); //currently causes issue in FF 54, should be fixed in FF 55

        List<WebElement> photo_links = driver.findElements(By.xpath("//table[@id=\"thumbs-table\"]/tbody/tr/td/a[not(contains(@href,'EXIF'))][not(./img)]"));

        ArrayList<String> photo_links_urls = new ArrayList<String>();

        for (WebElement y : photo_links) {
            String a = y.getAttribute("href");
            photo_links_urls.add(a);
        }
        System.out.println("LLL" + photo_links_urls.size());

        System.out.println(photo_links_urls);


        for (int i = 0; i < photo_links.size(); i++) {
            driver.get(photo_links_urls.get(i));
            try {
                driver.findElement(By.linkText("Full Size Image")).click();
                WebElement link_element = driver.findElement(By.xpath("//img[contains(@src,'FULLRES')]"));

                String link1 = link_element.getAttribute("src");
                System.out.println(link1);

                URL url = new URL(link1);
                String file_name = url.getFile();
                String destination = directory + file_name.substring(file_name.lastIndexOf("/"));

                System.out.println(destination);

                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destination);

                byte[] bt = new byte[2048];
                int length;

                while ((length = is.read(bt)) != -1) {
                    os.write(bt, 0, length);
                }

                is.close();
                os.close();

            } catch (NoSuchElementException e) {
                System.out.println(e);
            }

            try {
                WebElement raw_element = driver.findElement(By.xpath("//a[@class='caption']"));
                String raw_url = raw_element.getAttribute("href");
                URL url = new URL(raw_url);
                String file_name = url.getFile();
                String destination = directory + file_name.substring(file_name.lastIndexOf("/"));

                System.out.println(destination);

                InputStream is = url.openStream();
                OutputStream os = new FileOutputStream(destination);

                byte[] bt = new byte[2048];
                int length;

                while ((length = is.read(bt)) != -1) {
                    os.write(bt, 0, length);
                }

                is.close();
                os.close();

                System.out.println("success");

            } catch (NoSuchElementException e) {
                System.out.println(e);
            }
        }
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }
}

