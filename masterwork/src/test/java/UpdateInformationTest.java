import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("User account management")
public class UpdateInformationTest extends BaseTest {
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
  @DisplayName("TC10_Update Data")
  @Feature("Changing user account information.")
  @Story("After changing user account information, the success message should be correct.")
  @Description("Logs in with valid credentials , navigates to edit account settings, modifies the last name, and asserts that the success message is correct.")
  void updateInformation() {
    makeSureLoginIsSuccessful();
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.navigateToEditAccount();
    MyAccountInformationPage myAccountInformationPage = PageFactory
            .initElements(driver, MyAccountInformationPage.class);
    makeScreenshot();
    myAccountInformationPage.modifyLastName("Herrman");
    makeScreenshot();
    assertThat(myAccountPage.getSuccessMessage())
            .isEqualTo("Success: Your account has been successfully updated.");
  }


}