package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class HomePage extends BasePage {
  protected static String url;

  public HomePage(WebDriver driver) {
    super(driver);
    url = "http://test-automation-shop2.greenfox.academy/index.php?route=common/home";
  }

  @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a")
  private WebElement myAccountDropDown;

  @FindBy(linkText = "Login")
  private WebElement login;

  @FindBy(linkText = "Register")
  private WebElement register;

  @FindBy(name = "search")
  WebElement searchInput;

  @FindBy(xpath = "//*[@id=\"search\"]/span/button")
  WebElement searchButton;

  @FindBy(linkText = "Brands")
  WebElement brands;

  @FindBy(linkText = "Laptops & Notebooks")
  WebElement laptopsNotebooksDropDown;


  public void open() {
    driver.get(url);
    driver.manage().window().maximize();
    assertThat(isLoaded()).isTrue();
  }

  public boolean isLoaded() {
    return driver.getTitle().equals("Your Store");
  }


  private WebElement getRegister() {
    return register;
  }

  private WebElement getLogin() {
    return login;
  }

  private WebElement getMyAccountDropDown() {
    return myAccountDropDown;
  }

  public void navigateToRegistrationPage() {
    getMyAccountDropDown().click();
    wait.until(ExpectedConditions.elementToBeClickable(getRegister()));
    getRegister().click();
  }

  public void navigateToLoginPage() {
    getMyAccountDropDown().click();
    wait.until(ExpectedConditions.elementToBeClickable(getLogin()));
    getLogin().click();
  }

  public void search(String query) {
    searchInput.sendKeys(query);
    searchButton.click();
  }

  public void navigateToBrands() {
    brands.click();
    assertThat(driver.getTitle()).isEqualTo("Find Your Favorite Brand");
  }

  public void getAllLaptopsNoteBooks() {
    laptopsNotebooksDropDown.click();
    WebElement showAll = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Show All Laptops & Notebooks")));
    showAll.click();
  }


}
