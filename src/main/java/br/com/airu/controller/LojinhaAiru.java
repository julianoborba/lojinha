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
		
		if (cliente == null)
			return "O checkout não foi realizado. Dados insuficientes.";
			
		List<Pedido> pedidos = cliente.getPedidos();
		
		if (!pedidos.isEmpty()) {

			BigDecimal total = Utils.valorDecimal(0);
			BigDecimal frete = Utils.valorDecimal(0);
			
			StringBuilder result = new StringBuilder("Pedido para ");
			result.append(cliente.getName());
			result.append("\n");
			
			int prazo = 0;
			
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
									frete.add(EFrete.COMUM.getValue().multiply(adicionalPerecivel)) : 
										frete.add(EFrete.COMUM.getValue());
						break;
					case MANUFATURADO:
						total = total.add(preco.multiply(qtde));
						prazo = prazo < 5 ? 5 : prazo;
						frete = produto.isPerecivel() ? 
									frete.add(EFrete.MANUFATURADO.getValue().multiply(adicionalPerecivel)) : 
										frete.add(EFrete.MANUFATURADO.getValue());
						break;
					case IMPORTADO:
						BigDecimal qtdeNova = qtde.multiply(new BigDecimal(1.5));
						total = total.add(Utils.escalaDecimal(preco.multiply(qtdeNova)));
						prazo = prazo < 15 ? 15 : prazo;
						frete = produto.isPerecivel() ? 
									frete.add(EFrete.IMPORTADO.getValue().multiply(adicionalPerecivel)) : 
										frete.add(EFrete.IMPORTADO.getValue());
						break;
				}
			}
			
			//--> Logica dos coupons
			boolean houveDesconto = false;
			BigDecimal desconto = Utils.valorDecimal(0);
			List<Coupon> coupons = cliente.getCoupons();
			if (!coupons.isEmpty()) {
				Collections.sort(coupons);
				for (Coupon coupon : coupons) {
					if (total.compareTo(coupon.getValorMinimo()) >= 0) {
						total = total.subtract(coupon.getDesconto());
						desconto = desconto.add(coupon.getDesconto());
					}
				}
				houveDesconto = desconto.compareTo(BigDecimal.ZERO) > 0;
			}
			//<--
			
			result.append("Valor total: ");
			result.append(total + "\n");
			result.append("Valor frete: ");
			result.append(frete + "\n");
			result.append("Prazo de entrega: ");
			result.append(prazo + " dias\n");
			if (houveDesconto)
				result.append("Desconto: " + desconto);
		
			return result.toString();
			
		} else 
			return "Nenhum pedido foi feito.";
		
	}
}