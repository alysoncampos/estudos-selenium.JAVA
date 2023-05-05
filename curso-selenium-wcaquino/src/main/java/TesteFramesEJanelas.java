import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

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
    public void deveInteragirComFrames() {
        dsl.entrarFrame("frame1");
        dsl.clicarBotao("frameButton");
        String texto = dsl.alertaObterTextoEAceita();
        Assertions.assertEquals("Frame OK!", texto);
        dsl.sairFrame();
        dsl.escrever("elementosForm:nome", texto);
        Assertions.assertEquals(texto, dsl.obterValorElemento(("elementosForm:nome")));
    }

    @Test
    public void deveInteragirComJanelas() {
        dsl.clicarBotao("buttonPopUpEasy");
        dsl.trocarJanela("Popup");
        dsl.escrever(By.tagName("textarea"), "Deu certo?");
        driver.close();
        dsl.trocarJanela("");
        String msg = "e agora?";
        dsl.escrever("elementosForm:sugestoes", msg);
        Assertions.assertEquals(msg,  dsl.obterValorElemento("elementosForm:sugestoes"));
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        dsl.clicarBotao("buttonPopUpHard");
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());
        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[1]);
        String msg1 = "Deu certo?";
        dsl.escrever(By.tagName("textarea"), msg1);
        Assertions.assertEquals(msg1, dsl.obterValorCampo(By.tagName("textarea")));

        dsl.trocarJanela((String) driver.getWindowHandles().toArray()[0]);
        String msg2 = "e agora?";
        dsl.escrever(By.tagName("textarea"), msg2);
        Assertions.assertEquals(msg2, dsl.obterValorCampo(By.id("elementosForm:sugestoes")));
    }
}
