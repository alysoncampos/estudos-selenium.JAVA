import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

    @Test
    public void deveInteragirComAlertSimples() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("alert")).click();
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();

        Assertions.assertEquals("Alert Simples", texto);
        alert.accept();

        driver.findElement(By.id("elementosForm:nome")).sendKeys(texto);
        Assertions.assertEquals(texto, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));

        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirmAccept() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();

        Assertions.assertEquals("Confirmado", alert.getText());
        alert.accept();

        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirmDismiss() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("confirm")).click();
        Alert alert = driver.switchTo().alert();
        alert.dismiss();

        Assertions.assertEquals("Negado", alert.getText());
        alert.accept();

        driver.quit();
    }

    @Test
    public void deveInteragirComAlertConfirmPrompt() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("prompt")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Digite um numero", alert.getText());
        alert.sendKeys("12");
        alert.accept();

        Assertions.assertEquals("Era 12?", alert.getText());
        alert.accept();

        Assertions.assertEquals(":D", alert.getText());
        alert.accept();

        driver.quit();
    }
}
