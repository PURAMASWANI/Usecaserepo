package Test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
//import io.github.bonigarcia.wdm.WebDriverManager;   // <-- Uncommented

public class GoogleSearchTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
      //  WebDriverManager.chromedriver().setup();    // <-- Uncommented

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleTitle() {
        driver.get("https://www.google.com");
        String title = driver.getTitle();
        assertTrue(title.contains("Google"), 
            "Expected title to contain 'Google', but was: " + title);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}