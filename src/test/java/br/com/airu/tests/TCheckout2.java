package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import br.com.airu.controller.LojinhaAiru;
import br.com.airu.model.Cliente;
import br.com.airu.model.EProduto;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class TCheckout2 {

	@Test
	public void test() {

		Cliente dragonborn = new Cliente("Dragonborn");
		
		Produto potion = new Produto("Potion of Vigorous Stamina", Utils.valorDecimal(1000), EProduto.COMUM, true);
		Produto ring = new Produto("Ring of Namira", Utils.valorDecimal(6000), EProduto.IMPORTADO);
		
		HashMap<Produto, BigDecimal> produtos = new HashMap<Produto, BigDecimal>();
		produtos.put(potion, new BigDecimal(2));
		produtos.put(ring, new BigDecimal(1));
		Pedido pedido1 = new Pedido(produtos);
		
		HashMap<Produto, BigDecimal> produtos2 = new HashMap<Produto, BigDecimal>();
		produtos2.put(potion, new BigDecimal(3));
		produtos2.put(ring, new BigDecimal(2));
		Pedido pedido2 = new Pedido(produtos2);
		pedido2.setUsarCoupons(true);
		
		dragonborn.addPedido(pedido1);
		dragonborn.addPedido(pedido2);
		
		assertEquals("Pedido número 1 para Dragonborn\n"
				+ "Valor total: 11000.00\n"
				+ "Valor frete: 120.00\n"
				+ "Prazo de entrega: 15 dias\n"
				+ "-------------------------\n"
				+ "Pedido número 2 para Dragonborn\n"
				+ "Valor total: 21000.00\n"
				+ "Valor frete: 120.00\n"
				+ "Prazo de entrega: 15 dias\n"
				+ "-------------------------\n", new LojinhaAiru().fazerCheckout(dragonborn));
		
	}
}