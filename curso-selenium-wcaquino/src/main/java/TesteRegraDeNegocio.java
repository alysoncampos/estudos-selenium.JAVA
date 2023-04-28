import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteRegraDeNegocio {

    @Test
    public void deveValidarNomeObrigatorio() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Nome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Sobrenome eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Sexo eh obrigatorio", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarComidaVegetariana() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:sexo")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:3")).click();
        driver.findElement(By.id("elementosForm:cadastrar")).click();
        Alert alert = driver.switchTo().alert();

        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", alert.getText());
        driver.quit();
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();

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
        driver.quit();
    }
}
