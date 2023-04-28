import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

    @Test
    public void testeInteragirComTextField() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");

        Assertions.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeInteragirComTextArea() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");

        Assertions.assertEquals("Teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
        driver.quit();
    }

    @Test
    public void testeInteragirComRadioButton() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:sexo:0")).click();

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
        driver.quit();
    }

    @Test
    public void testeInteragirComCheckbox() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();

        Assertions.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
        driver.quit();
    }

    @Test
    public void testeInteragirComCombo() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        //combo.selectByIndex(2);
        //combo.selectByValue("2graucomp");
        combo.selectByVisibleText("Superior");

        Assertions.assertEquals("Superior", combo.getFirstSelectedOption().getText());
        driver.quit();
    }

    @Test
    public void testeDeveVerificarValoresCombo() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        Assertions.assertEquals(8, options.size());

        boolean encontrou = false;
        for(WebElement option : options) {
            if(option.getText().equals("Mestrado")) {
                encontrou = true;
                break;
            }
        }
        Assertions.assertTrue(encontrou);
        driver.quit();
    }

    @Test
    public void testeDeveVerificarValoresComboMultiplo() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        Assertions.assertEquals(3, combo.getAllSelectedOptions().size());

        combo.deselectByVisibleText("Corrida");

        Assertions.assertEquals(2, combo.getAllSelectedOptions().size());
        driver.quit();
    }

    @Test
    public void deveInteragirComBotoes() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();

        Assertions.assertEquals("Obrigado!", botao.getAttribute("value"));
        driver.quit();
    }

    @Test
    public void deveInteragirComLinks() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.findElement(By.linkText("Voltar")).click();

        Assertions.assertEquals("Voltou!",
                driver.findElement(By.id("resultado")).getText());
        driver.quit();
    }

    @Test
    public void deveBuscarTextosNaPagina() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
//        Assertions.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));
//        Assertions.assertEquals("Campo de Treinamento",
//                driver.findElement(By.tagName("h3")).getText());
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());
        driver.quit();
    }
}
