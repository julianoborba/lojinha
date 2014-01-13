package br.com.airu.controller;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;

import br.com.airu.model.Cliente;
import br.com.airu.model.Coupon;
import br.com.airu.model.EFrete;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class LojinhaAiru {

	public String fazerCheckout(Cliente cliente) {
		
		if (cliente == null)
			return "O checkout não foi realizado. Dados insuficientes.";
			
		List<Pedido> pedidos = cliente.getPedidos();
		if (!pedidos.isEmpty()) {

			StringBuilder resultado = new StringBuilder();
			int contadorDePedidos = 1;
			
			for (Pedido pedido : pedidos) {

				boolean houveDesconto = false;
				BigDecimal desconto = new BigDecimal(0);
				BigDecimal total = new BigDecimal(0);
			
				if (!pedido.getProdutos().isEmpty()) {
					
					resultado.append("Pedido número ");
					resultado.append(contadorDePedidos);
					resultado.append(" para ");
					resultado.append(cliente.getNome());
					resultado.append("\n");
					
					for (Entry<Produto, BigDecimal> entry : pedido.getProdutos().entrySet()) {

						// O Hashmap representa <Produto, Quantidade>
						Produto produto = entry.getKey();
						BigDecimal quantidade = entry.getValue();
						BigDecimal preco = produto.getPreco();
						BigDecimal adicionalPerecivel = new BigDecimal(10);
						
						switch (produto.getTipo()) {
							case COMUM:
								pedido.setSubTotal(pedido.getSubTotal().add(preco.multiply(quantidade)));
								pedido.setPrazo(pedido.getPrazo() < 3 ? 3 : pedido.getPrazo());
								pedido.setFrete(produto.isPerecivel() ? 
										pedido.getFrete().add(EFrete.COMUM.getValue().multiply(adicionalPerecivel)) : 
											pedido.getFrete().add(EFrete.COMUM.getValue()));
								break;
							case MANUFATURADO:
								pedido.setSubTotal(pedido.getSubTotal().add(preco.multiply(quantidade)));
								pedido.setPrazo(pedido.getPrazo() < 5 ? 5 : pedido.getPrazo());
								pedido.setFrete(produto.isPerecivel() ? 
										pedido.getFrete().add(EFrete.MANUFATURADO.getValue().multiply(adicionalPerecivel)) : 
											pedido.getFrete().add(EFrete.MANUFATURADO.getValue()));
								break;
							case IMPORTADO:
								BigDecimal qtdeNova = quantidade.multiply(new BigDecimal(1.5));
								pedido.setSubTotal(pedido.getSubTotal().add(Utils.escalaDecimal(preco.multiply(qtdeNova))));
								pedido.setPrazo(pedido.getPrazo() < 15 ? 15 : pedido.getPrazo());
								pedido.setFrete(produto.isPerecivel() ? 
										pedido.getFrete().add(EFrete.IMPORTADO.getValue().multiply(adicionalPerecivel)) : 
											pedido.getFrete().add(EFrete.IMPORTADO.getValue()));
								break;
						}
					}
					
					// Desconto com coupons caso o cliente desejar utilizar no pedido corrente
					if (pedido.isUsarCoupons()) {
						List<Coupon> coupons = cliente.getCoupons();
						if (!coupons.isEmpty()) {
							Collections.sort(coupons);
							for (Coupon coupon : coupons) {
								if (pedido.getSubTotal().compareTo(coupon.getValorMinimo()) >= 0) {
									total = pedido.getSubTotal().subtract(coupon.getDesconto());
									desconto = desconto.add(coupon.getDesconto());
								}
							}
							houveDesconto = desconto.compareTo(BigDecimal.ZERO) > 0;
						}
					}
					
					resultado.append("Valor total: ");
					resultado.append(houveDesconto ? total : pedido.getSubTotal());
					resultado.append("\n");
					resultado.append("Valor frete: ");
					resultado.append(pedido.getFrete());
					resultado.append("\n");
					resultado.append("Prazo de entrega: ");
					resultado.append(pedido.getPrazo());
					resultado.append(" dias");
					if (houveDesconto) {
						resultado.append("\nDesconto: ");
						resultado.append(desconto);
					}
					resultado.append("\n-------------------------\n");
					
				} else
					return "Não há produtos no pedido.";
				
				contadorDePedidos++;
			}
			
			return resultado.toString();
			
		} else 
			return "Nenhum pedido foi feito.";
	}
}