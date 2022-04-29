package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

import io.restassured.http.ContentType;
import javafx.beans.binding.When;

public class VerbosTest {

	@Test
	public void deveSalvarUsuario() {
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\"name\":\"Jose\",\"age\":50}")
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Jose"))
			.body("age", is(50))
		;
	}
	
	@Test
	public void naoDevoSalvarUsuarioSemNome () {
		given()
			.log().all()
			.contentType(ContentType.JSON)
			.body("{\"age\":50}")
		.when()
			.post("https://restapi.wcaquino.me/users")
		.then()
			.log().all()
			.statusCode(400)
			.body("id", is(nullValue()))
			.body("error", is("Name é um atributo obrigatório"))
		;
	}
	
	@Test
	public void deveSalvarUsuarioViaXML() {
		given()
			.log().all()
			.contentType(ContentType.XML)
			.body("<user><name>João</name><age>50</age></user>")
		.when()
			.post("https://restapi.wcaquino.me/usersXML")
		.then()
			.log().all()
			.statusCode(201)
			.body("id", is(notNullValue()))
			.body("name", is("Jose"))
			.body("age", is(50))
		;
	}
	
}

