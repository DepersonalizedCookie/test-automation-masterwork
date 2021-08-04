package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class ResultPage1 extends BasePage {
  @FindBy(linkText = "2")
  WebElement nextPageButton;

  @FindBy(xpath = "//*[@id=\"content\"]/div[4]/div[2]")
  WebElement showingInfo;

  @FindBy(xpath = "//*[@id=\"content\"]/div[4]/div[1]/ul/li[1]/span")
  WebElement firstPage;


  public ResultPage1(WebDriver driver) {
    super(driver);
  }

  public void navigateToNextPage() {
    nextPageButton.click();
  }

  public String getInfo() {
    return showingInfo.getText();
  }

  public WebElement getFirstPage() {
    return firstPage;
  }
}
