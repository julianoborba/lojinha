package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.airu.controller.LojinhaAiru;
import br.com.airu.model.Cliente;
import br.com.airu.model.Coupon;
import br.com.airu.model.EProduto;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class ClienteTest {

	@Test
	public void testCheckout() {
		
		Cliente dragonborn = new Cliente("Dragonborn");
		
		Produto caixa = new Produto("Potion of Vigorous Stamina", Utils.valorDecimal(1000), EProduto.COMUM, true);
		Produto boneca = new Produto("Daedric Sword", Utils.valorDecimal(3000), EProduto.MANUFATURADO);
		Produto relogio = new Produto("Ring of Namira", Utils.valorDecimal(6000), EProduto.IMPORTADO);
		
		Coupon coupon2 = new Coupon(01010, Utils.valorDecimal(500.00), Utils.valorDecimal(1000.00));
		Coupon coupon1 = new Coupon(0101, Utils.valorDecimal(250.00), Utils.valorDecimal(500.00));
		
		Pedido pedido1 = new Pedido(caixa, 2);
		Pedido pedido2 = new Pedido(boneca, 1);
		Pedido pedido3 = new Pedido(relogio, 1);
		
		dragonborn.addPedido(pedido1);
		dragonborn.addPedido(pedido2);
		dragonborn.addPedido(pedido3);
		
		dragonborn.addCoupon(coupon2);
		dragonborn.addCoupon(coupon1);
		
		System.out.println(new LojinhaAiru().fazCheckout(dragonborn));
		
		assertEquals("Pedido para Dragonborn\n" + "Valor total: 13250.00\n" + "Valor frete: 130.00\n" + "Prazo de entrega: 15 dias\n" + "Desconto: 750.00", new LojinhaAiru().fazCheckout(dragonborn));
				
	}
}