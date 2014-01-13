package br.com.airu.model;

import java.math.BigDecimal;

public class Coupon implements Comparable<Coupon> {

	private long codigo;
	private BigDecimal desconto;
	private BigDecimal valorMinimo;

	public Coupon() {
	}
	
	public Coupon(long codigo, BigDecimal desconto, BigDecimal valorMinimo) {
		this.codigo = codigo;
		this.desconto = desconto;
		this.valorMinimo = valorMinimo;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public BigDecimal getDesconto() {
		return desconto;
	}

	public void setDesconto(BigDecimal desconto) {
		this.desconto = desconto;
	}

	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}

	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}

	@Override
	public String toString() {
		return "Coupon [codigo=" + codigo + ", desconto=" + desconto + ", valorMinimo=" + valorMinimo + "]";
	}

	@Override
	public int compareTo(Coupon c) {
		return this.valorMinimo.compareTo(c.valorMinimo);
	}

}