package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    @FindBy(xpath = "//input[@id='username']")
    private WebElement username;

    @FindBy(xpath = "//input[@id='password']")
    private WebElement password;

    @FindBy(xpath = "//input[@id='Login']")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@id='rememberUn']")
    private WebElement rememberMeCheckbox;

    @FindBy(xpath = "//div[@id='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        PageFactory.initElements(driver, this);
    }

    public void navigateToLoginPage(String url) {
        driver.get(url);
        wait.until(ExpectedConditions.visibilityOf(username));
    }

    public void enterUsername(String user) {
        try {
            wait.until(ExpectedConditions.visibilityOf(username));
            username.clear();
            username.sendKeys(user);
        } catch (Exception exception) {
            throw new RuntimeException("Unable to enter username", exception);
        }
    }

    public void enterPassword(String pass) {
        try {
            wait.until(ExpectedConditions.visibilityOf(password));
            password.clear();
            password.sendKeys(pass);
        } catch (Exception exception) {
            throw new RuntimeException("Unable to enter password", exception);
        }
    }

    public void toggleRememberMe(boolean enable) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(rememberMeCheckbox));
            if (rememberMeCheckbox.isSelected() != enable) {
                rememberMeCheckbox.click();
            }
        } catch (Exception exception) {
            throw new RuntimeException("Unable to toggle remember me", exception);
        }
    }

    public void clickLogin() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(loginButton));
            loginButton.click();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to click login button", exception);
        }
    }

    public String getErrorMessageText() {
        try {
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            return errorMessage.getText().trim();
        } catch (Exception exception) {
            throw new RuntimeException("Unable to read error message", exception);
        }
    }

    public void login(String user, String pass, boolean rememberMe) {
        enterUsername(user);
        enterPassword(pass);
        toggleRememberMe(rememberMe);
        clickLogin();
    }
}
