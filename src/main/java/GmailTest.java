import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.util.Base64;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

public class GmailTest {

    String driverPath = "C:\\Users\\Administrator\\Desktop\\driver\\chromedriver.exe";
    String url = "https://www.gmail.com";

    String username = "";
    String password = "";

    WebDriver driver ;
    WebDriverWait wait;


    @Test
    public void gmailTest() throws InterruptedException {
        openUrl();
        login(username,password);
    }

    public void openUrl(){
        System.setProperty("webdriver.chrome.driver", driverPath);

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.setExperimentalOption("useAutomationExtension", false);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));


        driver = new ChromeDriver(options);
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        wait = new WebDriverWait(driver, 30);
        driver.get(url);
    }

    public void login(String userName, String password) throws InterruptedException {

//        WebElement singInLink = driver.findElement(By.xpath("//a[contains(text(), 'Sign in')]"));
//        wait.until(ExpectedConditions.visibilityOf(singInLink));
//        singInLink.click();

        WebElement userNameTextBox = driver.findElement(By.xpath("//input[@type='email']"));
        wait.until(ExpectedConditions.visibilityOf(userNameTextBox));
        userNameTextBox.sendKeys(userName);

        WebElement nextButton = driver.findElement(By.xpath("//span[contains(text(), 'Next')]"));
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();

        WebElement passwordTextBox = driver.findElement(By.xpath("//input[@type='password']"));
        wait.until(ExpectedConditions.visibilityOf(passwordTextBox));
        String decodedPassword = decodedString(password);
        passwordTextBox.sendKeys(decodedPassword);

        nextButton = driver.findElement(By.xpath("//span[contains(text(), 'Next')]"));
        wait.until(ExpectedConditions.visibilityOf(nextButton));
        nextButton.click();

        Thread.sleep(3000);
    }

    public String decodedString(String encodedString){
        byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
        String decodedString = new String(decodedBytes);
        return decodedString;
    }
}
