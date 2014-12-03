package cams7.siscom.jpa.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import cams7.jpa.domain.BaseEntity;

@MappedSuperclass
public abstract class Detalhe<PK extends Serializable> extends BaseEntity<PK> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private PK id;

	private Integer quantidade;

	private Double total;

	private Double unitario;

	public Detalhe() {
		super();
	}

	public Detalhe(PK id) {
		super(id);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
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
