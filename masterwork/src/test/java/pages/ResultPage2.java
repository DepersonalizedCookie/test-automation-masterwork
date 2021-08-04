package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ResultPage2 extends BasePage{
  @FindBy(linkText = "1")
  WebElement previousPageButton;

  @FindBy(xpath = "//*[@id=\"content\"]/div[4]/div[2]")
  WebElement showingInfo;

  public ResultPage2(WebDriver driver) {
    super(driver);
  }

  public void navigateBackToPreviousPage() {
    previousPageButton.click();
  }
  public String getInfo() {
    return showingInfo.getText();
  }
}
