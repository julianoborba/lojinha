package br.com.airu.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import br.com.airu.controller.LojinhaAiru;
import br.com.airu.model.Cliente;

public class TCheckout4 {

	@Test
	public void test() {
		
		Cliente dragonborn = null;
		assertEquals("O checkout não foi realizado. Dados insuficientes.", new LojinhaAiru().fazCheckout(dragonborn));
		
	}
}