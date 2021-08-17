import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("new-address")
@DisplayName("TC9_Create new address")
@Epic("Address book management")
public class RepetitiveDataEntryTest extends BaseTest {
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

  @ParameterizedTest(name = "TC9_Create new address - Fistname = {0}, Lastname = {1}, Address1 = {2}, City = {3}, Postcode = {4}, Country = {5}, State = {6}")
  @Tag("addresses")
  @Feature("Adding new address")
  @Story("After adding an address, the success message should be correct.")
  @Description("Adds new addresses from a CSV file source, and asserts that the success message is correct.")
  @CsvFileSource(resources = "addresses.csv")
  void shouldCreateNewAddress(String firstname, String lastname, String address1, String city, String postcode, String country, String state) {
    makeSureLoginIsSuccessful();
    navigateToAddressBookPage();
    AddressBookPage addressBookPage = PageFactory.initElements(driver, AddressBookPage.class);
    addressBookPage.navigateToAddNewAddress();
    AddNewAddressPage addNewAddressPage = PageFactory.initElements(driver, AddNewAddressPage.class);
    addNewAddressPage.addAddress(firstname, lastname, address1, city, postcode, country, state);
    makeScreenshot();
    assertThat(addNewAddressPage.isSuccessful()).isTrue();
    makeScreenshot();
    assertThat(addressBookPage.getSuccessMessage()).isEqualTo("Your address has been successfully added");
  }

}