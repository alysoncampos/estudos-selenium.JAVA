import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TesteFramesEJanelas {

    @Test
    public void deveInteragirComFrames() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.switchTo().frame("frame1");
        driver.findElement(By.id("frameButton")).click();

        Alert alert = driver.switchTo().alert();
        String msg = alert.getText();
        Assertions.assertEquals("Frame OK!", msg);
        alert.accept();

        driver.switchTo().defaultContent();
        driver.findElement(By.id("elementosForm:nome")).sendKeys(msg);
        Assertions.assertEquals(msg, driver.findElement(By.id("elementosForm:nome")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComJanelas() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("buttonPopUpEasy")).click();
        driver.switchTo().window("Popup");
        driver.findElement(By.tagName("textarea")).sendKeys("Deu certo?");
        driver.close();

        driver.switchTo().window("");
        String msg = "e agora?";
        driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(msg);

        Assertions.assertEquals(msg, driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));
    }

    @Test
    public void deveInteragirComJanelasSemTitulo() {
        System.setProperty("webdriver.chrome.driver", "driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("file:///" + System.getProperty("user.dir") + "/src/main/resources/componentes.html");

        driver.findElement(By.id("buttonPopUpHard")).click();
        System.out.println(driver.getWindowHandle());
        System.out.println(driver.getWindowHandles());

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[1]);
        String msg1 = "Deu certo?";
        driver.findElement(By.tagName("textarea")).sendKeys(msg1);
        Assertions.assertEquals(msg1, driver.findElement(By.tagName("textarea")).getAttribute("value"));

        driver.switchTo().window((String) driver.getWindowHandles().toArray()[0]);
        String msg2 = "e agora?";
        driver.findElement(By.tagName("textarea")).sendKeys(msg2);
        Assertions.assertEquals(msg2, driver.findElement(By.id("elementosForm:sugestoes")).getAttribute("value"));

        driver.quit();
    }
}
