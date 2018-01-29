package utils.photoDownload;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class ImagingResource {
    static WebDriver driver;
    static String maker = "Panasonic";
    static String model = "Oplymp_targets";
    static String linkToFiles = "http://www.imaging-resource.com/PRODS/canon-eos-m100/canon-eos-m100GALLERY.HTM";

    public static void download(URL url, String destination) throws IOException {
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestProperty("Referer","http://www.imaging-resource.com/PRODS/canon-eos-m100/YIMG-1794.CR2.HTM");
        connection.setRequestProperty("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
        connection.setRequestProperty("Upgrade-Insecure-Requests","1");
        connection.setRequestProperty("Connection","keep-alive");
        connection.setRequestProperty("Host","www.imaging-resource.com");
        connection.setRequestProperty("Accept-Encoding","gzip, deflate");
        connection.setRequestProperty("Accept-Language","en-US,en;q=0.9");
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        connection.setRequestProperty("Cookie", "__cfduid=def972d5681103f8075722c4fe8937c0f1516705260; optimizelyEndUserId=oeu1516705261590r0.7801117101725461; optimizelySegments=%7B%222097821172%22%3A%22gc%22%2C%222114790441%22%3A%22search%22%2C%222121700427%22%3A%22false%22%7D; optimizelyBuckets=%7B%7D; __utmc=228902916; __utmz=228902916.1516705263.1.1.utmcsr=google|utmccn=(organic)|utmcmd=organic|utmctr=(not%20provided); __qca=P0-1769225072-1516705263323; bm_monthly_unique=true; bm_daily_unique=true; bm_sample_frequency=100; __gads=ID=f4d26dabd8140a1b:T=1516705265:S=ALNI_Mbfxj2TUFdkpTuSsIlmvsIHbbe-8A; bm_last_load_status=NOT_BLOCKING; amplitude_idimaging-resource.com=eyJkZXZpY2VJZCI6IjIzMmVmYzQ1LWNhNDktNDJlZi1iMmIxLWQ2M2ZjOGE0OWE1MiIsInVzZXJJZCI6bnVsbCwib3B0T3V0IjpmYWxzZX0=; __utma=228902916.1057577452.1516705263.1516705263.1516709265.2; __utmt=1; __utmb=228902916.4.9.1516709451329");

        InputStream inputStream = connection.getInputStream();
        OutputStream outputStream = new FileOutputStream(destination);

        byte[] bt = new byte[2048];
        int length;

        while ((length = inputStream.read(bt)) != -1) {
            outputStream.write(bt, 0, length);
        }

        inputStream.close();
        outputStream.close();
    }



    @BeforeMethod
    public static void openBrowser() {
        FirefoxBinary firefoxBinary = new FirefoxBinary();
        firefoxBinary.addCommandLineOptions("--headless");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setBinary(firefoxBinary);
        driver = new FirefoxDriver(firefoxOptions);
    }


    @Test
    public static void photoGet() throws IOException, AWTException {
        String main_directory = "/Users/Ira/temp/" + maker + "_" + model;
        String directory = main_directory + "/JPG&RAW";

        new File(main_directory).mkdir();
        new File(directory).mkdir();

        driver.get(linkToFiles);

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
                URL url = new URL(link1);

                String file_name = url.getFile();
                String destination = directory + file_name.substring(file_name.lastIndexOf("/"));

                download(url,destination);

            } catch (NoSuchElementException e) {
                System.out.println(e);
                WebElement raw_element = driver.findElement(By.xpath("//a[@class='caption']"));
                String raw_url = raw_element.getAttribute("href");
                URL url = new URL(raw_url);
                String file_name = url.getFile();
                String destination = directory + file_name.substring(file_name.lastIndexOf("/"));


                download(url,destination);
            }
        }
    }

    @AfterMethod
    public static void closeBrowser() {
        driver.quit();
    }
}

