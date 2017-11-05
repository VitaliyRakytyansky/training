import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Task6 {

    public static WebDriver driver;
    public static String homeUrl = "http://localhost/litecart/";


    @BeforeClass

    public static void startTesting() {
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
    }

    @Before
    public void getToBasePage() {
        driver.get(homeUrl);
        driver.manage().window().maximize();
    }


    @Test
    public void addAndRemoveProductsTest() {
        Integer startQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());

        WebDriverWait wait = new WebDriverWait(driver, 5);
        int purchaseNumber = 3;

        for (int i = 0; i < purchaseNumber; i++) {
            addProductToChart();

        }

        String poductQuantity = driver.findElement(By.cssSelector("span.quantity")).getText();
        Assert.assertEquals("Number of products in the cart differs from expected", poductQuantity, Integer.toString(startQuantity + purchaseNumber));

        driver.findElement(By.xpath("//div[@id='cart-wrapper']//a[3]")).click();

        while (driver.findElements(By.cssSelector("tr .header")).size() != 0) {

            driver.findElements(By.xpath("//*[@id='box-checkout-cart']//button")).get(1).click();

            wait.until(ExpectedConditions.stalenessOf(driver.findElement(By.cssSelector("tr.header"))));
        }

        driver.navigate().to(homeUrl);
        String removedQuantity = driver.findElement(By.cssSelector("span.quantity")).getText();
        Assert.assertEquals("Not all products have been removed from the cart.", "0", removedQuantity);

    }


    private void addProductToChart() {

        WebDriverWait wait = new WebDriverWait(driver, 5);

        driver.findElements(By.cssSelector("li.product.column.shadow.hover-light")).get(0).click();


        if (driver.findElements(By.cssSelector("select[name = 'options[Size]'")).size() != 0) {
            Select dropDown = new Select(driver.findElement(By.cssSelector("select[name = 'options[Size]'")));
            dropDown.selectByIndex(1);
        }

        WebElement quantityElement = driver.findElement(By.cssSelector("span.quantity"));
        Integer primaryQuantity = Integer.parseInt(driver.findElement(By.cssSelector("span.quantity")).getText());
        String realQuantity = Integer.toString(primaryQuantity + 1);

        driver.findElement(By.name("add_cart_product")).click();
        wait.until(ExpectedConditions.textToBePresentInElement(quantityElement, realQuantity));
        driver.navigate().back();


    }

}





