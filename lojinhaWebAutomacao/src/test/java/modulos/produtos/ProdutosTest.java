package modulos.produtos;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

@DisplayName("Testes web do módulo de produtos")
public class ProdutosTest {
    @Test
    @DisplayName("Não é permitido registrar um produto com valor igual a zero")
    public void testNaoEPermitidoRegistrarProdutoComValorIgualAZero() {
        //Abrir navegador
        System.setProperty("webdriver.chrome.driver", "/Users/rosangela.costa/drivers/chromedriver");
        WebDriver navegador = new ChromeDriver();

        //Vou maximizar a tela
        navegador.manage().window().maximize();

        //Navegar para a página da lojinha Web
        navegador.get("http://165.227.93.41/lojinha-web/v2/");

        //Fazer login
        navegador.findElement(By.cssSelector("label[for='usuario']")).click();
        navegador.findElement(By.id("usuario")).sendKeys("admin");
        navegador.findElement(By.cssSelector("label[for='senha']")).click();
        navegador.findElement(By.id("senha")).sendKeys("admin");

        navegador.findElement(By.name("action")).click();

        //Vou para tela de registro de produto
        //O texto do link precisa ser informado conforme o visualizado pelo usuário na tela
        navegador.findElement(By.linkText("ADICIONAR PRODUTO")).click();

        //Vou preencher dados do produto e o valor será igual a zero
        navegador.findElement(By.id("produtonome")).sendKeys("Macbook Pro");
        navegador.findElement(By.name("produtovalor")).sendKeys("000");
        navegador.findElement(By.id("produtocores")).sendKeys("preto, branco");

        //Vou submeter o formulário
        navegador.findElement(By.cssSelector("button[type=submit]")).click();

        //Vou validar que a mensagem de erro foi apresentada
    }
}
