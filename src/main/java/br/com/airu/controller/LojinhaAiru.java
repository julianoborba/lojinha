package br.com.airu.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import br.com.airu.model.Cliente;
import br.com.airu.model.Coupon;
import br.com.airu.model.EFrete;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class LojinhaAiru {

	public String fazCheckout(Cliente cliente) {
		
		BigDecimal total = Utils.valorDecimal(0);
		BigDecimal frete = Utils.valorDecimal(0);
		String result = "Pedido para " + cliente.getName() + "\n";
		int prazo = 0;
		
		List<Pedido> pedidos = cliente.getPedidos();
		if (!pedidos.isEmpty()) {
			for (Pedido pedido : pedidos) {
				
				Produto produto = pedido.getProduto();
				BigDecimal preco = produto.getPreco();
				BigDecimal qtde = new BigDecimal(pedido.getQuantidade());
				BigDecimal adicionalPerecivel = new BigDecimal(10);
				
				switch (produto.getTipo()) {
					case COMUM:
						total = total.add(preco.multiply(qtde));
						prazo = prazo < 3 ? 3 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(EFrete.COMUM.getValue().multiply(adicionalPerecivel)) : frete.add(EFrete.COMUM.getValue());
						break;
					case MANUFATURADO:
						total = total.add(preco.multiply(qtde));
						prazo = prazo < 5 ? 5 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(EFrete.MANUFATURADO.getValue().multiply(adicionalPerecivel)) : frete.add(EFrete.MANUFATURADO.getValue());
						break;
					case IMPORTADO:
						BigDecimal qtdeNova = qtde.multiply(new BigDecimal(1.5));
						total = total.add(Utils.escalaDecimal(preco.multiply(qtdeNova)));
						prazo = prazo < 15 ? 15 : prazo;
						frete = produto.isPerecivel() ? 
								frete.add(EFrete.IMPORTADO.getValue().multiply(adicionalPerecivel)) : frete.add(EFrete.IMPORTADO.getValue());
						break;
				}
			}
		}
		
		BigDecimal desconto = Utils.valorDecimal(0);
		boolean comDesconto = false;
		
		// L�gica dos coupons
		List<Coupon> coupons = cliente.getCoupons();
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