package Test;
//package Test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;
import io.github.bonigarcia.wdm.WebDriverManager;

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

        // Wait a bit for results to load (simplified; in real tests use WebDriverWait)
        try { Thread.sleep(2000); } catch (InterruptedException e) {}

        // Verify that the results page title contains the search term
        String title = driver.getTitle();
        assertTrue(title.contains("Selenium WebDriver"), 
            "Title does not contain search term. Actual: " + title);

        // Verify that at least one result link is present
        WebElement firstResult = driver.findElement(By.cssSelector("h3"));
        assertNotNull(firstResult, "No search results found!");
        assertTrue(firstResult.isDisplayed(), "First result is not visible");
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}