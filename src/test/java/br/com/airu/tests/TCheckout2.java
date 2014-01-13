package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

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
		
		Pedido pedido1 = new Pedido(potion, 2);
		Pedido pedido3 = new Pedido(ring, 1);
		
		dragonborn.addPedido(pedido1);
		dragonborn.addPedido(pedido3);
		
		assertEquals("Pedido para Dragonborn\n" + "Valor total: 11000.00\n"
				   + "Valor frete: 120.00\n" + "Prazo de entrega: 15 dias",
				   new LojinhaAiru().fazCheckout(dragonborn));
		
	}
}