package br.com.lojinha;

import br.com.lojinha.enums.Tamanho;
import br.com.lojinha.pojo.ItensInclusos;
import br.com.lojinha.pojo.Produto;
import br.com.lojinha.pojo.ProdutoInternacional;
import br.com.lojinha.pojo.ProdutoNacional;

import java.util.*;

public class LojinhaApp {
    public static void main(String[] args) {
        Produto meuProduto = new Produto("Apple", Tamanho.MEDIO);

        meuProduto.setNome("Celular XR");
        meuProduto.setValor(3500.31);

        List<ItensInclusos> itensInclusos = new ArrayList<>();
        itensInclusos.add(new ItensInclusos("Carregador", 1));
        itensInclusos.add(new ItensInclusos("Fone de ouvido", 1));

        meuProduto.setItensInclusos(itensInclusos);

        for (ItensInclusos itemAtual : meuProduto.getItensInclusos()) {
            System.out.println(itemAtual.getQuantidade() + " " + itemAtual.getNome());
        }

        ProdutoNacional meuProdutoNacional = new ProdutoNacional("Arezzo", Tamanho.MEDIO);
        meuProdutoNacional.setNome("Sandália");
        meuProdutoNacional.setImpostoNacional(0.55789);
        meuProdutoNacional.setValor(200.00);
        System.out.println(meuProdutoNacional.getDadosFavoritos());
        System.out.println(meuProdutoNacional.getImpostoNacional());

        ProdutoInternacional meuProdutoInternacional = new ProdutoInternacional("Apply", Tamanho.GRANDE);
        meuProdutoInternacional.setNome("Iphone 13");
        meuProdutoInternacional.setTaxaDeImportacao(0.35);
        meuProdutoInternacional.setValor(-99.99);
        System.out.println(meuProdutoInternacional.getDadosFavoritos());
        System.out.println(meuProdutoInternacional.getTaxaDeImportacao());
    }
}
