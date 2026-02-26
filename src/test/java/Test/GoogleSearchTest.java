package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class GoogleSearchTest {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
    }

    @Test
    public void testGoogleSearch() {
        // Navigate to Google
        driver.get("https://www.google.com");

        // Find the search box and enter a query
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.submit();

        // Wait for the title to contain the search term (or "Google Search")
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean titleContainsExpected = wait.until(ExpectedConditions.or(
            ExpectedConditions.titleContains("Selenium WebDriver"),
            ExpectedConditions.titleContains("Google Search")
        ));

        // Assert that the title contains either the search term or "Google Search"
        String title = driver.getTitle();
        assertTrue(title.contains("Selenium WebDriver") || title.contains("Google Search"),
            "Title did not contain expected text. Actual title: " + title);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}