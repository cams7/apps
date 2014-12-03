package cams7.siscom.jpa.domain.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the cobranca database table.
 * 
 */
@Entity
@Table(name = "cobranca")
@NamedQuery(name = "Cobranca.findAll", query = "SELECT c FROM CobrancaEntity c")
public class CobrancaEntity extends cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cobranca_seq", sequenceName = "cobranca_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cobranca_seq")
	@Column(name = "cod_cobranca")
	private Long id;

	@Size(min = 5, max = 50)
	@Column(name = "advogado_processo")
	private String processoAdvogado;

	@NotEmpty
	@Size(min = 10, max = 50)
	@Column(name = "assunto_cobranca")
	private String assunto;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_processo")
	private Date processoData;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envio1")
	private Date envio1;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envio2")
	private Date envio2;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envio3")
	private Date envio3;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envio4")
	private Date envio4;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "envio5")
	private Date envio5;

	@Size(min = 10, max = 30)
	@Column(name = "num_processo")
	private String processoNumero;

	@NotNull
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column(name = "texto_cobranca")
	private String texto;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	public CobrancaEntity() {
		super();
	}

	public CobrancaEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProcessoAdvogado() {
		return this.processoAdvogado;
	}

	public void setProcessoAdvogado(String processoAdvogado) {
		this.processoAdvogado = processoAdvogado;
	}

	public String getAssunto() {
		return this.assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public Date getProcessoData() {
		return this.processoData;
	}

	public void setProcessoData(Date processoData) {
		this.processoData = processoData;
	}

	public Date getEnvio1() {
		return this.envio1;
	}

	public void setEnvio1(Date envio1) {
		this.envio1 = envio1;
	}

	public Date getEnvio2() {
		return this.envio2;
	}

	public void setEnvio2(Date envio2) {
		this.envio2 = envio2;
	}

	public Date getEnvio3() {
		return this.envio3;
	}

	public void setEnvio3(Date envio3) {
		this.envio3 = envio3;
	}

	public Date getEnvio4() {
		return this.envio4;
	}

	public void setEnvio4(Date envio4) {
		this.envio4 = envio4;
	}

	public Date getEnvio5() {
		return this.envio5;
	}

	public void setEnvio5(Date envio5) {
		this.envio5 = envio5;
	}

	public String getProcessoNumero() {
		return this.processoNumero;
	}

	public void setProcessoNumero(String processoNumero) {
		this.processoNumero = processoNumero;
	}

	public String getTexto() {
		return this.texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

}