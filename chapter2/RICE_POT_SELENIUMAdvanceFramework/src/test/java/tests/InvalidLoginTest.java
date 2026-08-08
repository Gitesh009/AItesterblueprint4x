package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.LoginPage;

public class InvalidLoginTest {
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeTest
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        loginPage = new LoginPage(driver);
        loginPage.navigateToLoginPage("https://login.salesforce.com/?locale=in");
    }

    @Test
    public void shouldShowErrorForInvalidCredentials() {
        loginPage.login("invalid.user@example.com", "InvalidPassword", false);
        String message = loginPage.getErrorMessageText();
        Assert.assertTrue(message.contains("Please check your username and password"), "Expected invalid login error message");
    }

    @AfterTest
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
