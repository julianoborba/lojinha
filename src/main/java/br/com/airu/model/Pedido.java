package br.com.airu.model;

public class Pedido {
	
	private long codigo;
	private Produto produto;
	private int quantidade;

	public Pedido() {
	}

	public Pedido(Produto produto, int quantidade) {
		this.produto = produto;
		this.quantidade = quantidade;
	}
	
	public Pedido(long codigo, Produto produto, int quantidade) {
		this.codigo = codigo;
		this.produto = produto;
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	@Override
	public String toString() {
		return "Pedido [codigo=" + codigo + ", produto=" + produto
				+ ", quantidade=" + quantidade + "]";
	}

}