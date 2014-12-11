package br.com.cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
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

import br.com.cams7.siscom.jpa.domain.Endereco;

/**
 * The persistent class for the cliente_endereco database table.
 * 
 */
@Entity
@Table(name = "cliente_endereco")
@NamedQuery(name = "ClienteEndereco.findAll", query = "SELECT e FROM ClienteEnderecoEntity e")
public class ClienteEnderecoEntity extends br.com.cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cliente_endereco_seq", sequenceName = "cliente_endereco_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cliente_endereco_seq")
	@Column(name = "cod_endereco")
	private Long id;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "bairro", column = @Column(name = "bairro_endereco", length = Endereco.BAIRRO_LENGTH)),
			@AttributeOverride(name = "cep", column = @Column(name = "cep_endereco", length = Endereco.CEP_LENGTH)),
			@AttributeOverride(name = "cidade", column = @Column(name = "cidade_endereco", length = Endereco.CIDADE_LENGTH, nullable = false)),
			@AttributeOverride(name = "logradouro", column = @Column(name = "logradouro_endereco", length = Endereco.LOGRADOURO_LENGTH, nullable = false)),
			@AttributeOverride(name = "uf", column = @Column(name = "uf_endereco", length = Endereco.UF_LENGTH, nullable = false, columnDefinition = "char(2)")) })
	private Endereco endereco;

	@Size(min = 3, max = 20)
	@Column(name = "complemento_endereco")
	private String complemento;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	public ClienteEnderecoEntity() {
		super();
	}

	public ClienteEnderecoEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getComplemento() {
		return this.complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

}