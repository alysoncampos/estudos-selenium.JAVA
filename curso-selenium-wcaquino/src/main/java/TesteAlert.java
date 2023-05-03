import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    private WebDriver driver;

    @BeforeEach
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();
    }

    @AfterEach
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void deveInteragirComAlertSimples() {
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();

        Assertions.assertEquals("Alert Simples", texto);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        Assertions.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComAlertConfirmAccept() {
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assertions.assertEquals("Confirmado", alert.getText());
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertConfirmDismiss() {
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        Assertions.assertEquals("Negado", alert.getText());
        alert.accept();
    }

    @Test
    public void deveInteragirComAlertConfirmPrompt() {
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("12");
        alert.accept();

        Assertions.assertEquals("Era 12?", alert.getText());
        alert.accept();

        Assertions.assertEquals(":D", alert.getText());
        alert.accept();
    }
}
