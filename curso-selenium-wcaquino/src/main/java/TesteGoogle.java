import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteGoogle {

    private WebDriver driver;

    @BeforeEach
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @AfterEach
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void teste() {
        driver.get("http://www.google.com");
        driver.manage().window().maximize();
        Assertions.assertEquals("Google", driver.getTitle());
    }
}