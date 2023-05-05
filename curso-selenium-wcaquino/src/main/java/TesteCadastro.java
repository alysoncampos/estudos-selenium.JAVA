import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {

    private WebDriver driver;
    private DSL dsl;

    @BeforeEach
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
    }

    @AfterEach
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void deveCadastrarComSucesso() {
        dsl.escrever("elementosForm:nome", "Alyson");
        dsl.escrever("elementosForm:sobrenome","Campos");
        dsl.clicarRadio("elementosForm:sexo:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");

        Assertions.assertEquals("Alyson", dsl.obterTexto(By.cssSelector("#descNome > span")));
        Assertions.assertEquals("Campos", dsl.obterTexto(By.cssSelector("#descSobrenome > span")));
        Assertions.assertEquals("Masculino", dsl.obterTexto(By.cssSelector("#descSexo > span")));
        Assertions.assertEquals("Carne", dsl.obterTexto(By.cssSelector("#descComida > span")));
        Assertions.assertEquals("superior", dsl.obterTexto(By.cssSelector("#descEscolaridade > span")));
        Assertions.assertEquals("O que eh esporte?", dsl.obterTexto(By.cssSelector("#descEsportes > span")));
    }
}
