import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("Website UI")
public class PrivacyPolicyTest extends BaseTest {
  HomePage homePage;
  RegistrationPage registrationPage;

  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToRegistrationPage();
    registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    assertTrue(registrationPage.isLoaded());
  }

  @Test
  @Feature("Registration menu")
  @DisplayName("TC5_Privacy Statement Inspection")
  @Story("After opening the privacy policy modal, the modal title is correct.")
  public void privacyStatementInspection() {
    registrationPage.openPrivacyPolicy();
    assertThat(registrationPage.getPrivacyModalTitle()).isEqualTo(
            "Privacy Policy");
    registrationPage.closePrivacyPolicy();
  }
}
