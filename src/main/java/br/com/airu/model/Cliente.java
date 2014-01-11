package br.com.airu.model;

import java.util.ArrayList;
import java.util.List;

public class Cliente {

	private String nome;
	private List<Pedido> pedidos = new ArrayList<Pedido>();
	private List<Coupon> coupons = new ArrayList<Coupon>();

	public Cliente() {
	}

	public Cliente(String nome) {
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

}