import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


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
      wait = new WebDriverWait(driver,5);
    } else if (browser.equals("firefox")) {
      WebDriverManager.firefoxdriver().setup();
      this.driver = new FirefoxDriver();
      wait = new WebDriverWait(driver,5);
    } else {
      WebDriverManager.edgedriver().setup();
      this.driver = new EdgeDriver();
      wait = new WebDriverWait(driver,5);
    }

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