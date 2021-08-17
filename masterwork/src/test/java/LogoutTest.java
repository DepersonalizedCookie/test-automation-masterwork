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

  @Test
  @DisplayName("TC13_Logout")
  @Feature("Logout")
  @Story("After a successful login attempt and arrival on the \"My Account Page\", if the user logs out the title should be correct ")
  @Description("Makes a valid login attempt, and after arriving on the \"My Account Page\", clicks on the \"Log out\" button, and asserts that the title is correct.")
  public void logout() {
    makeSureLoginIsSuccessful();
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.logout();
    makeScreenshot();
    assertThat(driver.getTitle()).isEqualTo("Account Logout");
  }
}
