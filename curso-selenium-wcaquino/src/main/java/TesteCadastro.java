import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteCadastro {

    private WebDriver driver;
    private DSL dsl;
    private CampoTreinamentoPage page;

    @BeforeEach
    public void inicializa() {
        System.setProperty("webdriver.chrome.driver","driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");
        driver.manage().window().maximize();
        dsl = new DSL(driver);
        page = new CampoTreinamentoPage(driver);
    }

    @AfterEach
    public void finaliza() {
        driver.quit();
    }

    @Test
    public void deveCadastrarComSucesso() {
        page.setNome("Alyson");
        page.setSobrenome("Campos");
        page.setSexoMasculino();
        page.setComidaCarne();
        page.setEscolaridade("Superior");
        page.setEsporte("O que eh esporte?");
        page.cadastrar();

        Assertions.assertEquals("Alyson", page.obterNomeCadastro());
        Assertions.assertEquals("Campos", page.obterSobrenomeCadastro());
        Assertions.assertEquals("Masculino", page.obterSexoCadastro());
        Assertions.assertEquals("Carne", page.obterComidaCadastro());
        Assertions.assertEquals("superior", page.obterEscolaridadeCadastro());
        Assertions.assertEquals("O que eh esporte?", page.obterEsporteCadastro());
    }

    @Test
    public void deveValidarNomeObrigatorio() {
        page.cadastrar();
        Assertions.assertEquals("Nome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSobrenomeObrigatorio() {
        page.setNome("Alyson");
        page.cadastrar();
        Assertions.assertEquals("Sobrenome eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarSexoObrigatorio() {
        page.setNome("Alyson");
        page.setSobrenome("Campos");
        page.cadastrar();
        Assertions.assertEquals("Sexo eh obrigatorio", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarComidaVegetariana() {
        page.setNome("Alyson");
        page.setSobrenome("Campos");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setComidaVegetariano();
        page.cadastrar();
        Assertions.assertEquals("Tem certeza que voce eh vegetariano?", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveValidarEsportistaIndeciso() {
        page.setNome("Alyson");
        page.setSobrenome("Campos");
        page.setSexoFeminino();
        page.setComidaCarne();
        page.setEsporte("Natacao", "O que eh esporte?");
        page.cadastrar();
        Assertions.assertEquals("Voce faz esporte ou nao?", dsl.alertaObterTextoEAceita());
    }
}
