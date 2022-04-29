package br.com.riachuelo.soap;

import static io.restassured.RestAssured.*;

import org.junit.Test;


public class listarCartoesTest {

	@Test
	public void listarCartoes () {
		given()
			.log().all()
			.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
			.contentType("application/json")
			.body("{\"cliente\":{\"cpf\":\"02454502461\"},\"canal\":\"URA\"}")
		.when()
			.post("http://osbhml.riachuelo.net/Financeiro/Cartao/API/v4/listarCartoes")
		.then()
			.log().all()
			.statusCode(200)
		;	
	}
	
		
	@Test
	public void obterContaCliente () {
		given()
		.log().all()
		.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
		.contentType("application/json")
		.body("{\"cliente\":{\"cpf\":\"02454502461\"},\"canal\":\"URA\"}")
		.when()
		.post("http://osbhml.riachuelo.net/Financeiro/Cliente/API/v3/obterContaCliente")
	.then()
		.log().all()
		.statusCode(200)
	;
	}
	
	@Test
	public void validarCobranca () {
		given()
			.log().all()
			.auth().basic("sturasoa", "$v2NqDnSYX")
			.param("cpf",  "35858349811")
			.param("canal", "URA")
		.when()
			.get("http://osbhml.riachuelo.net/Financeiro/Cliente/API/v3/validarCobranca")
		.then()
			.log().all()
			.statusCode(200)
		;
	}

	
	@Test
	public void listarCartoesMigracao () {
		given()
			.log().all()
			.auth().basic("dev-svc-fin", "R14chu3l0")
			.contentType("application/json")
			.body("{\"cliente\":{\"cpf\":\"12642312887\"},\"canal\":\"URA\"}")
		.when()
			.post("http://osbhml.riachuelo.net/Financeiro/Cartao/API/v4/listarCartoesMigracao")
		.then()
			.log().all()
			.statusCode(200)
	;
	}
	
