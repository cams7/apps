package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cams7.siscom.jpa.domain.Cabecario;

/**
 * The persistent class for the orc_cab database table.
 * 
 */
@Entity
@Table(name = "orc_cab")
@AttributeOverrides({
		@AttributeOverride(name = "data", column = @Column(name = "data_orc_cab")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_orc_cab")) })
@NamedQuery(name = "OrcamentoCabecario.findAll", query = "SELECT o FROM OrcamentoCabecarioEntity o")
public class OrcamentoCabecarioEntity extends Cabecario<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "orc_cab_seq", sequenceName = "orc_cab_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orc_cab_seq")
	@Column(name = "cod_orc_cab")
	private Long id;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	// bi-directional many-to-one association to FuncionarioEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_funcionario")
	private FuncionarioEntity funcionario;

	// bi-directional many-to-one association to OrcamentoDetalheEntity
	@OneToMany(mappedBy = "orcamento")
	private List<OrcamentoDetalheEntity> detalhes;

	public OrcamentoCabecarioEntity() {
		super();
	}

	public OrcamentoCabecarioEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FuncionarioEntity getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public List<OrcamentoDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<OrcamentoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public OrcamentoDetalheEntity addDetalhe(OrcamentoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setOrcamento(this);

		return detalhe;
	}

	public OrcamentoDetalheEntity removeDetalhe(OrcamentoDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setOrcamento(null);

		return detalhe;
	}

}