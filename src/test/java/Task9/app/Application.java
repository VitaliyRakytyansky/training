package Task9.app;

        import io.github.bonigarcia.wdm.ChromeDriverManager;
        import Task9.pages.CheckPage;
        import Task9.pages.MainPage;
        import Task9.pages.ProductPage;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;

public class Application {

    private WebDriver driver;

    private ProductPage productPage;
    private MainPage mainPage;
    private CheckPage checkOutpage;

    public Application(){
        ChromeDriverManager.getInstance().setup();
        driver = new ChromeDriver();
        productPage = new ProductPage(driver);
        mainPage = new MainPage(driver);
        checkOutpage = new CheckPage(driver);
    }

    public void quit(){
        driver.quit();
    }

    public void addProductToCart(){
        mainPage.open();
        mainPage.selectProduct.click();
        productPage.AddProductToCart();
    }

    public void removeAllProducts(){
        checkOutpage.open();
        checkOutpage.removeAllProducts();
    }

    public boolean isProductTablePresent(){
        return checkOutpage.getProductTable();
    }


}