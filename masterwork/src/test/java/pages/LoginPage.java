package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

  @FindBy(id = "input-email")
  WebElement inputEmail;

  @FindBy(id = "input-password")
  WebElement inputPassword;

  @FindBy(xpath = "//*[@id=\"content\"]/div/div[2]/div/form/input")
  WebElement loginBtn;

  @FindBy(xpath = "//*[@id=\"account-login\"]/div[1]")
  WebElement failMessage;

  public LoginPage(WebDriver driver) {
    super(driver);
  }

  public void login(String email, String password) {
    inputEmail.sendKeys(email);
    inputPassword.sendKeys(password);
    loginBtn.click();
  }

  public boolean isLoaded() {
    return driver.getTitle().equals("Account Login");
  }

  public boolean isSuccessful() {
    return driver.getTitle().equals("My Account");
  }

  public boolean isUnsuccessful() {
    return failMessage.getText().equals("Warning: No match for E-Mail Address and/or Password.");
  }
}
