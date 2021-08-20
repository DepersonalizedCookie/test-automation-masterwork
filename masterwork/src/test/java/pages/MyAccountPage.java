package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.assertj.core.api.Assertions.assertThat;

public class MyAccountPage extends BasePage {

  public MyAccountPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(xpath = "//*[@id=\"top-links\"]/ul/li[2]/a")
  WebElement myAccountDropdown;

  @FindBy(linkText = "Edit your account information")
  WebElement editAccount;

  @FindBy(linkText = "Register for an affiliate account")
  WebElement affiliateAccount;

  @FindBy(linkText = "Modify your address book entries")
  WebElement addressBook;

  @FindBy(xpath = "//*[@id=\"account-account\"]/div[1]")
  WebElement successMessage;

  @FindBy(linkText = "Your Store")
  WebElement homePageLink;


  public boolean isLoaded() {
    return driver.getTitle().equals("My Account");
  }

  public void logout() {
    myAccountDropdown.click();
    WebElement logout = wait.until(ExpectedConditions
            .elementToBeClickable(By.linkText("Logout")));
    logout.click();
  }

  public void navigateToEditAccount() {
    editAccount.click();
    assertThat(driver.getTitle()).isEqualTo("My Account Information");
  }

  public String getSuccessMessage() {
    return successMessage.getText();
  }

  public void navigateToAddressBook() {
    addressBook.click();
    assertThat(driver.getTitle()).isEqualTo("Address Book");
  }

  public void navigateToAffiliateAccount() {
    affiliateAccount.click();
    assertThat(driver.getTitle()).isEqualTo("Your Affiliate Information");
  }

  public void navigateToHomePage() {
    homePageLink.click();
  }
}
