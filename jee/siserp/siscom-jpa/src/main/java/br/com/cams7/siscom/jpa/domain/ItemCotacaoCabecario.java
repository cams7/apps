package br.com.cams7.siscom.jpa.domain;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class ItemCotacaoCabecario {

	public static final byte CONDICOES_SIZE_MAX = 30;
	public static final byte ENTREGA_SIZE_MAX = 30;

	@Size(min = 5, max = CONDICOES_SIZE_MAX)
	private String condicoes;

	private Double desconto;

	@Size(min = 5, max = ENTREGA_SIZE_MAX)
	private String entrega;

	private Integer fornecedorId;

	private Double total;

	public ItemCotacaoCabecario() {
		super();
	}

	public String getCondicoes() {
		return condicoes;
	}

	public void setCondicoes(String condicoes) {
		this.condicoes = condicoes;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public String getEntrega() {
		return entrega;
	}

	public void setEntrega(String entrega) {
		this.entrega = entrega;
	}

	public Integer getFornecedorId() {
		return fornecedorId;
	}

	public void setFornecedorId(Integer fornecedorId) {
		this.fornecedorId = fornecedorId;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

}
