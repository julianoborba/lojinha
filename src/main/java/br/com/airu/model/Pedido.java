package br.com.airu.model;

import java.math.BigDecimal;
import java.util.HashMap;

public class Pedido {
	
	private long codigo;
	private int prazo;
	private HashMap<Produto, BigDecimal> produtos = new HashMap<Produto, BigDecimal>();
	private BigDecimal subTotal = new BigDecimal(0);
	private BigDecimal frete = new BigDecimal(0);
	private boolean usarCoupons;
	
	public Pedido() {
	}
	
	public Pedido(HashMap<Produto, BigDecimal> produtos) {
		this.produtos = produtos;
	}
	
	public Pedido(int prazo, HashMap<Produto, BigDecimal> produtos, BigDecimal subTotal, BigDecimal frete) {
		this.prazo = prazo;
		this.produtos = produtos;
		this.subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.frete = frete.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public Pedido(long codigo, int prazo, HashMap<Produto, BigDecimal> produtos, BigDecimal subTotal, BigDecimal frete) {
		this.codigo = codigo;
		this.prazo = prazo;
		this.produtos = produtos;
		this.subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
		this.frete = frete.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public HashMap<Produto, BigDecimal> getProdutos() {
		return produtos;
	}

	public void addProdutos(Produto produto, BigDecimal quantidade) {
		produtos.put(produto, quantidade);
	}

	public int getPrazo() {
		return prazo;
	}

	public void setPrazo(int prazo) {
		this.prazo = prazo;
	}

	public BigDecimal getSubTotal() {
		return subTotal;
	}

	public void setSubTotal(BigDecimal subTotal) {
		this.subTotal = subTotal.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public BigDecimal getFrete() {
		return frete;
	}

	public void setFrete(BigDecimal frete) {
		this.frete = frete.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public boolean isUsarCoupons() {
		return usarCoupons;
	}

	public void setUsarCoupons(boolean usarCoupons) {
		this.usarCoupons = usarCoupons;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", prazo=" + prazo + ", produtos="
				+ produtos + ", subTotal=" + subTotal + ", frete=" + frete
				+ ", usarCoupons=" + usarCoupons + "]";
	}
	
}