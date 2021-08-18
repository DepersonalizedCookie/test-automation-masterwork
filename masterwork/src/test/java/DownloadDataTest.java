import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.support.PageFactory;
import pages.*;

import java.io.IOException;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

@Epic("List products by Category")
public class DownloadDataTest extends BaseTest {

  @Step("Adds elements to the wishlist")
  public void addToWishlist() {
    MyAccountPage myAccountPage = PageFactory.initElements(driver, MyAccountPage.class);
    myAccountPage.navigateToHomePage();
    HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.addFeaturedToWishList();
  }


  @Step("Navigates to the wishlist from the \"Home Page\".")
  public void goToWishList() {
    HomePage homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.navigateToWishList();
    assertThat(driver.getTitle()).isEqualTo("My Wish List");
  }

  @Step("Writes a file from the wishlist elements names")
  public void writeFile() {
    WishListPage wishListPage = PageFactory.initElements(driver, WishListPage.class);
    List<String> fileContent = wishListPage.getWishListProductsPrices();
    Path filePath = Paths.get("src/test/resources/wishListProductPrices.txt");
    try {
      Files.write(filePath, fileContent);
    } catch (IOException e) {
      System.err.println("Can not write file!");
    }
  }


  @Step("Reads the content of the file, and returns it as a string list")
  public List<String> readFileContent() {
    String folder = "src/test/resources/";
    List<String> result = new ArrayList<>(Arrays.asList("", ""));
    try {
      result = new ArrayList<>(Files.readAllLines(Paths.get(folder + "wishListProductPrices.txt")));
    } catch (IOException e) {
      System.err.println("Can not read file!");
    }
    return result;
  }

  @Test
  @DisplayName("TC12_Data Download")
  @Feature("Product listing")
  @Story("When listing all products from a product category, the products should appear.")
  @Description("When listing all products from a product category, and exporting the product prices to file, the file content should match with the names.")
  public void downloadProductsNames() {
    navigateToLoginPage();
    makeSureLoginIsSuccessful();
    addToWishlist();
    goToWishList();
    writeFile();
    WishListPage wishListPage = PageFactory.initElements(driver, WishListPage.class);
    assertThat(readFileContent()).isEqualTo(wishListPage.getWishListProductsPrices());
  }
}
