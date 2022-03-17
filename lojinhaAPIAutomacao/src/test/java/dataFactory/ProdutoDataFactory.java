package dataFactory;

import pojo.ComponentePojo;
import pojo.ProdutoPojo;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDataFactory {
    public static ProdutoPojo criarProdutoComumComOValorIgualA(double valor) {
        ProdutoPojo produto = new ProdutoPojo();
        produto.setProdutoNome("Tablet Sansung 10 A7");
        produto.setProdutoValor(valor);

        List<String> cores = new ArrayList<>();
        cores.add("Preto");
        cores.add("Braco");
        cores.add("Cinza");
        produto.setProdutoCores(cores);

        List<ComponentePojo> componentes = new ArrayList<>();

        ComponentePojo componente = new ComponentePojo();
        componente.setComponenteNome("Carregador");
        componente.setComponenteQuantidade(1);

        ComponentePojo segundoComponente = new ComponentePojo();
        segundoComponente.setComponenteNome("Memory Card");
        segundoComponente.setComponenteQuantidade(2);

        componentes.add(componente);
        componentes.add(segundoComponente);

        produto.setComponentes(componentes);

        return produto;
    }
}
