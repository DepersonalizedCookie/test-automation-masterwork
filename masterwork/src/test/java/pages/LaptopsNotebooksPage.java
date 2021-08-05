package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class LaptopsNotebooksPage extends BasePage {
  @FindBy(tagName = "h4")
  List<WebElement> productNames;

  public LaptopsNotebooksPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getProductNames() {
    List<String> returnList = new ArrayList<>();
    for (WebElement productName : productNames) {
      returnList.add(productName.getText());
    }
    return returnList;
  }
}
