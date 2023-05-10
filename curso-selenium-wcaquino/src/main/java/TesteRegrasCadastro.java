import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class TesteRegrasCadastro {

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

    public static Collection<Object[]> getCollection() {
        return Arrays.asList(new Object[][] {
                {"", "", "", Arrays.asList(), new String[]{}, "Nome eh obrigatorio"},
                {"Alyson", "", "", Arrays.asList(), new String[]{}, "Sobrenome eh obrigatorio"},
                {"Alyson", "Campos", "", Arrays.asList(), new String[]{}, "Sexo eh obrigatorio"},
                {"Alyson", "Campos", "Masculino", Arrays.asList("Carne", "Vegetariano"),
                        new String[]{}, "Tem certeza que voce eh vegetariano?"},
                {"Alyson", "Campos", "Masculino", Arrays.asList("Carne"),
                        new String[]{"Natacao", "O que eh esporte?"}, "Voce faz esporte ou nao?"}
        });
    }

    @ParameterizedTest
    @MethodSource("getCollection")
    public void deveValidarRegras(String nome, String sobrenome, String sexo, List<String> comidas,
                                  String[] esportes, String msg) {
        page.setNome(nome);
        page.setSobrenome(sobrenome);
        if (sexo.equals("Masculino")) {
            page.setSexoMasculino();
        }
        if (sexo.equals("Feminino")){
            page.setSexoFeminino();
        }
        if (comidas.contains("Carne")) page.setComidaCarne();
        if (comidas.contains("Pizza")) page.setComidaPizza();
        if (comidas.contains("Vegetariano")) page.setComidaVegetariano();
        page.setEsporte(esportes);
        page.cadastrar();
        Assertions.assertEquals(msg, dsl.alertaObterTextoEAceita());
    }
}
