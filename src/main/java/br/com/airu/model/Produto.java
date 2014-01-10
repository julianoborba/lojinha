package br.com.airu.model;

import java.math.BigDecimal;

public class Produto {

	public static final int COMUM = 0;
	public static final int MANUFATURADO = 1;
	public static final int IMPORTADO = 2;

	private String titulo;
	private BigDecimal preco;
	private int tipo;
	private boolean perecivel;

	public Produto() {
	}

	public Produto(String titulo, BigDecimal preco, int tipo) {
		this.titulo = titulo;
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.tipo = tipo;
	}
	
	public Produto(String titulo, BigDecimal preco, int tipo, boolean perecivel) {
		this.titulo = titulo;
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.tipo = tipo;
		this.perecivel = perecivel;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public boolean isPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	@Override
	public String toString() {
		return "Produto [titulo=" + titulo + ", preco=" + preco + ", tipo=" + tipo + ", perecivel=" + perecivel + "]";
	}

}