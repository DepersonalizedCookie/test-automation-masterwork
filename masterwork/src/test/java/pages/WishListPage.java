package pages;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
public class WishListPage extends BasePage{
  @FindBy(className = "price")
  List<WebElement> wishListContent;

  public WishListPage(WebDriver driver) {
    super(driver);
  }

  public List<String> getWishListProductsPrices() {
    List<String> returnList = new ArrayList<>();
    for (WebElement wishListElement : wishListContent) {
        returnList.add(wishListElement.getText());
      }
    return returnList;
  }
}

