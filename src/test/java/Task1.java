import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Task1 {

    @Test
    public void googleTest()
    {
        ChromeDriverManager.getInstance().setup();
        WebDriver driver = new ChromeDriver();

        driver.get("http://www.google.com");

        driver.findElement(By.xpath("//*[@id='lst-ib']")).sendKeys("Webdriver");

        driver.quit();
    }
}
