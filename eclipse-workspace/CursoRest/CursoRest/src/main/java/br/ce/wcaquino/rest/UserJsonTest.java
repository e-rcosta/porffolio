package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UserJsonTest {
	@Test
	public void deveVerificarPrimeiroNivel() {
		given().when().get("http://restapi.wcaquino.me/users/1").then().statusCode(200).body("id", is(1))
				// nome contem a palavra Silva
				.body("name", containsString("Silva"))
				// idade maior que 18 anos
				.body("age", greaterThan(18));
	}

	public void deveVerificarPrimeiroNivelOutraFormas() {
		Response response = RestAssured.request(Method.GET, "http://restapi.wcaquino.me/users/1");

		// path
		Assert.assertEquals(1, response.path("id"));
		// informa no path que está passando uma string %s
		Assert.assertEquals(1, response.path("%s", "id"));

		// jsonpath
		JsonPath jpath = new JsonPath(response.asString());
		Assert.assertEquals(1, jpath.getInt("id"));

		// from
		int id = JsonPath.from(response.asString()).getInt("id");
		Assert.assertEquals(1, id);
	}

	@Test
	public void deveVerificarSegundoNivel() {
		given().when().get("http://restapi.wcaquino.me/users/2").then().statusCode(200)
				// nome contem a palavra Silva
				.body("name", containsString("Joaquina"))
				// verifica segundo nível nome da rua
				.body("endereco.rua", is("Rua dos bobos"));
		;
	}

	@Test
	public void deveVerificarLista () {
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/3")
		.then()
			.statusCode(200)
			//nome contem a palavra Silva
			.body("name", containsString("Ana"))
			 //verifica tamanho da lista
			.body("filhos.name", hasSize(2))
			.body("filhos[0].name", is("Zezinho"))
			.body("filhos[1].name", is("Luizinho"))
			//verifica se na lista possui o item informado
			.body("filhos.name", hasItem("Zezinho"))
			//verifica se na lista existe os itens informados
			.body("filhos.name", hasItems("Zezinho", "Luizinho"))
			;
	}
	
	@Test
	public void deveRetornarErroUsuarioInexistente() {
		given()
		.when()
			.get("http://restapi.wcaquino.me/users/4")
		.then()
			.statusCode(404)
			.body("error", is("Usuário inexistente"))
		;
	}
	
	@Test
	public void deveVerificarListaRaiz() {
		given()
		.when()
			.get("http://restapi.wcaquino.me/users")
		.then()
			.statusCode(200)
			//verifica quantos registros existem na lista. O $ indica que será procurao na raiz.
			.body("$", hasSize(3))
			.body("name", hasItems("João da Silva", "Maria Joaquina", "Ana Júlia"))
			.body("age[1]", is(25))
			//Verifica na lista(arrays) se contem Zezinho
			.body("filhos.name", hasItems(Arrays.asList("Zezinho", "Luizinho")))
			//o f junto do valor indica que é um float
			.body("salary", contains(1234.5678f, 2500, null));
		;
	}
	
	@Test
	public void devoFazerVerificacoesAvancadas() {
		given()
		.when()
			.get("http://restapi.wcaquino.me/users")
		.then()
			.statusCode(200)
			//verifica quantos registros existem na lista. O $ indica que será procurao na raiz.
			.body("$", hasSize(3))
			//verifica se existe na lista duas pessoas cuja a idade é menor ou igual a 25
			.body("age.findAll.{it <= 25}.size()" , is(2))
			.body("age.findAll.{it <= 25 && it > 20}.size()" , is(1))
			.body("findAll.{it.age <= 25 && it.age > 20}.name" , hasItem("Maria Joaquina"))
		;
	}
}
