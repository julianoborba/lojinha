package br.com.airu.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import br.com.airu.utils.Utils;

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

	public String checkout() {
		
		BigDecimal total = Utils.valorDecimal(0);
		BigDecimal frete = Utils.valorDecimal(0);
		String result = "Pedido para " + getName() + "\n";
		int prazo = 0;
		
		if (!pedidos.isEmpty()) {
			for (Pedido pedido : pedidos) {
				
				Produto produto = pedido.getProduto();
				BigDecimal preco = produto.getPreco();
				BigDecimal qtde = new BigDecimal(pedido.getQuantidade());
				BigDecimal adicionalPerecivel = new BigDecimal(10);
				
				switch (produto.getTipo()) {
					case Produto.COMUM:
						total = total.add(preco.multiply(qtde));
						prazo = prazo < 3 ? 3 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(Pedido.FRETE_COMUM.multiply(adicionalPerecivel)) : frete.add(Pedido.FRETE_COMUM);
						break;
					case Produto.MANUFATURADO:
						total = total.add(preco.multiply(qtde));
						prazo = prazo < 5 ? 5 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(Pedido.FRETE_MANUFATURADO.multiply(adicionalPerecivel)) : frete.add(Pedido.FRETE_MANUFATURADO);
						break;
					case Produto.IMPORTADO:
						BigDecimal qtdeNova = qtde.multiply(new BigDecimal(1.5));
						total = total.add(Utils.escalaDecimal(preco.multiply(qtdeNova)));
						prazo = prazo < 15 ? 15 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(Pedido.FRETE_IMPORTADO.multiply(adicionalPerecivel)) : frete.add(Pedido.FRETE_IMPORTADO);
						break;
				}
			}
		}
		
		BigDecimal desconto = Utils.valorDecimal(0);
		boolean comDesconto = false;
		
		// Lógica dos coupons
		if (!coupons.isEmpty()) {
			Collections.sort(coupons);
			for (Coupon coupon : coupons) {
				if (total.compareTo(coupon.getValor_minimo()) >= 0) {
					comDesconto = true;
					total = total.subtract(coupon.getDesconto());
					desconto = desconto.add(coupon.getDesconto());
				}
			}
		}
		
		result += "Valor total: " + total + "\n";
		result += "Valor frete: " + frete + "\n";
		result += "Prazo de entrega: " + prazo + " dias\n";
		result += comDesconto ? "Desconto: " + desconto : "";
		return result;
		
	}
}