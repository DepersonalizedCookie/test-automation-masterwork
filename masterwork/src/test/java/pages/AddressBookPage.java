package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;


public class AddressBookPage extends BasePage {

  public AddressBookPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(linkText = "New Address")
  WebElement addNewAddress;

  @FindBy(xpath = "//*[@id=\"account-address\"]/div[1]")
  WebElement successMessage;


  public void navigateToAddNewAddress() {
    addNewAddress.click();
  }

  public String getSuccessMessage() {
    return successMessage.getText();
  }

  public List<String> getAddressInfoText() {
    List<WebElement> addressInfoList = driver.findElements(By.className("text-left"));
    List<String> getInfo = new ArrayList<>();
    for (WebElement addressInfo : addressInfoList) {
      getInfo.add(addressInfo.getText());
    }
    return getInfo;
  }

  public void deleteAddressByAddressInfo(String info) {
    List<String> getInfo = getAddressInfoText();
    for (int i = 0; i < getInfo.size(); i++) {
      if (getInfo.get(i).contains(info)) {
        List<WebElement> deleteButtons = driver.findElements(By.linkText("Delete"));
        wait.until(ExpectedConditions.elementToBeClickable(deleteButtons.get(i)));
        deleteButtons.get(i).click();
        break;
      }
    }
  }
}

