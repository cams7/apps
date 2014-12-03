package cams7.siscom.jpa.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import cams7.apps.jpa.domain.BaseEntity;

@MappedSuperclass
public abstract class FechamentoCheque<PK extends Serializable> extends
		BaseEntity<PK> {

	private static final long serialVersionUID = 1L;

	@Size(min = 5, max = 7)
	private String mesAno;

	public FechamentoCheque() {
		super();
	}

	public FechamentoCheque(PK id) {
		super(id);
	}

	public String getMesAno() {
		return mesAno;
	}

	public void setMesAno(String mesAno) {
		this.mesAno = mesAno;
	}

}
