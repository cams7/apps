package br.com.cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

/**
 * The persistent class for the req_cab database table.
 * 
 */
@Entity
@Table(name = "req_cab")
@NamedQuery(name = "RequisicaoCabecario.findAll", query = "SELECT r FROM RequisicaoCabecarioEntity r")
public class RequisicaoCabecarioEntity extends
		br.com.cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "req_cab_seq", sequenceName = "req_cab_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "req_cab_seq")
	@Column(name = "cod_req_cab")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_req_cab")
	private Date data;

	// bi-directional many-to-one association to FuncionarioEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_funcionario")
	private FuncionarioEntity funcionario;

	// bi-directional many-to-one association to RequisicaoDetalheEntity
	@OneToMany(mappedBy = "requisicao")
	private List<RequisicaoDetalheEntity> detalhes;

	public RequisicaoCabecarioEntity() {
		super();
	}

	public RequisicaoCabecarioEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public FuncionarioEntity getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public List<RequisicaoDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<RequisicaoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public RequisicaoDetalheEntity addDetalhe(RequisicaoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setRequisicao(this);

		return detalhe;
	}

	public RequisicaoDetalheEntity removeDetalhe(RequisicaoDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setRequisicao(null);

		return detalhe;
	}

}