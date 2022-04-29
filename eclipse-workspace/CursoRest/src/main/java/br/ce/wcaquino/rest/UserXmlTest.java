package br.ce.wcaquino.rest;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.internal.path.xml.NodeImpl;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class UserXmlTest {
	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	
	@BeforeClass
	public static void setup() {
		RestAssured.baseURI = "https://restapi.wcaquino.me";
//		RestAssured.port = 443;
//		RestAssured.basePath = "";
		
		RequestSpecBuilder reqBuilder = new RequestSpecBuilder();
		reqBuilder.log(LogDetail.ALL);
		reqSpec = reqBuilder.build();
		
		ResponseSpecBuilder resBuilder = new ResponseSpecBuilder();
		resBuilder.expectStatusCode(200);
		resSpec = resBuilder.build(); 
		
		//Quando declarase dessa forma, não é necessário declarar nos testes o log e o status code 200, os metodos acabam erdando esse comportamento
		RestAssured.requestSpecification = reqSpec;
		RestAssured.responseSpecification = resSpec;
	}
	
	@Test
	public void devoTrabalharComXML() {
			
		given()
//			.spec(reqSpec)
		.when()
			.get("/usersxml/3")
		.then()
//			.statusCode(200)
//			.spec(resSpec)
		
			.rootPath("user")
			.body("name", is("Ana Julia"))
			.body("@id", is("3"))
			
			.rootPath("user.filhos")
			
			.body("name[0]", is("Zezinho"))
			.body("name[1]", is("Luizinho"))
			
			.detachRootPath("filhos")
			.body("filhos.name", hasItem("Luizinho"))
			
			.appendRootPath("filhos")
			.body("name", hasItems("Luizinho", "Zezinho"))
				
		;
	}
	
	@Test
	public void devoFazerPesquisasAvancadasComXML() {
		 
		given()
		.when()
			.get("/usersxml")
		.then()
			.statusCode(200)
			.body("users.user.size()", is(3))
			.body("users.user.findAll{it.age.toInteger() <= 25}.size()", is(2))
			.body("users.user.@id", hasItems("1", "2", "3"))
			.body("users.user.find{it.age == 25}.name", is("Maria Joaquina"))
			.body("users.user.findAll{it.name.toString().contains('n')}.name", hasItems("Maria Joaquina", "Ana Julia"))
			.body("users.user.salary.find{it != null}", is("1234.5678"))
			.body("users.user.salary.find{it != null}.toDouble()", is(1234.5678d))
			.body("users.user.age.collect{it.toInteger() * 2}", hasItems(60, 50, 40))
			.body("user.user.name.findAll{it.toString().startsWith('Maria')}.collect{it.toString().toUpperCase()}", is("MARIA JOAQUINA"))
		;
	}
	
	@Test
	public void devoFazerPesquisasAvancadasComXMLEJava() {
		 
		ArrayList<NodeImpl> nomes = given()
		.when()
			.get("/usersxml")
		.then()
			.statusCode(200)
			.extract().path("user.user.name.findAll{it.toString().contains('n')}")
		;
		Assert.assertEquals(2, nomes.size());
		Assert.assertEquals("Maria Joaquina".toUpperCase(), nomes.get(0).toString().toUpperCase());
		Assert.assertTrue("ANA JULIA".equalsIgnoreCase(nomes.get(1).toString()));
	}
	
	@Test
	public void devoFazerPesquisasAvancadasComXPath() {
		given()
		.when()
			.get("/usersxml")
		.then()
			.statusCode(200)
			.body(hasXPath("count(/users/user)", is("3")))
			.body(hasXPath("/users/user[@id = '1']"))
			.body(hasXPath("//user[@id = '2']"))
			//para nevagar na arvore do xml para cima usa=se o /..
			.body(hasXPath("//name[text() = 'Luizinho']/../../name", is("Ana Julia")))
			.body(hasXPath("//name[text() = 'Ana Julia']/following-sibling::filhos", allOf(containsString("Zezinho"), containsString("Luizinho"))))
			.body(hasXPath("/users/user/name", is("João da Silva")))
			.body(hasXPath("//name", is("João da Silva")))
			.body(hasXPath("/users/user[2]/name", is("Maria Joaquina")))
			//comando last pega o último user da lista
			.body(hasXPath("/users/user[last()]/name", is("Ana Julia")))
			//o . dentro do contains significa que vai procurar pelo n em qualquer local do texto
			.body(hasXPath("count(/users/user/name[contains(.,'n')])", is("2")))
			.body(hasXPath("//user[age < 24]/name", is("Ana Julia")))
			.body(hasXPath("//user[age > 20 and age < 30]/name", is("Maria Joaquina")))
		;
		
	}
}
