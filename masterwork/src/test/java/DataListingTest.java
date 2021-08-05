import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;

@Epic("List products by brand")
public class DataListingTest extends BaseTest {
  HomePage homePage;
  AppleProductsPage appleProductsPage;

  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.navigateToBrands();
  }

  @Step
  public void navigateToAppleProducts() {
    BrandsPage brandsPage = PageFactory.initElements(driver, BrandsPage.class);
    brandsPage.navigateToAppleProducts();
    assertThat(driver.getTitle()).isEqualTo("Apple");
  }

  @Step
  public void sorting() {
    appleProductsPage = PageFactory.initElements(driver, AppleProductsPage.class);
    appleProductsPage.sortBy("Price (High > Low)");
    assertThat(driver.getCurrentUrl())
            .contains("sort=p.price&order=DESC");
  }

  @Test
  @DisplayName("TC6_Data Listing")
  @Feature("Product listing")
  @Story("When listing all products from a brand, and sorting for descending price order, the first price should be the highest.")
  public void shouldListAllAppleProducts() {
    navigateToAppleProducts();
    sorting();
    makeScreenshot();
    assertThat(appleProductsPage.getPrices().get(0)).isEqualTo(appleProductsPage.getMaxPrice());
  }
}
