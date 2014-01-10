package br.com.airu.model;

public enum EProduto {
	
	COMUM(0), MANUFATURADO(1), IMPORTADO(2);
	
	private int value;

	private EProduto(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
}