import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class AddNewDataTest extends BaseTest {
  HomePage homePage;
  RegistrationPage registrationPage;
  MyAccountPage myAccountPage;
  MyAffiliateAccountPage myAffiliateAccountPage;

  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToRegistrationPage();
    registrationPage = PageFactory.initElements(driver, RegistrationPage.class);
    assertThat(registrationPage.isLoaded()).isTrue();
  }

  @Step("After a registration attempt with valid credentials assert that the title is correct.")
  public void makeSureRegistrationIsSuccessful() {
    String email = "mccrory" + System.currentTimeMillis()+ "@gmail.com";
    registrationPage.register("Colet", "McCrory", email, "320-538-9567",
            "reTdeJ", "reTdeJ");
    assertTrue(registrationPage.isSuccessful());
  }

  @Test
  @DisplayName("TC8_Adding New Data")
  @Feature("Affiliate account signup")
  @Story("Successful affiliate registration with check payee name only.")
  public void makeNewAffiliateAccount() {
    makeSureRegistrationIsSuccessful();
    registrationPage.navigateToMyAccount();
    myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.navigateToAffiliateAccount();
    myAffiliateAccountPage = PageFactory
            .initElements(driver, MyAffiliateAccountPage.class);
    makeScreenshot();
    myAffiliateAccountPage.newAffiliateAccount("Bruce Banner");
    makeScreenshot();
    assertThat(myAccountPage.getSuccessMessage())
            .isEqualTo("Success: Your account has been successfully updated.");
  }
}
