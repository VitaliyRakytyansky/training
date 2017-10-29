import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Task4 {

    public static WebDriver driver;

    public Task4() {
        ChromeDriverManager.getInstance().setup();
        //InternetExplorerDriverManager.getInstance().arch32().setup();
        //FirefoxDriverManager.getInstance().setup();

        driver = new ChromeDriver();
        //driver = new InternetExplorerDriver();
        //driver = new FirefoxDriver();
    }

    @Before
    public void getToThePage() {
        driver.get("http://localhost/litecart/");
        driver.manage().window().maximize();
    }

    @Test
    public void verifyProductNameTest() {

        String duckItemTextMainPage = driver.findElement(By.cssSelector("#box-campaigns .name")).getText();

        //go to product page
        driver.findElement(By.cssSelector("#box-campaigns img")).click();

        String duckItemTextItemPage = driver.findElement(By.cssSelector("h1[class = 'title']")).getText();

        Assert.assertEquals("Product Name is not equal on Main page and Item Page", duckItemTextMainPage, duckItemTextItemPage);

    }

    @Test
    public void verifyRegularPriceTest() {

        String regularPriceTextOnMainPage = driver.findElement(By.cssSelector("div#box-campaigns .regular-price ")).getText();

        //go to product page
        driver.findElement(By.cssSelector("#box-campaigns img")).click();

        String regularPriceTextOnProductPage = driver.findElement(By.cssSelector(".information .regular-price ")).getText();

        Assert.assertEquals("Regular price value on main page is not the same as on product page.", regularPriceTextOnMainPage, regularPriceTextOnProductPage);

    }

    @Test
    public void verifyDiscountPriceTest() {
        String discountPriceTextOnMainPage = driver.findElement(By.cssSelector("div#box-campaigns .campaign-price ")).getText();

        //go to product page
        driver.findElement(By.cssSelector("#box-campaigns img")).click();

        String discountPriceTextOnProductPage = driver.findElement(By.cssSelector(".information .campaign-price ")).getText();

        Assert.assertEquals("Discount price value on main page is not the same as on product page.", discountPriceTextOnMainPage, discountPriceTextOnProductPage);
    }

    @Test

    public void verifyRegularPriceFontOnMainPage() {

        String regularPriceFontColor = driver.findElement(By.cssSelector("div#box-campaigns .regular-price ")).getCssValue("color");
        String regularPriceFontDecoration = driver.findElement(By.cssSelector("div#box-campaigns .regular-price ")).getCssValue("text-decoration-line");
        //rgba(119, 119, 119, 1)
        //line-through

        Assert.assertEquals("Regular price text color on main page is not gray", "rgba(119, 119, 119, 1)", regularPriceFontColor);
        Assert.assertEquals("Regular price text color on main page is not line-through", "line-through", regularPriceFontDecoration);

    }

    @Test

    public void verifyRegularPriceFontOnProductPage() {
        //go to product page
        driver.findElement(By.cssSelector("#box-campaigns img")).click();

        String discountPriceFontColor = driver.findElement(By.cssSelector(".information .regular-price")).getCssValue("color");
        String discountPriceFontDecoration = driver.findElement(By.cssSelector(".information .regular-price ")).getCssValue("text-decoration-line");
        //rgba(102, 102, 102, 1)

        Assert.assertEquals("Regular price text color on main page is not gray", "rgba(102, 102, 102, 1)", discountPriceFontColor);
        Assert.assertEquals("Regular price text color on main page is not line-through", "line-through", discountPriceFontDecoration);
    }

    @Test

    public void verifyDiscountPriceFontOnMainPage() {

        String discountPriceFontColor = driver.findElement(By.cssSelector("div#box-campaigns .campaign-price ")).getCssValue("color");
        String discountPriceFontDecoration = driver.findElement(By.cssSelector("div#box-campaigns .campaign-price ")).getCssValue("font-weight");
        //rgba(204, 0, 0, 1)
        //bold

        Assert.assertEquals("Discount price text color on main page is not red", "rgba(204, 0, 0, 1)", discountPriceFontColor);
        Assert.assertEquals("Discount price text style on main page is not bold.", "bold", discountPriceFontDecoration);
    }

    @Test

    public void verifyDiscountPriceFontOnProductPage() {

        //go to product page
        driver.findElement(By.cssSelector("#box-campaigns img")).click();

        String discountPriceFontColor = driver.findElement(By.cssSelector(".information .campaign-price ")).getCssValue("color");
        String discountPriceFontDecoration = driver.findElement(By.cssSelector(".information .campaign-price ")).getCssValue("font-weight");
        //rgba(204, 0, 0, 1)
        //bold

        Assert.assertEquals("Discount price text color on product page is not red.", "rgba(204, 0, 0, 1)", discountPriceFontColor);
        Assert.assertEquals("Discount price text style on product page is not bold.", "bold", discountPriceFontDecoration);
    }

    @After

    public void closeBrowser(){
        driver.close();
    }

}

