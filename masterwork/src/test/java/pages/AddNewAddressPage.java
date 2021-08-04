package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;


public class AddNewAddressPage extends BasePage {

  public AddNewAddressPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(id = "input-firstname")
  WebElement firstNameField;

  @FindBy(id = "input-lastname")
  WebElement lastNameField;

  @FindBy(id = "input-address-1")
  WebElement address1Field;

  @FindBy(id = "input-city")
  WebElement cityField;

  @FindBy(id = "input-postcode")
  WebElement postCodeField;

  @FindBy(id = "input-country")
  WebElement countryField;

  @FindBy(id = "input-zone")
  WebElement stateField;

  @FindBy(xpath = "//*[@id=\"content\"]/form/div/div[2]/input")
  WebElement buttonContinue;

  public void addAddress(String firstname, String lastname, String address1, String city, String postcode, String country, String state) {
    firstNameField.sendKeys(firstname);
    lastNameField.sendKeys(lastname);
    address1Field.sendKeys(address1);
    cityField.sendKeys(city);
    postCodeField.sendKeys(postcode);
    Select selectCountry = new Select(countryField);
    selectCountry.selectByVisibleText(country);
    Select selectState = new Select(stateField);
    wait.until(ExpectedConditions.textToBePresentInElement(stateField, state));
    selectState.selectByVisibleText(state);
    buttonContinue.click();
  }

  public boolean isSuccessful() {
   return driver.getTitle().equals("Address Book");
  }
}
