import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;

@Epic("Address book management")
public class DeleteDataTest extends BaseTest {
  HomePage homePage;

  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToLoginPage();
  }

  @Step("Adds a new address.")
  public void addNewAddress() {
    AddressBookPage addressBookPage = PageFactory
            .initElements(driver, AddressBookPage.class);
    addressBookPage.navigateToAddNewAddress();
    AddNewAddressPage addNewAddressPage = PageFactory
            .initElements(driver, AddNewAddressPage.class);
    addNewAddressPage.addAddress(
            "Lynne", "Ousby", "9 Meadow Ridge Junction", "Stockholm", "114 29", "Sweden", "Stockholm");
    makeScreenshot();
    assertThat(addressBookPage.getSuccessMessage())
            .isEqualTo("Your address has been successfully added");
  }

  @Test
  @Feature("Delete address")
  @DisplayName("TC11_Delete data")
  @Story("After deleting the address, the success message should be correct.")
  @Description("Deletes an address by address info, and asserts that the success message is correct.")
  public void deleteAddressTest() {
    makeSureLoginIsSuccessful();
    navigateToAddressBookPage();
    addNewAddress();
    AddressBookPage addressBookPage = PageFactory
            .initElements(driver, AddressBookPage.class);
    addressBookPage.deleteAddressByAddressInfo("Sweden");
    assertThat(addressBookPage.getSuccessMessage())
            .isEqualTo("Your address has been successfully deleted");
  }
}
