package tests;

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
    WebDriver navegador; // PALAVRA QUE COMUNIQUE O CÓDIGO COM O NAVEGADOR

    @Before // AÇÃO QUE É EXECUTADA ANTES DOS TESTES
    public void config() { // METODO DE CONFIGURAÇÃO INICIAIS
        WebDriverManager.chromedriver().setup(); // LINHA QUE CONFIGURA O NAVEGADOR DO TESTE
        navegador = new ChromeDriver(); // IMPORTANDO O NAVEGADOR CHROME
        navegador.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // ESPERA DEFINIDA PRA CADA COMANDO
        navegador.manage().window().maximize(); // MAXIMIZAR A JANELA DO WINDOWS
        navegador.get("http://automationpratice.com.br/"); //  VISITAR URL FORNECIDA
    }

    @Test
    @Description("tests.Cadastro de usuario")
    public void registerUser(){

        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());

        navegador.findElement(By.cssSelector("a[href='/register']")).click();
        navegador.findElement(By.cssSelector("#user")).sendKeys("Danilo");
        navegador.findElement(By.cssSelector("#email")).sendKeys("Ivarotaosso@gmail.com");
        navegador.findElement(By.cssSelector("#password")).sendKeys("Ivarotaosso1");
        navegador.findElement(By.cssSelector("#btnRegister")).click();

        String registerSuccess = navegador.findElement(By.cssSelector("h2[id*='swal']")).getText();
        assertEquals("tests.Cadastro realizado!", registerSuccess);
    }

    @Test
    // executar o teste e ele vem no Junit
    @Description("Login com sucesso")
    // Descrição do que eu vou testar
    public void loginSuccess(){
    // "public" Qualquer metodo ou classe podem acessar este metodo
        // "Void" se o metodo não retorna uma informação
        // loginSuccess é o nome do metodo (O nome do metodo deve estar relacionado ao que você deve fazer)
        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());
        // Comando Junit para fazer afirmações: (utilizar o comando title no ctrl+F)
        // primeiro eu passo o texto que eu quero afirmar e depois o elemento que deve conter este texto

        navegador.findElement(By.cssSelector("i[class='fa fa-user']")).click();
        // FindElement é o comando de busca
        // By.cssSelector é como eu vou buscar(neste caso por um seletor css
        // um seletor css é quando eu pego a TAG e o conteudo de um atributo dentro de [""]
        navegador.findElement(By.cssSelector("#user")).sendKeys("ivarotaosso@gmail.com");
        // Se for ID colocar um # na frente
        // Se for uma classe colocar um . na frente
        navegador.findElement(By.cssSelector("#password")).sendKeys("Ivarotaosso1");
        // sendkeys é para preencher o texto em um campo
        navegador.findElement(By.cssSelector("#btnLogin")).click();
        // click é para clicar em algum botão
        String loginDone = navegador.findElement(By.cssSelector("h2[id*='swal']")).getText();
        // variável é como uma caixinha onde eu guardo informações, para escrever uma variavel eu preciso
        // informar o tipo dela neste caso é uma "String", depois tenho que dar um nome pra ela
        // esse nome deve ser relacionado a informação que ela vai guardar.
        // getText é usado para pegar o texto de um elemento! EX. LOGIN EFETUADO COM SUCESSO!
        assertEquals("Login realizado", loginDone);
        // Comando Junit para fazer afirmações:
        // primeiro eu passo o texto que eu quero afirmar e depois o elemento que deve conter este texto
    }

    @Test
    @Description("Login e-mail invalido")
    public void invalidEmailLogin(){

        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());
        navegador.findElement(By.cssSelector("i[class='fa fa-user']")).click();
        navegador.findElement(By.cssSelector("#user")).sendKeys("ivarosemosso.com");
        navegador.findElement(By.cssSelector("#password")).sendKeys("Ivarotaosso1");
        navegador.findElement(By.cssSelector("#btnLogin")).click();

        String invalidEmail = navegador.findElement(By.cssSelector("span[class*='invalid_input']")).getText();
        assertEquals("E-mail inválido.", invalidEmail);
    }

    @Test
    @Description("Login password invalid")
    public void invalidPasswordLogin(){

        assertEquals("QAZANDO Shop E-Commerce", navegador.getTitle());
        navegador.findElement(By.cssSelector("i[class='fa fa-user']")).click();
        navegador.findElement(By.cssSelector("#user")).sendKeys("ivarotaosso@gmail.com");
        navegador.findElement(By.cssSelector("#password")).sendKeys("12");
        navegador.findElement(By.cssSelector("#btnLogin")).click();

        String invalidPassword = navegador.findElement(By.cssSelector("span[class='invalid_input']")).getText();
        assertEquals("Senha inválida.", invalidPassword);
    }
    @After
    public void close() {
        navegador.quit();
    }
}
