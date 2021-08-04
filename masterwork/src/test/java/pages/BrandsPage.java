package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class BrandsPage extends BasePage{
  @FindBy(linkText = "Apple")
  WebElement apple;
  public BrandsPage(WebDriver driver) {
    super(driver);
  }

  public void navigateToAppleProducts() {
    apple.click();
  }
}
