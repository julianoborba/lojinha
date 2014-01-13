package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.HashMap;

import org.junit.Test;

import br.com.airu.controller.LojinhaAiru;
import br.com.airu.model.Cliente;
import br.com.airu.model.Coupon;
import br.com.airu.model.EProduto;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class TCheckout1 {

	@Test
	public void testCheckout() {
		
		Cliente dragonborn = new Cliente("Dragonborn");
		
		Produto potion = new Produto("Potion of Vigorous Stamina", Utils.valorDecimal(1000), EProduto.COMUM, true);
		Produto sword = new Produto("Daedric Sword", Utils.valorDecimal(3000), EProduto.MANUFATURADO);
		Produto ring = new Produto("Ring of Namira", Utils.valorDecimal(6000), EProduto.IMPORTADO);
		
		Coupon coupon2 = new Coupon(01010, Utils.valorDecimal(500.00), Utils.valorDecimal(1000.00));
		Coupon coupon1 = new Coupon(0101, Utils.valorDecimal(250.00), Utils.valorDecimal(500.00));
		
		HashMap<Produto, BigDecimal> produtos = new HashMap<Produto, BigDecimal>();
		produtos.put(potion, new BigDecimal(2));
		produtos.put(sword, new BigDecimal(1));
		produtos.put(ring, new BigDecimal(1));
		
		Pedido pedido1 = new Pedido(produtos);
		pedido1.setUsarCoupons(true);
		
		dragonborn.addPedido(pedido1);
		dragonborn.addCoupon(coupon2);
		dragonborn.addCoupon(coupon1);
		
		assertEquals("Pedido número 1 para Dragonborn\n"
					+ "Valor total: 13500.00\n"
					+ "Valor frete: 130.00\n"
					+ "Prazo de entrega: 15 dias\n"
					+ "Desconto: 750.00\n"
					+ "-------------------------\n", new LojinhaAiru().fazerCheckout(dragonborn));
				
	}
}