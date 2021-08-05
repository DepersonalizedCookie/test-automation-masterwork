import io.qameta.allure.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.openqa.selenium.support.PageFactory;
import pages.HomePage;
import pages.LaptopsNotebooksPage;

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
  HomePage homePage;

  @BeforeEach
  public void setUpPreconditions() {
    homePage = PageFactory.initElements(driver, HomePage.class);
    homePage.open();
  }

  @Step
  public void getAllLapTopsAndNoteBooks() {
    homePage.getAllLaptopsNoteBooks();
    assertThat(driver.getTitle()).isEqualTo("Laptops & Notebooks");
  }

  @Step
  public void writeFile() {
    LaptopsNotebooksPage laptopsNotebooksPage = PageFactory.initElements(driver, LaptopsNotebooksPage.class);
    List<String> fileContent = laptopsNotebooksPage.getProductNames();
    Path filePath = Paths.get("src/test/resources/productNames.txt");
    try {
      Files.write(filePath, fileContent);
    } catch (IOException e) {
      System.err.println("Can not write file!");
    }
  }

  @Step
  public List<String> readFileContent() {
    String folder = "src/test/resources/";
    List<String> result = new ArrayList<>(Arrays.asList("", ""));
    try {
      result = new ArrayList<>(Files.readAllLines(Paths.get(folder + "productNames.txt")));
    } catch (IOException e) {
      System.err.println("Can not read file!");
    }
    return result;
  }

  @Test
  @DisplayName("TC12_Data Download")
  @Feature("Product listing")
  @Story("When listing all products from a product category, the products should appear.")
  @Description("When listing all products from a product category, and exporting the product names to file, the file content should match with the names.")
  public void downloadProductsNames() {
    getAllLapTopsAndNoteBooks();
    writeFile();
    LaptopsNotebooksPage laptopsNotebooksPage = PageFactory.initElements(driver, LaptopsNotebooksPage.class);
    assertThat(readFileContent()).isEqualTo(laptopsNotebooksPage.getProductNames());
  }
}
