package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.airu.model.Cliente;
import br.com.airu.model.Coupon;
import br.com.airu.model.Pedido;
import br.com.airu.model.Produto;
import br.com.airu.utils.Utils;

public class ClienteTest {

	@Test
	public void testCheckout() {
		
		Cliente zacarias = new Cliente("Dragonborn");
		
		Produto caixa = new Produto("Potion of Vigorous Stamina", Utils.valorDecimal(1000), Produto.COMUM, true);
		Produto boneca = new Produto("Daedric Sword", Utils.valorDecimal(3000), Produto.MANUFATURADO);
		Produto relogio = new Produto("Ring of Namira", Utils.valorDecimal(6000), Produto.IMPORTADO);
		
		Coupon coupon2 = new Coupon(01010, Utils.valorDecimal(500.00), Utils.valorDecimal(1000.00));
		Coupon coupon1 = new Coupon(0101, Utils.valorDecimal(250.00), Utils.valorDecimal(500.00));
		
		Pedido pedido1 = new Pedido(caixa, 2);
		Pedido pedido2 = new Pedido(boneca, 1);
		Pedido pedido3 = new Pedido(relogio, 1);
		
		zacarias.addPedido(pedido1);
		zacarias.addPedido(pedido2);
		zacarias.addPedido(pedido3);
		
		zacarias.addCoupon(coupon2);
		zacarias.addCoupon(coupon1);
		
		assertEquals("Pedido para Dragonborn\n" + "Valor total: 13250.00\n" + "Valor frete: 130.00\n" + "Prazo de entrega: 15 dias\n" + "Desconto: 750.00", zacarias.checkout());
				
	}
}