package br.com.airu.model;

import java.math.BigDecimal;

public enum EFrete {

	COMUM(new BigDecimal(10)), MANUFATURADO(new BigDecimal(10)), IMPORTADO(new BigDecimal(20));
	
	private BigDecimal value;

	private EFrete(BigDecimal value) {
		this.value = value;
	}
	
	public BigDecimal getValue() {
		return value;
	}

}