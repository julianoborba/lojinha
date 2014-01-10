package br.com.airu.model;

import java.math.BigDecimal;

import br.com.airu.utils.Utils;

public class Pedido {
	
	public static final BigDecimal FRETE_COMUM = Utils.valorDecimal(10);
	public static final BigDecimal FRETE_MANUFATURADO = Utils.valorDecimal(10);
	public static final BigDecimal FRETE_IMPORTADO = Utils.valorDecimal(20);

	private Produto produto;
	private int quantidade;

	public Pedido() {
	}

	public Pedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	@Override
	public String toString() {
		return "Pedido [produto=" + produto + ", quantidade=" + quantidade + "]";
	}
	
}