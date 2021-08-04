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
  @Story("After a registration attempt with valid credentials assert that the title is correct.")
  public void shouldRegister() {
    String email = "holographic" + Math.random() + "@gmail.com";
    registrationPage.register("Martha", "Lewis", email,
            "46791", "KidnapTheSandyClaws", "KidnapTheSandyClaws");
    assertThat(registrationPage.isSuccessful()).isTrue();
  }

  @Test
  @Feature("Registration")
  @DisplayName("TC2_Invalid registration")
  @Story("After a registration attempt with invalid credentials, assert that the error message is correct.")
  void shouldNotRegister() {
    registrationPage.register("Peter", "Herrmann", "herman@gmail.com",
            "5555", "KidnapTheSandyClaws", "KidnapTheSandyClaws");
    assertThat(registrationPage.isUnsuccessful()).isTrue();
  }
}
