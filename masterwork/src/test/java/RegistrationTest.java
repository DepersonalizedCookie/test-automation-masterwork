import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@Epic("User account management")
public class RegistrationTest extends BaseTest {
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
  @Feature("Registration")
  @DisplayName("TC1_Valid registration")
  @Story("After a registration attempt with valid credentials we should arrive on the \"My Account Page\".")
  @Description("Attempts registration with valid credentials, makes a screenshot of the \"My Account Page\" and asserts the title is correct.")
  public void shouldRegister() {
    String email = "holographic" + System.currentTimeMillis() + "@gmail.com";
    registrationPage.register("Martha", "Lewis", email,
            "46791", "KidnapTheSandyClaws", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(registrationPage.isSuccessful()).isTrue();
  }

  @Test
  @Feature("Registration")
  @DisplayName("TC2_Invalid registration")
  @Story("After a registration attempt with invalid credentials, the error message should be correct.")
  @Description("Attempts registration with invalid credentials, makes a screenshot of the error message, and asserts that the error message is correct")
  void shouldNotRegister() {
    String email = "herman" + System.currentTimeMillis() + "@gmail.com";
    registrationPage.register("Peter", "Herrmann", email,
            "55", "KidnapTheSandyClaws", "KidnapTheSandyClaws");
    makeScreenshot();
    assertThat(registrationPage.isUnsuccessful()).isTrue();
  }
}
