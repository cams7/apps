package cams7.siscom.jpa.domain;

import javax.persistence.Embeddable;

@Embeddable
public class ItemCotacaoDetalhe {
	private Double total;

	private Double unitario;

	public ItemCotacaoDetalhe() {
		super();
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getUnitario() {
		return unitario;
	}

	public void setUnitario(Double unitario) {
		this.unitario = unitario;
	}

}
