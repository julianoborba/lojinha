package br.com.airu.model;

import java.math.BigDecimal;

public class Coupon implements Comparable<Coupon> {

	private int codigo;
	private BigDecimal desconto;
	private BigDecimal valor_minimo;

	public Coupon() {
	}
	
	public Coupon(int codigo, BigDecimal desconto, BigDecimal valor_minimo) {
		this.codigo = codigo;
		this.desconto = desconto;
		this.valor_minimo = valor_minimo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getValor_minimo() {
		return valor_minimo;
	}

	public void setValor_minimo(BigDecimal valor_minimo) {
		this.valor_minimo = valor_minimo;
	}

	@Override
	public String toString() {
		return "Coupon [codigo=" + codigo + ", desconto=" + desconto + ", valor_minimo=" + valor_minimo + "]";
	}

	@Override
	public int compareTo(Coupon c) {
		return this.valor_minimo.compareTo(c.valor_minimo);
	}

}