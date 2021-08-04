package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class AddressBookPage extends BasePage{
  protected static WebElement deleteButton;

  public AddressBookPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(linkText = "New Address")
  WebElement addNewAddress;

  @FindBy(xpath = "//*[@id=\"account-address\"]/div[1]")
  WebElement successMessage;

  @FindBy(xpath = "//*[@id=\"content\"]/div[1]/table/tbody/tr[1]/td[1]")
  WebElement addressInfo;


  public void navigateToAddNewAddress() {
    addNewAddress.click();
  }

  public String getSuccessMessage() {
    return successMessage.getText();
  }

  public String getAddressInfoText() {
    return addressInfo.getText();
  }

  public void deleteAddressByAddressInfo(String info) {
    deleteButton = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("Delete")));
    if (getAddressInfoText().contains(info)) {
      deleteButton.click();
    }
  }
}
