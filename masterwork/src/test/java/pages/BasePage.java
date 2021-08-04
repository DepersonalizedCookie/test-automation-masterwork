package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
  protected static WebDriver driver;
  protected static WebDriverWait wait;
  public BasePage(WebDriver driver) {
    this.driver = driver;
    wait = new WebDriverWait(driver, 10);
  }
}
