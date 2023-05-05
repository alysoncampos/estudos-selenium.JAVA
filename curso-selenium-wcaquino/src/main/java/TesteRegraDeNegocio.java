import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteRegraDeNegocio {

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
    public void deveValidarNomeObrigatorio() {
        dsl.clicarBotao("elementosForm:cadastrar");
        Assertions.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        dsl.escrever("elementosForm:nome","Alyson");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assertions.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        dsl.escrever("elementosForm:nome", "Alyson");
        dsl.escrever("elementosForm:sobrenome","Campos");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assertions.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        dsl.escrever("elementosForm:nome", "Alyson");
        dsl.escrever("elementosForm:sobrenome", "Campos");
        dsl.clicarRadio("elementosForm:sexo");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        dsl.escrever("elementosForm:nome", "Alyson");
        dsl.escrever("elementosForm:sobrenome", "Campos");
        dsl.clicarRadio("elementosForm:sexo");
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");
        dsl.clicarBotao("elementosForm:cadastrar");
        Assertions.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
