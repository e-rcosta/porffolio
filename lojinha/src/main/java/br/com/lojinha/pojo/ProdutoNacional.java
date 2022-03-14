package br.com.lojinha.pojo;

import br.com.lojinha.enums.Tamanho;
import br.com.lojinha.interfaces.Favorito;

//herança: classe herda da classe Produto
public class ProdutoNacional extends Produto implements Favorito {
    private double impostoNacional;

    public ProdutoNacional(String marcaInicial, Tamanho tamanhoInicial) {
        //super, passa para cima os parâmetros, no caso para a classe Produto
        super(marcaInicial, tamanhoInicial);
    }

    public double getImpostoNacional() {
        return this.impostoNacional;
    }

    public void setImpostoNacional(double impostoNacional) {
        this.impostoNacional = impostoNacional;
    }

    public String getDadosFavoritos() {
        return this.getNome() + ", " + this.getMarca() + " e " + this.getValor();
    }
}
