import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.internal.WebElementToJsonConverter;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Task5 {

    public WebDriver driver;

    public Task5() {
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
    }

    @Before
    public void getToThePage() {
        driver.get("http://localhost/litecart/admin");

        // ligin to the page
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().window().maximize();

    }

    @Test
    public void addNewItemTest() {


        driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']//li[2]")).click();
        driver.findElement(By.xpath("//a[@class='button'][contains(text(), ' Add New Product')]")).click();

        List<WebElement> radioButton = driver.findElements(By.name("status"));

        boolean rb = false;

        rb = radioButton.get(0).isSelected();

        if (rb != true) {
            radioButton.get(0).click();
        }

        driver.findElement(By.name("name[en]")).sendKeys("Vitaliy");
        driver.findElement(By.name("code")).sendKeys("053");

        List<WebElement> categoriesCheckBox = driver.findElements(By.name("categories[]"));

        for (int i = 0; i < categoriesCheckBox.size() - 1; i++) {
            boolean cb = categoriesCheckBox.get(i).isSelected();
            if (cb == true)
                categoriesCheckBox.get(i + 1).click();
        }

        Select dropDown = new Select(driver.findElement(By.name("default_category_id")));

        dropDown.getOptions().get(1).click();

        List<WebElement> productGroupsCheckBox = driver.findElements(By.name("product_groups[]"));

        int genderSize = productGroupsCheckBox.size();

        for (int i = 0; i < genderSize; i++) {
            productGroupsCheckBox.get(i).click();
        }

        driver.findElement(By.name("quantity")).clear();
        driver.findElement(By.name("quantity")).sendKeys("9,00");

        driver.findElement(By.name("new_images[]")).sendKeys("F:\\Photo\\86\\1.jpg");

        driver.findElement(By.name("date_valid_from")).sendKeys("25022016");
        driver.findElement(By.name("date_valid_to")).sendKeys("25022018");

        // Information tab

        driver.findElement(By.xpath("//a[@href='#tab-information']")).click();
        driver.findElements(By.cssSelector("ul.index li")).get(1).click();
        Select elManufacturer = new Select(driver.findElement(By.name("manufacturer_id")));

        elManufacturer.selectByIndex(1);

        driver.findElement(By.className("trumbowyg-editor")).sendKeys("Trial by combat.");
        driver.findElement(By.name("head_title[en]")).sendKeys("Winter is coming");

        // Prices tab
        driver.findElement(By.xpath("//a[@href='#tab-prices']")).click();
        driver.findElement(By.name("purchase_price")).sendKeys(Keys.DELETE + "5");
        Select elCurrency = new Select(driver.findElement(By.name("purchase_price_currency_code")));

        elCurrency.selectByIndex(1);

        driver.findElement(By.name("prices[USD]")).sendKeys("10");
        driver.findElement(By.name("prices[EUR]")).sendKeys("20");

        driver.findElement(By.name("save")).click();

        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("table.dataTable")));
        Boolean isProductAdded = driver.findElements(By.xpath("//td/a[contains(text(), 'Vitaliy')]")).size() > 0;

        Assert.assertTrue("A new product has not been added to the Catalog.", isProductAdded);

    }

}


