import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class TesteCampoTreinamento {

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
    public void testeInteragirComTextField() {
        dsl.escrever("elementosForm:nome", "Teste de escrita");
        Assertions.assertEquals("Teste de escrita", dsl.obterValorCampo("elementosForm:nome"));
    }

    @Test
    public void testeInteragirComTextArea() {
        dsl.escrever("elementosForm:sugestoes", "Teste");
        Assertions.assertEquals("Teste", dsl.obterValorCampo("elementosForm:sugestoes"));
    }

    @Test
    public void testeInteragirComRadioButton() {
        dsl.clicarRadio("elementosForm:sexo:0");
        Assertions.assertTrue(dsl.isRadioMarcado("elementosForm:sexo:0"));
    }

    @Test
    public void testeInteragirComCheckbox() {
        dsl.clicarCheck("elementosForm:comidaFavorita:2");
        Assertions.assertTrue(dsl.isCheckMarcado("elementosForm:comidaFavorita:2"));
    }

    @Test
    public void testeInteragirComCombo() {
        dsl.selecionarCombo("elementosForm:escolaridade", "Superior");
        Assertions.assertEquals("Superior", dsl.obterValorSelecionadoCombo("elementosForm:escolaridade"));
    }

    @Test
    public void testeDeveVerificarValoresCombo() {
        Assertions.assertEquals(8, dsl.obterQuantidadeOpcoesCombo("elementosForm:escolaridade"));
        Assertions.assertTrue(dsl.verificarOpcaoCombo("elementosForm:escolaridade", "Mestrado"));
    }

    @Test
    public void testeDeveVerificarValoresComboMultiplo() {
        dsl.selecionarCombo("elementosForm:esportes", "Natacao");
        dsl.selecionarCombo("elementosForm:esportes", "Corrida");
        dsl.selecionarCombo("elementosForm:esportes", "O que eh esporte?");

        Assertions.assertEquals(3,
                dsl.obterValoreSelecionadosCombo("elementosForm:esportes").size());
        dsl.deselecionarCombo("elementosForm:esportes", "Corrida");
        Assertions.assertEquals(2,
                dsl.obterValoreSelecionadosCombo("elementosForm:esportes").size());
        Assertions.assertEquals(List.of("Natacao", "O que eh esporte?"),
                dsl.obterValoreSelecionadosCombo("elementosForm:esportes"));
    }

    @Test
    public void deveInteragirComBotoes() {
        dsl.clicarBotao("buttonSimple");
        Assertions.assertEquals("Obrigado!", dsl.obterValorElemento("buttonSimple"));
    }

    @Test
    public void deveInteragirComLinks() {
        dsl.clicarLink("Voltar");
        Assertions.assertEquals("Voltou!", dsl.obterTexto("resultado"));
    }

    @Test
    public void deveBuscarTextosNaPagina() {
//        Assertions.assertTrue(driver.findElement(By.tagName("body"))
//                .getText().contains("Campo de Treinamento"));
        Assertions.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
        Assertions.assertEquals("Cuidado onde clica, muitas armadilhas...",
                dsl.obterTexto(By.className("facilAchar")));
    }

    @Test
    public void deveInteragirComNome() {
        dsl.escrever("elementosForm:nome", "Ályson");
        Assertions.assertEquals("Ályson", dsl.obterValorCampo("elementosForm:nome"));
        dsl.escrever("elementosForm:nome", "Campos");
        Assertions.assertEquals("Campos", dsl.obterValorCampo("elementosForm:nome"));
    }
}
