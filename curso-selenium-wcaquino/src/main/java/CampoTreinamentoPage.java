import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CampoTreinamentoPage {
    private DSL dsl;

    public CampoTreinamentoPage(WebDriver driver) {
        dsl = new DSL(driver);
    }

    public void setNome(String valor) {
        dsl.escrever("elementosForm:nome", valor);
    }

    public void setSobrenome(String valor) {
        dsl.escrever("elementosForm:sobrenome",valor);
    }

    public void setSexoMasculino() {
        dsl.clicarRadio("elementosForm:sexo:0");
    }

    public void setSexoFeminino() {
        dsl.clicarRadio("elementosForm:sexo:1");
    }

    public void setComidaCarne() {
        dsl.clicarRadio("elementosForm:comidaFavorita:0");
    }

    public void setComidaVegetariano() {
        dsl.clicarRadio("elementosForm:comidaFavorita:3");
    }

    public void setEscolaridade(String valor) {
        dsl.selecionarCombo("elementosForm:escolaridade", valor);
    }

    public void cadastrar() {
        dsl.clicarBotao("elementosForm:cadastrar");
    }

    public void setEsporte(String... valores) {
        for (String valor : valores) {
            dsl.selecionarCombo("elementosForm:esportes", valor);
        }
    }

    public String obterNomeCadastro() {
        return dsl.obterTexto(By.cssSelector("#descNome > span"));
    }

    public String obterSobrenomeCadastro() {
        return dsl.obterTexto(By.cssSelector("#descSobrenome > span"));
    }

    public String obterSexoCadastro() {
        return dsl.obterTexto(By.cssSelector("#descSexo > span"));
    }

    public String obterComidaCadastro() {
        return dsl.obterTexto(By.cssSelector("#descComida > span"));
    }

    public String obterEscolaridadeCadastro() {
        return dsl.obterTexto(By.cssSelector("#descEscolaridade > span"));
    }

    public String obterEsporteCadastro() {
        return dsl.obterTexto(By.cssSelector("#descEsportes > span"));
    }

}
