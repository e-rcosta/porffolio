package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import java.util.*;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class OlaMundoTest {
	@Test
	public void testOlaMundo() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me:80/ola");
		Assert.assertTrue (response.getBody().asString().equals("Ola Mundo!"));
		Assert.assertTrue("O status code deveria ser 200", response.statusCode() == 200);
		Assert.assertEquals(200, response.statusCode());
		
		
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
	}
	
	@Test
	public void devoConhecerOutrasFormasRestAssured() {
		Response response = request(Method.GET, "http://restapi.wcaquino.me:80/ola");
		ValidatableResponse validacao = response.then();
		validacao.statusCode(200);
		
		//Foi importado o metódo estático do RestAssured
		get("http://restapi.wcaquino.me/ola").then().statusCode(200);
		
		
		//Forma mais organizada de trabalhar com testes
		//Dado, quando e então
		given() //pré condição
		.when() //ação
			.get("http://restapi.wcaquino.me/ola")
		.then() //acertivas
			.statusCode(200);
	}
	
	@Test
	public void devoConhecerMatchersHamcert() {
		Assert.assertThat("Maria", Matchers.is("Maria"));
		Assert.assertThat(128, Matchers.is(128));
		Assert.assertThat(128, Matchers.isA(Integer.class));
		Assert.assertThat(128d, Matchers.isA(Double.class));
		//Valor examinado é menor que o valor especificado
		Assert.assertThat(128d, Matchers.greaterThan(120d));
		//Valor examinado é maior que o valor especificado
		Assert.assertThat(128d, Matchers.lessThan(150d));
		
		List<Integer> impares = Arrays.asList(1,3,5,7,9);
		//Verifica se o tamanho da lista é de 5 elementos
		assertThat(impares, hasSize(5));
		//Verifica se a lista contem todos os elementos e na mesma ordem
		assertThat(impares, contains(1,3,5,7,9));
		//Verifica se a lista contem todos os elementos independente da ordem
		assertThat(impares, containsInAnyOrder(1,3,9,7,5));
		//Verifica se a lista contem o elemento
		assertThat(impares, hasItem(1));
		assertThat("Maria", is(not("João")));
		//O is usado acima é opcional
		assertThat("Maria", not("João"));
		//Para fazer um OR, onde verifica se o valor examinado é um dos valores especificados
		assertThat("Maria", anyOf(is("Maria"), is("Joaquina")));
		//(and) Todas as condições devem ser satisfeitas. Inicia com Joa, termina com ina e se em qualquer lugar do texto contem qui
		assertThat("Joaquina", allOf(startsWith("Joa"), endsWith("ina"), containsString("qui")));
	}
	
	@Test
	public void devoVallidarBody() {
		given() //pré condição
		.when() //ação
			.get("http://restapi.wcaquino.me/ola")
		.then() //acertivas
			.statusCode(200)
			.body(is("Ola Mundo!"))
			//Verifica se no body contem a strng Mundo
			.body(containsString("Mundo"))
			//Verifique que o corpo não está vazio
			.body(is(not(nullValue())));
	}
}
