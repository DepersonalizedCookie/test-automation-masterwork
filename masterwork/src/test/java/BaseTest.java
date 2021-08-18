import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;
import pages.LoginPage;
import pages.MyAccountPage;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {
  WebDriver driver;
  WebDriverWait wait;

  @BeforeEach
  public void setup() throws IOException {
    Properties properties = new Properties();
    InputStream resourceAsStream = this.getClass().getResourceAsStream("/test.properties");
    properties.load(resourceAsStream);
    String browser = properties.getProperty("browser");
    if (browser.equals("chrome")) {
      WebDriverManager.chromedriver().setup();
      this.driver = new ChromeDriver();
      wait = new WebDriverWait(driver, 5);
    } else if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      this.driver = new FirefoxDriver();
      wait = new WebDriverWait(driver, 5);
    } else {
      WebDriverManager.edgedriver().setup();
      this.driver = new EdgeDriver();
      wait = new WebDriverWait(driver, 5);
    }

  }

  @Step("Valid login attempt")
  public void makeSureLoginIsSuccessful() {
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    loginPage.login("herman@gmail.com", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(loginPage.isSuccessful()).isTrue();
  }

  @Step("Navigates to the \"Address Book Page\" from the \"My Account Page\".")
  public void navigateToAddressBookPage() {
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.navigateToAddressBook();
  }

  @Step("Navigates to the \"Login Page\" from the \"Home Page\", and asserts that the page is loaded.")
  public void navigateToLoginPage() {
    HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToLoginPage();
    LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
    assertTrue(loginPage.isLoaded());
  }

  @Attachment("Screenshot")
  public byte[] makeScreenshot() {
    return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
  }


  @AfterEach
  public void after() {
    driver.quit();
  }

}