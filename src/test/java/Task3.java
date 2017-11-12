import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Task3 {

    public WebDriver driver;

    @Before
    public void getToThePage() {
        driver.get("http://localhost/litecart/");
        driver.manage().window().maximize();
    }

    @After

    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void loginToAdminPage() {
        ChromeDriverManager.getInstance().setup();

        WebDriver driver = new ChromeDriver();

        driver.get("http://localhost/litecart/admin/");

        // ligin to the page
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

        List<WebElement> menuItems = driver.findElements(By.cssSelector("#app-"));

        for (int i = 0; menuItems.size() > i; i++) {
            WebElement menuItem = driver.findElement(By.xpath("//*[@id='app-']["+(i+1)+"]"));
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
}
