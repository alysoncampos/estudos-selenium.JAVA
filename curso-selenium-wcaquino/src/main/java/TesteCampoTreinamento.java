import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class TesteCampoTreinamento {

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
    public void testeInteragirComTextField() {
        driver.findElement(By.id("elementosForm:nome")).sendKeys("Teste de escrita");
        Assertions.assertEquals("Teste de escrita", driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void testeInteragirComTextArea() {
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys("Teste");
        Assertions.assertEquals("Teste", driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void testeInteragirComRadioButton() {
        driver.findElement(By.id("elementosForm:sexo:0")).click();
        Assertions.assertTrue(driver.findElement(By.id("elementosForm:sexo:0")).isSelected());
    }

    @Test
    public void testeInteragirComCheckbox() {
        driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
        Assertions.assertTrue(driver.findElement(By.id("elementosForm:comidaFavorita:2")).isSelected());
    }

    @Test
    public void testeInteragirComCombo() {
        WebElement element = driver.findElement(By.id("elementosForm:escolaridade"));
        Select combo = new Select(element);
        //combo.selectByIndex(2);
        //combo.selectByValue("2graucomp");
        combo.selectByVisibleText("Superior");
        Assertions.assertEquals("Superior", combo.getFirstSelectedOption().getText());
    }

    @Test
    public void testeDeveVerificarValoresCombo() {
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
    }

    @Test
    public void testeDeveVerificarValoresComboMultiplo() {
        WebElement element = driver.findElement(By.id("elementosForm:esportes"));
        Select combo = new Select(element);
        combo.selectByVisibleText("Natacao");
        combo.selectByVisibleText("Corrida");
        combo.selectByVisibleText("O que eh esporte?");

        Assertions.assertEquals(3, combo.getAllSelectedOptions().size());
        combo.deselectByVisibleText("Corrida");
        Assertions.assertEquals(2, combo.getAllSelectedOptions().size());
    }

    @Test
    public void deveInteragirComBotoes() {
        WebElement botao = driver.findElement(By.id("buttonSimple"));
        botao.click();
        Assertions.assertEquals("Obrigado!", botao.getAttribute("value"));
    }

    @Test
    public void deveInteragirComLinks() {
        driver.findElement(By.linkText("Voltar")).click();

        Assertions.assertEquals("Voltou!",
                driver.findElement(By.id("resultado")).getText());
    }

    @Test
    public void deveBuscarTextosNaPagina() {
//        Assertions.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));
//        Assertions.assertEquals("Campo de Treinamento",
//                driver.findElement(By.tagName("h3")).getText());
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                driver.findElement(By.className("facilAchar")).getText());
    }
}
