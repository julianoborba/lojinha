package br.com.airu.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private long codigo;
	private String nome;
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Cliente() {
	}

	public Cliente(String nome) {
		this.nome = nome;
	}
	
	public Cliente(long codigo, String nome) {
		this.codigo = codigo;
		this.nome = nome;
	}

	public void addPedido(Pedido pedido) {
		pedidos.add(pedido);
	}
	
	public void addCoupon(Coupon coupon) {
		coupons.add(coupon);
	}

	public String getName() {
		return nome;
	}

	public List<Pedido> getPedidos() {
		return pedidos;
	}

	public List<Coupon> getCoupons() {
		return coupons;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", pedidos="
				+ pedidos + ", coupons=" + coupons + "]";
	}
	
}