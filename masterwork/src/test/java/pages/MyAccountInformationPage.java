package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountInformationPage extends BasePage {

  public MyAccountInformationPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "input-lastname")
  WebElement lastName;

  @FindBy(xpath = "//*[@id=\"content\"]/form/div/div[2]/input")
  WebElement button;

  public void modifyLastName(String newLastName) {
    lastName.clear();
    lastName.sendKeys(newLastName);
    button.click();
    assertThat(driver.getTitle()).isEqualTo("My Account");
  }
}