	@Test
	public void desbloquearLogico () {
		
		String myEnvelope = 
				  "<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\">\r\n" + 
				  "<SOAP-ENV:Header/>\r\n" + 
				  "<SOAP-ENV:Body>\r\n" + 
				  "<ns10:DesbloquearLogicoRequest xmlns:ns10=\"http://api.riachuelo.net/integracao/financeiro/cartao/DesbloquearLogico/v4\" xmlns:ns11=\"http://api.riachuelo.net/integracao/financeiro/cartao/EnviarSenhaProvisoria/v4\" xmlns:ns12=\"http://api.riachuelo.net/integracao/financeiro/cartao/ValidarRecebimento/v4\" xmlns:ns13=\"http://api.riachuelo.net/modelo/financeiro/Cartao/v2\" xmlns:ns14=\"http://api.riachuelo.net/modelo/corporativo/Email/v2\" xmlns:ns15=\"http://api.riachuelo.net/modelo/corporativo/Cliente/v3\" xmlns:ns16=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v2\" xmlns:ns17=\"http://api.riachuelo.net/modelo/corporativo/Endereco/v2\" xmlns:ns18=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v3\" xmlns:ns19=\"http://api.riachuelo.net/integracao/financeiro/cliente/ConsultarCliente/v3\" xmlns:ns2=\"http://ws.cliente.csi.plusoft.com.br\" xmlns:ns20=\"http://api.riachuelo.net/integracao/financeiro/cliente/ConsultarInformacoesCadastrais/v3\" xmlns:ns3=\"http://vo.ws.cliente.csi.plusoft.com.br\" xmlns:ns4=\"http://vo.crm.csi.plusoft.com.br\" xmlns:ns5=\"http://api.riachuelo.net/modelo/financeiro/Cartao/v3\" xmlns:ns6=\"http://api.riachuelo.net/modelo/corporativo/Email/v3\" xmlns:ns7=\"http://api.riachuelo.net/modelo/corporativo/Cliente/v4\" xmlns:ns8=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v4\" xmlns:ns9=\"http://api.riachuelo.net/modelo/corporativo/Endereco/v3\">\r\n" + 
				  "<ns10:cpf>60609214853</ns10:cpf>\r\n" + 
				  "<ns10:ultimosDigitosCartao>7116</ns10:ultimosDigitosCartao>\r\n" + 
				  "<ns10:loginUsuario>URA</ns10:loginUsuario>\r\n" + 
				  "<ns10:canalDesbloqueio>URA</ns10:canalDesbloqueio>\r\n" + 
				  "</ns10:DesbloquearLogicoRequest>\r\n" + 
				  "</SOAP-ENV:Body>\r\n" + 
				  "</SOAP-ENV:Envelope>";
		
		given()
			.log().all()
			.auth().basic("dev-svc-fin", "R14chu3l0")
			.contentType("application/soap+xml; charset=UTF-8;")
			.body(myEnvelope)
		.when()
			.post("http://osbhml.riachuelo.net:80/Financeiro/Cartao/v4")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void obterUltimasTransacoesContaPorTipo () {
		given()
			.log().all()
			.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
			.contentType("application/json")
			.body("{\"cliente\":{\"cpf\":\"5877437607\"},\"tipoTransacao\":\"PAGAMENTO_RECEBIDO\",\"quantidadeTransacoes\":3,\"canal\":\"URA\"}")
		.when()
			.post("http://osbhml.riachuelo.net/Financeiro/Cliente/API/v3/obterUltimasTransacoesContaPorTipo")
		.then()
			.log().all()
			.statusCode(200)
	;
	}
	
	@Test
	public void saldos() {
		given()
			.log().all()
			.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
			.param("cpf",  "35858349811")
			.param("ConsultarSaldosRequest.tipo", "5")
			.param("canal", "URA")
			.contentType("application/json")
		.when()
			.get("http://osbhml.riachuelo.net/Financeiro/Cliente/API/v3/saldos")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void consultarFatura () {
		given()
			.log().all()
			.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
			.param("cpf",  "35858349811")
			.param("quantidadeFaturasAnteriores", "1")
			.param("canal", "URA")
			.param("faturaEmAberto", "true")
		.when()
			.get("http://osbhml.riachuelo.net/Financeiro/Fatura/API/v3/")
		.then()
			.log().all()
			.statusCode(200)
		;	
	}
	
	@Test
	public void consultarDiasEmAtraso () {
		given()
			.log().all()
			.auth().basic("sturasoa", "$v2NqDnSYX")
			.param("cpf",  "35858349811")
			.param("canal", "URA")
		.when()
			.get("http://osbhml.riachuelo.net/Financeiro/Fatura/API/v3/consultarDiasEmAtraso")
		.then()
			.log().all()
			.statusCode(200)
	;	
	}
	
	@Test
	public void faturaDetalhes () {
		given()
			.log().all()
			.auth().basic("SVC-OSB-FIN-CO", "R1achu3l0")
			.param("cpf",  "00610349163")
			.param("dataVencimento",  "2019-03-30")
			.param("canal", "URA")
		.when()
			.get("http://osbhml.riachuelo.net/Financeiro/Fatura/API/v3/detalhes")
		.then()
			.log().all()
			.statusCode(200)
	;	
	}
	
	@Test
	public void obterLinahDigitavel () {
		given()
			.log().all()
			.auth().basic("dev-svc-fin", "R14chu3l0")
			.contentType("application/json")
			.body("{\"cliente\":{\"cpf\":\"27824692825\"},\"boleto\":{\"datas\":{\"vencimento\":\"2017-05-30\"},\"valores\":{\"valorDocumento\":{\"valor\":20}}},\"canal\":\"URA\"}")
		.when()
			.post("http://osbhml.riachuelo.net/Financeiro/Fatura/API/v3/obterLinhaDigitavel")
		.then()
			.log().all()
			.statusCode(200)
	;
	}
	
	
	
	@Test
	public void criateAtendimentoPadrao () {
		
		String myEnvelope = 
				"<SOAP-ENV:Envelope xmlns:SOAP-ENV=\"http://schemas.xmlsoap.org/soap/envelope/\"><SOAP-ENV:Header/>\r\n" + 
				"<SOAP-ENV:Body>\r\n" + 
				"<ns2:createAtendimentoPadrao xmlns:ns2=\"http://ws.cliente.csi.plusoft.com.br\" xmlns:ns10=\"http://api.riachuelo.net/integracao/financeiro/cartao/DesbloquearLogico/v4\" xmlns:ns11=\"http://api.riachuelo.net/integracao/financeiro/cartao/EnviarSenhaProvisoria/v4\" xmlns:ns12=\"http://api.riachuelo.net/integracao/financeiro/cartao/ValidarRecebimento/v4\" xmlns:ns13=\"http://api.riachuelo.net/modelo/financeiro/Cartao/v2\" xmlns:ns14=\"http://api.riachuelo.net/modelo/corporativo/Endereco/v2\" xmlns:ns15=\"http://api.riachuelo.net/modelo/corporativo/Cliente/v3\" xmlns:ns16=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v2\" xmlns:ns17=\"http://api.riachuelo.net/modelo/corporativo/Email/v2\" xmlns:ns18=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v3\" xmlns:ns19=\"http://api.riachuelo.net/integracao/financeiro/cliente/ConsultarCliente/v3\" xmlns:ns20=\"http://api.riachuelo.net/integracao/financeiro/cliente/ConsultarInformacoesCadastrais/v3\" xmlns:ns3=\"http://vo.ws.cliente.csi.plusoft.com.br\" xmlns:ns4=\"http://vo.crm.csi.plusoft.com.br\" xmlns:ns5=\"http://api.riachuelo.net/modelo/financeiro/Cartao/v3\" xmlns:ns6=\"http://api.riachuelo.net/modelo/corporativo/Endereco/v3\" xmlns:ns7=\"http://api.riachuelo.net/modelo/corporativo/Cliente/v4\" xmlns:ns8=\"http://api.riachuelo.net/modelo/corporativo/Telefone/v4\" xmlns:ns9=\"http://api.riachuelo.net/modelo/corporativo/Email/v3\">\r\n" + 
				"<ns2:vo>\r\n" + 
				"	<ns4:atpaInManifconcluir>false</ns4:atpaInManifconcluir>\r\n" + 
				"	<ns4:atpaInTpatendimento xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	<ns4:atpaMensagens xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	<ns4:atpaNrTempoatendimento>0</ns4:atpaNrTempoatendimento>\r\n" + 
				"	<ns4:atpaTxTexto>URA BAND - INFORMACOES - LIMITE DE CREDITO - MASTER</ns4:atpaTxTexto>\r\n" + 
				"	<ns4:chamDsProtocolo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	<ns4:enviarEmailResponsavel xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	<ns4:idAsn1CdAssuntonivel1>0</ns4:idAsn1CdAssuntonivel1>\r\n" + 
				"	<ns4:idAsn2CdAssuntonivel2>0</ns4:idAsn2CdAssuntonivel2>\r\n" + 
				"	<ns4:idAtpdCdAtendpadrao>27</ns4:idAtpdCdAtendpadrao>\r\n" + 
				"	<ns4:idChamCdChamado>0</ns4:idChamCdChamado>\r\n" + 
				"	<ns4:idColoCdComolocalizou>0</ns4:idColoCdComolocalizou>\r\n" + 
				"	<ns4:idCompCdSequencial>0</ns4:idCompCdSequencial>\r\n" + 
				"	<ns4:idEmprCdEmpresa>0</ns4:idEmprCdEmpresa>\r\n" + 
				"	<ns4:idEsanCdEstadoanimo>0</ns4:idEsanCdEstadoanimo>\r\n" + 
				"	<ns4:idFocoCdFormacontato>0</ns4:idFocoCdFormacontato>\r\n" + 
				"	<ns4:idFuncCdAtendente>0</ns4:idFuncCdAtendente>\r\n" + 
				"	<ns4:idFuncCdResponsavel>0</ns4:idFuncCdResponsavel>\r\n" + 
				"	<ns4:idGrmaCdGrupomanifestacao>0</ns4:idGrmaCdGrupomanifestacao>\r\n" + 
				"	<ns4:idGrsaCdGrausatisfacao>0</ns4:idGrsaCdGrausatisfacao>\r\n" + 
				"	<ns4:idInfoCdSequencial>0</ns4:idInfoCdSequencial>\r\n" + 
				"	<ns4:idMatpCdManiftipo>0</ns4:idMatpCdManiftipo>\r\n" + 
				"	<ns4:idMidiCdMidia>0</ns4:idMidiCdMidia>\r\n" + 
				"	<ns4:idMnzeCdManifNivelZero>0</ns4:idMnzeCdManifNivelZero>\r\n" + 
				"	<ns4:idOporCdOportunidade>0</ns4:idOporCdOportunidade>\r\n" + 
				"	<ns4:idPessCdPessoa>0</ns4:idPessCdPessoa>\r\n" + 
				"	<ns4:idStmaCdStatusmanif>0</ns4:idStmaCdStatusmanif>\r\n" + 
				"	<ns4:idSugrCdSupergrupo>0</ns4:idSugrCdSupergrupo>\r\n" + 
				"	<ns4:idTpmaCdTpmanifestacao>0</ns4:idTpmaCdTpmanifestacao>\r\n" + 
				"	<ns4:idTpreCdTiporetorno>0</ns4:idTpreCdTiporetorno>\r\n" + 
				"	<ns4:maniNrSequencia>0</ns4:maniNrSequencia>\r\n" + 
				"	<ns4:pessCdCorporativo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	<ns4:pessoaVo>\r\n" + 
				"		<ns4:lstEmail xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:lstEndereco xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:lstTelefone xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:pessDsCgccpf>86778935553</ns4:pessDsCgccpf>\r\n" + 
				"		<ns4:pessDsIerg xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:pessInPfj xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:pessInSexo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"		<ns4:pessNmPessoa xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:nil=\"true\"/>\r\n" + 
				"	</ns4:pessoaVo>\r\n" + 
				"</ns2:vo>\r\n" + 
				"<ns2:idIdioCdIdioma>1</ns2:idIdioCdIdioma>\r\n" + 
				"</ns2:createAtendimentoPadrao>\r\n" + 
				"</SOAP-ENV:Body>\r\n" + 
				"</SOAP-ENV:Envelope>";
		given()
			.log().all()
			.contentType("application/soap+xml; charset=UTF-8;")
			.body(myEnvelope)
		.when()
			.post("http://h-crm.lojasriachuelo.com.br:8280/csiweb-riachuelo/services/AtendimentoPadraoWS")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void integracaoUra () {
		
		String myEnvelope = 
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ws=\"http://ws.cliente.csi.plusoft.com.br\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <ws:gerarProtocoloUra>\r\n" + 
				"         <ws:idFuncCdFuncionario>1</ws:idFuncCdFuncionario>\r\n" + 
				"      </ws:gerarProtocoloUra>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		given()
			.log().all()
			.contentType("application/soap+xml; charset=UTF-8;")
			.body(myEnvelope)
		.when()
			.post("http://h-crm.lojasriachuelo.com.br:8280/csiweb-riachuelo/services/IntegracaoUraWS")
		.then()
			.log().all()
			.statusCode(200)
		;
	}
	
	@Test
	public void consultarCliente () {
		
		String myEnvelope = 
				"<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:v3=\"http://api.riachuelo.net/integracao/financeiro/cliente/ConsultarCliente/v3\" xmlns:v31=\"http://api.riachuelo.net/modelo/corporativo/Cliente/v3\">\r\n" + 
				"   <soapenv:Header/>\r\n" + 
				"   <soapenv:Body>\r\n" + 
				"      <v3:ConsultarClienteRequest>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <v3:cliente>\r\n" + 
				"            <v31:cpf>49887645915</v31:cpf>\r\n" + 
				"         </v3:cliente>\r\n" + 
				"         <!--Optional:-->\r\n" + 
				"         <v3:detalhado>true</v3:detalhado>\r\n" + 
				"         <v3:canal>URA</v3:canal>\r\n" + 
				"         <v3:tipoConsulta>CPF</v3:tipoConsulta>\r\n" + 
				"      </v3:ConsultarClienteRequest>\r\n" + 
				"   </soapenv:Body>\r\n" + 
				"</soapenv:Envelope>";
		
		given()
			.log().all()
			.auth().basic("sturasoa", "$v2NqDnSYX")
			.contentType("text/xml; charset=UTF-8;")
			.body(myEnvelope)
		.when()
			.post("http://osbhml.riachuelo.net:80/Financeiro/Cliente/v3/")
		.then()
			.log().all()
			.statusCode(200)
		;
    }
	
}

