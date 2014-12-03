package cams7.siscom.jpa.domain.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the cliente_telefone database table.
 * 
 */
@Entity
@Table(name = "cliente_telefone")
@NamedQuery(name = "ClienteTelefone.findAll", query = "SELECT t FROM ClienteTelefoneEntity t")
public class ClienteTelefoneEntity extends cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cliente_telefone_seq", sequenceName = "cliente_telefone_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_telefone_seq")
	@Column(name = "cod_telefone")
	private Long id;

	@NotEmpty
	@Size(min = 8, max = 15)
	@Column(name = "numero_telefone")
	private String numero;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_telefone", length = 1, columnDefinition = "char(1)")
	private TipoTelefone tipo;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	public ClienteTelefoneEntity() {
		super();
	}

	public ClienteTelefoneEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumero() {
		return this.numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public TipoTelefone getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoTelefone tipo) {
		this.tipo = tipo;
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public enum TipoTelefone {
		CASA_FIXO, CELULAR, TRABALHO;
	}

}