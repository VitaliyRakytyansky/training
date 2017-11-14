import io.github.bonigarcia.wdm.ChromeDriverManager;
import com.google.common.io.Files;
import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.AbstractWebDriverEventListener;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Task8 {

    public static EventFiringWebDriver driver;
    private static WebDriverWait wait;

    @BeforeClass
    public static void getToThePage() {
        ChromeDriverManager.getInstance().setup();
        driver = new EventFiringWebDriver(new ChromeDriver());
        driver.register(new MyListener());

        driver.get("http://localhost/litecart/admin");
        driver.manage().window().maximize();
    }

    @AfterClass

    public static void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loginToAdminPage() {


        // ligin to the page
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("#app-"));

        for (int i = 0; menuItems.size() > i; i++) {
            WebElement menuItem = driver.findElement(By.xpath("//*[@id='app-'][" + (i + 1) + "]"));
            menuItem.click();

            List<WebElement> subItems = driver.findElements(By.cssSelector("#app- ul.docs li"));

            boolean hiIsPresent = driver.findElements(By.cssSelector("#content h1")).size() > 0;
            Assert.assertTrue("H1 is not present", hiIsPresent);

            if (subItems.size() > 0) {
                for (int j = 0; subItems.size() > j; j++) {
                    WebElement subItem = driver.findElements(By.cssSelector("#app- ul.docs li")).get(j);
                    subItem.click();

                    Assert.assertTrue("H1 is not present", hiIsPresent);
                }
            }
        }
    }

    public static class MyListener extends AbstractWebDriverEventListener {
        @Override
        public void afterFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println("Start searching " + by);
        }

        ;

        @Override
        public void beforeFindBy(By by, WebElement element, WebDriver driver) {
            System.out.println(by + " found");
        }

        ;

        @Override
        public void onException(Throwable throwable, WebDriver driver) {
            File tempFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            try {
                Files.copy(tempFile, new File("screen.png"));

            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(throwable);
        }

        ;
    }
}

