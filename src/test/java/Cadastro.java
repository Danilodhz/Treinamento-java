import io.github.bonigarcia.wdm.WebDriverManager;
import jdk.jfr.Description;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Cadastro {
    WebDriver navegador;

    @Before
    public void config() {
        WebDriverManager.chromedriver().setup();
        navegador = new ChromeDriver();
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        navegador.manage().window().maximize();
        navegador.get("http://automationpratice.com.br/");
    }

    @Test
    @Description("Cadastro de usuario")
    public void registerUser(){

        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());

        navegador.findElement(By.cssSelector("a[href='/register']")).click();
        navegador.findElement(By.cssSelector("#user")).sendKeys("Danilo");
        navegador.findElement(By.cssSelector("#email")).sendKeys("Ivarotaosso@gmail.com");
        navegador.findElement(By.cssSelector("#password")).sendKeys("Ivarotaosso1");
        navegador.findElement(By.cssSelector("#btnRegister")).click();

        String registerSuccess = navegador.findElement(By.cssSelector("h2[id*='swal']")).getText();
        assertEquals("Cadastro realizado!", registerSuccess);
    }

    @Test
    @Description("Login com sucesso")
    public void loginSuccess(){

        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());

        navegador.findElement(By.cssSelector("i[class='fa fa-user']")).click();
        navegador.findElement(By.cssSelector("#user")).sendKeys("ivarotaosso@gmail.com");
        navegador.findElement(By.cssSelector("#password")).sendKeys("Ivarotaosso1");
        navegador.findElement(By.cssSelector("#btnLogin")).click();

        String loginDone = navegador.findElement(By.cssSelector("h2[id*='swal']")).getText();
        assertEquals("Login realizado", loginDone);
    }

    @Test
    @Description("Login e-mail invalido")
    public void invalidEmailLogin(){

    }

    @Test
    @Description("Login password invalid")
    public void invalidPasswordLogin(){

    }
    @After
    public void close() {
        navegador.quit();
    }
}
