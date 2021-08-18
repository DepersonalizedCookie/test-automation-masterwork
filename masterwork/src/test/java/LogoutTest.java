import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;

@Epic("User account management")
public class LogoutTest extends BaseTest{

  @Test
  @DisplayName("TC13_Logout")
  @Feature("Logout")
  @Story("After a successful login attempt and arrival on the \"My Account Page\", if the user logs out the title should be correct ")
  @Description("Makes a valid login attempt, and after arriving on the \"My Account Page\", clicks on the \"Log out\" button, and asserts that the title is correct.")
  public void logout() {
    navigateToLoginPage();
    makeSureLoginIsSuccessful();
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.logout();
    makeScreenshot();
    assertThat(driver.getTitle()).isEqualTo("Account Logout");
  }
}
