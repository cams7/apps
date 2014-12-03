package cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cams7.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.Usuario;

/**
 * The persistent class for the funcionario database table.
 * 
 */
@Entity
@Table(name = "funcionario")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_funcionario")),
		@AttributeOverride(name = "nome", column = @Column(name = "nome_funcionario")) })
@SequenceGenerator(name = "USUARIO_GENERATOR", sequenceName = "funcionario_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "Funcionario.findAll", query = "SELECT f FROM FuncionarioEntity f")
public class FuncionarioEntity extends Usuario<Integer> {
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "login_funcionario")
	private String login;

	@NotEmpty
	@Size(min = 5, max = 30)
	@Column(name = "senha_funcionario")
	private String senha;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "admissao_funcionario")
	private Date admissao;

	@NotEmpty
	@Size(min = 5, max = 30)
	@Column(name = "funcao_funcionario")
	private String funcao;

	@NotNull
	@Enumerated(EnumType.STRING)
	@Column(name = "acesso_total", length = 1, columnDefinition = "char(1)")
	private AcessoTotal acessoTotal;

	// bi-directional many-to-one association to DepartamentoEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_departamento")
	private DepartamentoEntity departamento;

	// bi-directional many-to-one association to OrcamentoCabecarioEntity
	@OneToMany(mappedBy = "funcionario")
	private List<OrcamentoCabecarioEntity> orcamentos;

	// bi-directional many-to-one association to RequisicaoCabecarioEntity
	@OneToMany(mappedBy = "funcionario")
	private List<RequisicaoCabecarioEntity> requisicoes;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@OneToMany(mappedBy = "funcionario")
	private List<VendaCabecarioEntity> vendas;

	public FuncionarioEntity() {
		super();
	}

	public FuncionarioEntity(Integer id) {
		super(id);
	}

	public String getLogin() {
		return this.login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return this.senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Date getAdmissao() {
		return this.admissao;
	}

	public void setAdmissao(Date admissao) {
		this.admissao = admissao;
	}

	public String getFuncao() {
		return this.funcao;
	}

	public void setFuncao(String funcao) {
		this.funcao = funcao;
	}

	public AcessoTotal getAcessoTotal() {
		return this.acessoTotal;
	}

	public void setAcessoTotal(AcessoTotal acessoTotal) {
		this.acessoTotal = acessoTotal;
	}

	public DepartamentoEntity getDepartamento() {
		return this.departamento;
	}

	public void setDepartamento(DepartamentoEntity departamento) {
		this.departamento = departamento;
	}

	public List<OrcamentoCabecarioEntity> getOrcamentos() {
		return this.orcamentos;
	}

	public void setOrcamentos(List<OrcamentoCabecarioEntity> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public OrcamentoCabecarioEntity addOrcamento(
			OrcamentoCabecarioEntity orcamento) {
		getOrcamentos().add(orcamento);
		orcamento.setFuncionario(this);

		return orcamento;
	}

	public OrcamentoCabecarioEntity removeOrcamento(
			OrcamentoCabecarioEntity orcamento) {
		getOrcamentos().remove(orcamento);
		orcamento.setFuncionario(null);

		return orcamento;
	}

	public List<RequisicaoCabecarioEntity> getRequisicoes() {
		return this.requisicoes;
	}

	public void setRequisicoes(List<RequisicaoCabecarioEntity> requisicoes) {
		this.requisicoes = requisicoes;
	}

	public RequisicaoCabecarioEntity addRequisicao(
			RequisicaoCabecarioEntity requisicao) {
		getRequisicoes().add(requisicao);
		requisicao.setFuncionario(this);

		return requisicao;
	}

	public RequisicaoCabecarioEntity removeRequisicao(
			RequisicaoCabecarioEntity requisicao) {
		getRequisicoes().remove(requisicao);
		requisicao.setFuncionario(null);

		return requisicao;
	}

	public List<VendaCabecarioEntity> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<VendaCabecarioEntity> vendas) {
		this.vendas = vendas;
	}

	public VendaCabecarioEntity addVenda(VendaCabecarioEntity venda) {
		getVendas().add(venda);
		venda.setFuncionario(this);

		return venda;
	}

	public VendaCabecarioEntity removeVenda(VendaCabecarioEntity venda) {
		getVendas().remove(venda);
		venda.setFuncionario(null);

		return venda;
	}

	public enum AcessoTotal {
		S("Sim"), N("Não");

		private String desc;

		private AcessoTotal(String desc) {
			this.desc = desc;
		}

		public String getDesc() {
			return desc;
		}

	}

}