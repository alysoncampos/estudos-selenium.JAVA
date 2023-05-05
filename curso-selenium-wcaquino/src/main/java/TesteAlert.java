import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteAlert {

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
    public void deveInteragirComAlertSimples() {
        dsl.clicarBotao("alert");
        String texto = dsl.alertaObterTextoEAceita();
        Assertions.assertEquals("Alert Simples", texto);
        dsl.escrever("elementosForm:nome", texto);
        Assertions.assertEquals(texto, dsl.obterValorElemento("elementosForm:nome"));
    }

    @Test
    public void deveInteragirComAlertConfirmAccept() {
        dsl.clicarBotao("confirm");
        Assertions.assertEquals("Confirm Simples", dsl.alertaObterTextoEAceita());
        Assertions.assertEquals("Confirmado", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveInteragirComAlertConfirmDismiss() {
        dsl.clicarBotao("confirm");
        Assertions.assertEquals("Confirm Simples", dsl.alertaObterTextoENega());
        Assertions.assertEquals("Negado", dsl.alertaObterTextoEAceita());
    }

    @Test
    public void deveInteragirComAlertConfirmPrompt() {
        dsl.clicarBotao("prompt");
        Assertions.assertEquals("Digite um numero", dsl.alertaObterTexto());
        dsl.alertaEscreveEAceita("12");
        Assertions.assertEquals("Era 12?", dsl.alertaObterTextoEAceita());
        Assertions.assertEquals(":D", dsl.alertaObterTextoEAceita());
    }
}
