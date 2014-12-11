package br.com.cams7.siscom.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class Cheque<PK extends Serializable> extends
		FechamentoCheque<PK> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CHEQUE_GENERATOR")
	private PK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Size(min = 5, max = 50)
	private String historico;

	@Min(5)
	private Long numero;

	private Double valor;

	public Cheque() {
		super();
	}

	public Cheque(PK id) {
		super(id);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	public Long getNumero() {
		return numero;
	}

	public void setNumero(Long numero) {
		this.numero = numero;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
