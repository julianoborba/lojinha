package br.com.airu.model;

import java.math.BigDecimal;

public class Produto {

	private long codigo;
	private String titulo;
	private BigDecimal preco;
	private EProduto tipo;
	private boolean perecivel;

	public Produto() {
	}

	public Produto(long codigo, String titulo, BigDecimal preco, EProduto tipo) {
		this.codigo = codigo;
		this.titulo = titulo;
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.tipo = tipo;
	}
	
	public Produto(String titulo, BigDecimal preco, EProduto tipo) {
		this.titulo = titulo;
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.tipo = tipo;
	}
	
	public Produto(String titulo, BigDecimal preco, EProduto tipo, boolean perecivel) {
		this.titulo = titulo;
		this.preco = preco.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.tipo = tipo;
		this.perecivel = perecivel;
	}
	
	public Produto(long codigo, String titulo, BigDecimal preco, EProduto tipo, boolean perecivel) {
		this.codigo = codigo;
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

	public EProduto getTipo() {
		return tipo;
	}

	public void setTipo(EProduto tipo) {
		this.tipo = tipo;
	}

	public boolean isPerecivel() {
		return perecivel;
	}

	public void setPerecivel(boolean perecivel) {
		this.perecivel = perecivel;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Produto [codigo=" + codigo + ", titulo=" + titulo + ", preco="
				+ preco + ", tipo=" + tipo + ", perecivel=" + perecivel + "]";
	}

}