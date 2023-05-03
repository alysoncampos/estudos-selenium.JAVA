import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegraDeNegocio {

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
    public void deveValidarNomeObrigatorio() {
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        Assertions.assertEquals("Nome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Sobrenome eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Sexo eh obrigatorio", alert.getText());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:sexo")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:sexo")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        Select combo = new Select(driver.findElement(By.id("elementosForm:esportes")));
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();

        Assertions.assertEquals("Voce faz esporte ou nao?", msg);
    }
}
