package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class AppleProductsPage extends BasePage {
  @FindBy(id = "input-sort")
  WebElement sortBy;

  @FindBy(className = "price-tax")
  List<WebElement> prices;

  public AppleProductsPage(WebDriver driver) {
    super(driver);
  }

  public void sortBy(String category) {
    Select selectCategory = new Select(sortBy);
    selectCategory.selectByVisibleText(category);
  }

  private List<String> getPricesString() {
    List<String> returnPrices = new ArrayList<>();

    for (WebElement price : prices) {
      String priceString = price.getText().substring(9);
      priceString = priceString.replace(",", "");
      returnPrices.add(priceString);
    }
    return returnPrices;
  }


  public List<Float> getPrices() {
    List<String> stringPrices = getPricesString();
    List<Float> prices = new ArrayList<>();
    for (String stringPrice : stringPrices) {
      prices.add(Float.parseFloat(stringPrice));
    }
    return prices;
  }

  public float getMaxPrice() {
    List<Float> prices = getPrices();
    Float maxPrice = 0.0F;
    for (Float price : prices) {
      if (price > maxPrice) {
        maxPrice += price;
      }
    }
    return maxPrice;
  }
}
