package WebTesting.Utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.impl.SimpleLogger;

import java.time.Duration;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GWD {

    public static WebDriver driver;
    public static WebDriverWait wait;

    public static WebDriver getDriver(){


        if (driver == null){

            Locale.setDefault(new Locale("EN"));
            System.setProperty("user.language", "EN");

            Logger.getLogger("").setLevel(Level.SEVERE);
            System.setProperty(SimpleLogger.DEFAULT_LOG_LEVEL_KEY, "Error");
            System.setProperty(ChromeDriverService.CHROME_DRIVER_SILENT_OUTPUT_PROPERTY, "true");

            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
            driver.manage().window().maximize();
            wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        }
        return driver;
    }

    public static void quitDriver(){

        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }

        if(driver != null){
            driver.quit();
            driver = null;
        }
    }


    public static void bekle(int saniye){

        try {
            Thread.sleep(saniye * 1000);
        }catch (InterruptedException e){
            throw new RuntimeException(e);
        }
    }


}
