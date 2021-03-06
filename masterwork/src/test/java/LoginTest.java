import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;


@Epic("User account management")
public class LoginTest extends BaseTest {
  LoginPage loginPage;

  @Step("Initializes the \"Login Page\".")
  public void setUpPage() {
    loginPage = PageFactory.initElements(driver, LoginPage.class);
  }

  @Test
  @Feature("Login")
  @DisplayName("TC3_Valid Login")
  @Story("After a login attempt with valid credentials, assert that the title is the My Account Page title.")
  public void validLogin() {
    navigateToLoginPage();
    setUpPage();
    loginPage.login("herman@gmail.com", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(loginPage.isSuccessful()).isTrue();
  }

  @Test
  @Feature("Login")
  @DisplayName("TC4_Invalid Login")
  @Story("After a login attempt with invalid credentials, assert that the error message is correct.")
  public void invalidLogin() {
    navigateToLoginPage();
    setUpPage();
    loginPage.login("belongsToNoOne@gmail.com", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(loginPage.isUnsuccessful()).isTrue();
  }
}
