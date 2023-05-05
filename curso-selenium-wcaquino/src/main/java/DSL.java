import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class DSL {

    private WebDriver driver;

    public DSL(WebDriver driver) {
        this.driver = driver;
    }

    /**** TextField e TextArea ****/

    public void escrever(By by, String texto) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(texto);
    }

    public void escrever(String id, String texto) {
        escrever(By.id(id), texto);
    }

    public String obterValorCampo(By by) {
        return driver.findElement(by).getAttribute("value");
    }

    public String obterValorCampo(String id) {
        return obterValorCampo(By.id(id));
    }

    /**** Radio e Check ****/

    public void clicarRadio(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean isRadioMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    public void clicarCheck(String idCampo) {
        driver.findElement(By.id(idCampo)).click();
    }

    public boolean isCheckMarcado(String idCampo) {
        return driver.findElement(By.id(idCampo)).isSelected();
    }

    /**** Combo ****/

    public void selecionarCombo(String id, String valor) {
        obterCombo(id).selectByVisibleText(valor);
    }

    public void deselecionarCombo(String id, String valor) {
        obterCombo(id).deselectByVisibleText(valor);
    }

    public String obterValorSelecionadoCombo(String id) {
        return obterCombo(id).getFirstSelectedOption().getText();
    }

    public List<String> obterValoreSelecionadosCombo(String id) {
        Select combo = obterCombo(id);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<>();
        for (WebElement opcao : allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public Integer obterQuantidadeOpcoesCombo(String id) {
        return obterCombo(id).getOptions().size();
    }

    public boolean verificarOpcaoCombo(String id, String valor) {
        boolean encontrou = false;
        for(WebElement option : obterCombo(id).getOptions()) {
            if(option.getText().equals(valor)) {
                encontrou = true;
                break;
            }
        }
        return encontrou;
    }

    private Select obterCombo(String id) {
        WebElement element = driver.findElement(By.id(id));
        Select combo = new Select(element);
        return combo;
    }

    /**** Botao ****/

    public void clicarBotao(String id) {
        driver.findElement(By.id(id)).click();
    }

    public String obterValorElemento(String id) {
        return driver.findElement(By.id(id)).getAttribute("value");
    }

    /**** Link ****/

    public void clicarLink(String link) {
        driver.findElement(By.linkText(link)).click();
    }

    /**** Textos ****/

    public String obterTexto(By by) {
        return driver.findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    /**** Alerts ****/

    public String alertaObterTexto() {
        Alert alert = driver.switchTo().alert();
        return alert.getText();
    }

    public String alertaObterTextoEAceita() {
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        alert.accept();
        return texto;
    }

    public String alertaObterTextoENega() {
        Alert alert = driver.switchTo().alert();
        String texto = alert.getText();
        alert.dismiss();
        return texto;
    }

    public void alertaEscreveEAceita(String texto) {
        Alert alert = driver.switchTo().alert();
        alert.sendKeys(texto);
        alert.accept();
    }

    /**** Frames e Janelas ****/

    public void entrarFrame(String id) {
        driver.switchTo().frame(id);
    }

    public void sairFrame() {
        driver.switchTo().defaultContent();
    }

    public void trocarJanela(String id) {
        driver.switchTo().window(id);
    }
}
