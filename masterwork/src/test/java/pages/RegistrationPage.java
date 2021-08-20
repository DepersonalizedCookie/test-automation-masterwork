package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class RegistrationPage extends BasePage {
  protected static WebElement privacyModalHeader;

  @FindBy(name = "firstname")
  WebElement firstnameField;

  @FindBy(name = "lastname")
  WebElement lastnameField;

  @FindBy(name = "email")
  WebElement emailField;

  @FindBy(name = "telephone")
  WebElement telephoneField;

  @FindBy(name = "password")
  WebElement passwordField;

  @FindBy(name = "confirm")
  WebElement confirmField;

  @FindBy(name = "agree")
  WebElement agreeTo;

  @FindBy(xpath = "//*[@id=\"content\"]/form/div/div/input[2]")
  WebElement submitBtn;

  @FindBy(className = "text-danger")
  WebElement failMessage;

  @FindBy(linkText = "Privacy Policy")
  WebElement privacyPolicy;


  public RegistrationPage(WebDriver driver) {
    super(driver);
  }

  public void register(String firstName, String lastName, String emailAddress, String telePhone, String passwd, String confirm) {
    firstnameField.sendKeys(firstName);
    lastnameField.sendKeys(lastName);
    emailField.sendKeys(emailAddress);
    telephoneField.sendKeys(telePhone);
    passwordField.sendKeys(passwd);
    confirmField.sendKeys(confirm);
    agreeTo.click();
    submitBtn.click();
  }

  public boolean isLoaded() {
    return driver.getTitle().equals("Register Account");
  }

  public boolean isSuccessful() {
    return driver.getTitle().equals("Your Account Has Been Created!");
  }

  public boolean isUnsuccessful() {
    return failMessage.getText().equals("Telephone must be between 3 and 32 characters!");
  }

  public void openPrivacyPolicy() {
    privacyPolicy.click();
    privacyModalHeader = wait.until(ExpectedConditions
            .presenceOfElementLocated(By.className("modal-title")));
  }

  public String getPrivacyModalTitle() {
    return privacyModalHeader.getText();
  }

  public void closePrivacyPolicy() {
    WebElement privacyModalClose = wait.until(ExpectedConditions
            .elementToBeClickable(By.xpath("//*[@id=\"modal-agree\"]/div/div/div[1]/button")));
    privacyModalClose.click();
  }

  public void navigateToMyAccount() {
    WebElement continueBtn = wait.until(ExpectedConditions
            .presenceOfElementLocated(By.linkText("Continue")));
    continueBtn.click();
  }
}
