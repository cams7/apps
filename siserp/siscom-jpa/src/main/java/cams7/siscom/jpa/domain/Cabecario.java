package cams7.siscom.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import cams7.jpa.domain.BaseEntity;

@MappedSuperclass
public abstract class Cabecario<PK extends Serializable> extends BaseEntity<PK> {

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	private Double valor;

	public Cabecario() {
		super();
	}

	public Cabecario(PK id) {
		super(id);
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
