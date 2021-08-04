package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAffiliateAccountPage extends BasePage{
  @FindBy(id = "input-cheque")
  WebElement checkPayee;

  @FindBy(css = "#content > form > div > div > input[type=checkbox]:nth-child(2)")
  WebElement agreeTo;

  @FindBy(css = "#content > form > div > div > input.btn.btn-primary")
  WebElement button;

  public MyAffiliateAccountPage(WebDriver driver) {
    super(driver);
  }

  public void newAffiliateAccount(String payeeName) {
    checkPayee.sendKeys(payeeName);
    agreeTo.click();
    button.click();
    assertThat(driver.getTitle()).isEqualTo("My Account");
  }
}
