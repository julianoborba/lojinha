package br.com.airu.utils;

import java.math.BigDecimal;

public class Utils {

	public static BigDecimal valorDecimal(int valor) {
		return new BigDecimal(valor).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal valorDecimal(double valor) {
		return new BigDecimal(valor).setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

	public static BigDecimal escalaDecimal(BigDecimal valor) {
		return valor.setScale(2, BigDecimal.ROUND_HALF_EVEN);
	}

}