package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {
  protected static String url;

  public HomePage(WebDriver driver) {
    super(driver);
    url = "http://test-automation-shop2.greenfox.academy/index.php?route=common/home";
  }

  @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a")
  WebElement myAccountDropDown;

  @FindBy(name = "search")
  WebElement searchInput;

  @FindBy(xpath = "//*[@id=\"search\"]/span/button")
  WebElement searchButton;

  @FindBy(linkText = "Brands")
  WebElement brands;

  @FindBy(linkText = "Laptops & Notebooks")
  WebElement laptopsNotebooksDropDown;

  @FindBy(id = "wishlist-total")
  WebElement wishListLink;

  public void open() {
    driver.get(url);
    driver.manage().window().maximize();
    assertThat(isLoaded()).isTrue();
  }

  public boolean isLoaded() {
    return driver.getTitle().equals("Your Store");
  }

  public void navigateToRegistrationPage() {
    myAccountDropDown.click();
    WebElement register = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Register"))));
    register.click();
  }

  public void navigateToLoginPage() {
    myAccountDropDown.click();
    WebElement login = wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.linkText("Login"))));
    login.click();
  }

  public void search(String query) {
    searchInput.sendKeys(query);
    searchButton.click();
  }

  public void navigateToBrands() {
    brands.click();
    assertThat(driver.getTitle()).isEqualTo("Find Your Favorite Brand");
  }

  public void addFeaturedToWishList() {
    List<WebElement> wishListButtons = driver.findElements(By.xpath("//button[@data-original-title ='Add to Wish List']"));
    for (int i = 0; i < wishListButtons.size(); i++) {
      if (wishListButtons.get(i).isEnabled()) {
        wait.until(ExpectedConditions.elementToBeClickable(wishListButtons.get(i)));
        wishListButtons.get(i).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("alert-success")));
        WebElement successMessage = driver.findElement(By.className("alert-success"));
        if (successMessage.isDisplayed() && successMessage.getText()
                .contains(" to your wish list")) {
          JavascriptExecutor jse = (JavascriptExecutor) driver;
          jse.executeScript("scroll(0, 250)");
        }
      }
    }
  }


  public void navigateToWishList() {
    wishListLink.click();
  }
}
