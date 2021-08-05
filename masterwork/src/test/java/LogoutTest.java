import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class LogoutTest extends BaseTest{
  HomePage homePage;
  LoginPage loginPage;



  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToLoginPage();
    loginPage = PageFactory.initElements(driver, LoginPage.class);
    assertTrue(loginPage.isLoaded());
  }

  public void makeSureLoginIsSuccessful() {
    loginPage.login("herman@gmail.com", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(loginPage.isSuccessful()).isTrue();
  }
  @Test
  @DisplayName("TC13_Logout")
  @Feature("Logout")
  @Story("After successful login, and arriving on the My account page, log out and assert that the title is correct.")
  public void logout() {
    makeSureLoginIsSuccessful();
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.logout();
    makeScreenshot();
    assertThat(driver.getTitle()).isEqualTo("Account Logout");
  }
}
