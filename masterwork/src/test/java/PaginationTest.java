import io.qameta.allure.*;
import org.junit.jupiter.api.*;
import org.openqa.selenium.support.PageFactory;
import pages.*;

import static org.assertj.core.api.Assertions.*;


@Epic("Website navigation")
public class PaginationTest extends BaseTest {
  HomePage homePage;

  @BeforeEach
  void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
    homePage.search(" ");
    assertThat(driver.getTitle()).isEqualTo("Search -");
  }

  @Test
  @DisplayName("TC7_Pagination")
  @Feature("Search")
  @Story("When making a search, should be able to navigate between results.")
  @Description("When making a search, and navigating between two result pages, url and page indicator should be correct.")
  void shouldNavigateBetweenResultPages() {
    ResultPage1 resultPage1 = PageFactory.initElements(driver, ResultPage1.class);
    makeScreenshot();
    resultPage1.navigateToNextPage();
    ResultPage2 resultPage2 = PageFactory.initElements(driver, ResultPage2.class);
    assertThat(driver.getCurrentUrl()).contains("page=2");
    makeScreenshot();
    resultPage2.navigateBackToPreviousPage();
    assertThat(resultPage1.getFirstPage().getCssValue("background-color"))
            .isEqualTo("rgba(51, 122, 183, 1)");
  }
}
