import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import io.github.bonigarcia.wdm.InternetExplorerDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Set;


public class Task7 {
    public static WebDriver driver;
    public static String homeUrl = "http://localhost/litecart/admin";


    @BeforeClass
    public static void startTesting() {
        ChromeDriverManager.getInstance().setup();

        driver = new ChromeDriver();
    }

    @Before
    public void getToBasePage() {

        driver.get(homeUrl);
        // ligin to the page
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        driver.manage().window().maximize();
    }

    @After
    public void stop() {
        driver.quit();
    }

    @Test
    public void verifyLinksAreOpenedInNewWindow() {


        driver.findElement(By.xpath("//div[@id='box-apps-menu-wrapper']//li[3]")).click();
        driver.findElement(By.xpath("//a[@class='button'][contains(text(), ' Add New Country')]")).click();

        List<WebElement> elLinks = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));

        for (int i = 0; i < elLinks.size(); i++) {
            WebDriverWait wait = new WebDriverWait(driver, 5);

            String mainWindow = driver.getWindowHandle();

            Set<String> allWindows = driver.getWindowHandles();

            elLinks.get(i).click();

            String newWindow = wait.until(anyWindowOtherThan(allWindows));
            driver.switchTo().window(newWindow);

            Assert.assertTrue("Link is not working", (newWindow != mainWindow));
            driver.close();
            driver.switchTo().window(mainWindow);

        }


    }

    private ExpectedCondition<String> anyWindowOtherThan(Set<String> windows) {
        return new ExpectedCondition<String>() {
            public String apply(@Nullable WebDriver input) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(windows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
    }

}