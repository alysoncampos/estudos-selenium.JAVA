import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCadastro {

    @Test
    public void deveCadastrarComSucesso() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("elementosForm:nome")).sendKeys("Alyson");
        driver.findElement(By.id("elementosForm:sobrenome")).sendKeys("Campos");
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        driver.findElement(By.id("elementosForm:comidaFavorita:0")).click();
        new Select(driver.findElement(By.id("elementosForm:escolaridade")))
                .selectByVisibleText("Superior");
        new Select(driver.findElement(By.id("elementosForm:esportes")))
                .selectByVisibleText("O que eh esporte?");
        driver.findElement(By.id("elementosForm:cadastrar")).click();

        Assertions.assertEquals("Alyson", driver.findElement(By.cssSelector("#descNome > span")).getText());
        Assertions.assertEquals("Campos", driver.findElement(By.cssSelector("#descSobrenome > span")).getText());
        Assertions.assertEquals("Masculino", driver.findElement(By.cssSelector("#descSexo > span")).getText());
        Assertions.assertEquals("Carne", driver.findElement(By.cssSelector("#descComida > span")).getText());
        Assertions.assertEquals("superior", driver.findElement(By.cssSelector("#descEscolaridade > span")).getText());
        Assertions.assertEquals("O que eh esporte?", driver.findElement(By.cssSelector("#descEsportes > span")).getText());

        driver.quit();
    }
}
