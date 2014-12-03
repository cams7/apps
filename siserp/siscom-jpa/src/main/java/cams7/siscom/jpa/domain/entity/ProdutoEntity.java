package cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the produto database table.
 * 
 */
@Entity
@Table(name = "produto")
@NamedQuery(name = "Produto.findAll", query = "SELECT p FROM ProdutoEntity p")
public class ProdutoEntity extends cams7.jpa.domain.BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	public static final byte ID_SIZE_MIN = 5;
	public static final byte ID_SIZE_MAX = 13;

	@Id
	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ID_SIZE_MAX)
	private String id;

	@Column(name = "critico_produto")
	private Integer qtdCritica;

	@NotEmpty
	@Size(min = 5, max = 50)
	@Column(name = "descricao_produto")
	private String descricao;

	@Column(name = "estoque_produto")
	private Integer estoqueQtd;

	@NotNull
	@Column(name = "vlr_compra_produto")
	private Double compraVlr;

	@NotNull
	@Column(name = "vlr_venda_produto")
	private Double vendaVlr;

	// bi-directional many-to-one association to NFEDetalheEntity
	@OneToMany(mappedBy = "produto")
	private List<NFEDetalheEntity> nfesDetalhes;

	// bi-directional many-to-one association to OrcamentoDetalheEntity
	@OneToMany(mappedBy = "produto")
	private List<OrcamentoDetalheEntity> orcamentosDetalhes;

	// bi-directional many-to-one association to FornecedorEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_fornecedor")
	private FornecedorEntity fornecedor;

	// bi-directional many-to-one association to UnidadeProdutoEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_unidade")
	private UnidadeProdutoEntity unidade;

	// bi-directional many-to-one association to RequisicaoDetalheEntity
	@OneToMany(mappedBy = "produto")
	private List<RequisicaoDetalheEntity> requisicoesDetalhes;

	// bi-directional many-to-one association to VendaDetatheEntity
	@OneToMany(mappedBy = "produto")
	private List<VendaDetalheEntity> vendasDetalhes;

	public ProdutoEntity() {
		super();
	}

	public ProdutoEntity(String id) {
		super(id);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getQtdCritica() {
		return this.qtdCritica;
	}

	public void setQtdCritica(Integer qtdCritica) {
		this.qtdCritica = qtdCritica;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getEstoqueQtd() {
		return this.estoqueQtd;
	}

	public void setEstoqueQtd(Integer estoqueQtd) {
		this.estoqueQtd = estoqueQtd;
	}

	public Double getCompraVlr() {
		return this.compraVlr;
	}

	public void setCompraVlr(Double compraVlr) {
		this.compraVlr = compraVlr;
	}

	public Double getVendaVlr() {
		return this.vendaVlr;
	}

	public void setVendaVlr(Double vendaVlr) {
		this.vendaVlr = vendaVlr;
	}

	public List<NFEDetalheEntity> getNfesDetalhes() {
		return this.nfesDetalhes;
	}

	public void setNfesDetalhes(List<NFEDetalheEntity> nfesDetalhes) {
		this.nfesDetalhes = nfesDetalhes;
	}

	public NFEDetalheEntity addNfeDetalhe(NFEDetalheEntity nfeDetalhe) {
		getNfesDetalhes().add(nfeDetalhe);
		nfeDetalhe.setProduto(this);

		return nfeDetalhe;
	}

	public NFEDetalheEntity removeNfeDetalhe(NFEDetalheEntity nfeDetalhe) {
		getNfesDetalhes().remove(nfeDetalhe);
		nfeDetalhe.setProduto(null);

		return nfeDetalhe;
	}

	public List<OrcamentoDetalheEntity> getOrcamentosDetalhes() {
		return this.orcamentosDetalhes;
	}

	public void setOrcamentosDetalhes(
			List<OrcamentoDetalheEntity> orcamentosDetalhes) {
		this.orcamentosDetalhes = orcamentosDetalhes;
	}

	public OrcamentoDetalheEntity addOrcamentoDetalhe(
			OrcamentoDetalheEntity orcamentoDetalhe) {
		getOrcamentosDetalhes().add(orcamentoDetalhe);
		orcamentoDetalhe.setProduto(this);

		return orcamentoDetalhe;
	}

	public OrcamentoDetalheEntity removeOrcamentoDetalhe(
			OrcamentoDetalheEntity orcamentoDetalhe) {
		getOrcamentosDetalhes().remove(orcamentoDetalhe);
		orcamentoDetalhe.setProduto(null);

		return orcamentoDetalhe;
	}

	public FornecedorEntity getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public UnidadeProdutoEntity getUnidade() {
		return this.unidade;
	}

	public void setUnidade(UnidadeProdutoEntity unidade) {
		this.unidade = unidade;
	}

	public List<RequisicaoDetalheEntity> getRequisicoesDetalhes() {
		return this.requisicoesDetalhes;
	}

	public void setRequisicoesDetalhes(
			List<RequisicaoDetalheEntity> requisicoesDetalhes) {
		this.requisicoesDetalhes = requisicoesDetalhes;
	}

	public RequisicaoDetalheEntity addRequisicaoDetalhe(
			RequisicaoDetalheEntity requisicaoDetalhe) {
		getRequisicoesDetalhes().add(requisicaoDetalhe);
		requisicaoDetalhe.setProduto(this);

		return requisicaoDetalhe;
	}

	public RequisicaoDetalheEntity removeRequisicaoDetalhe(
			RequisicaoDetalheEntity requisicaoDetalhe) {
		getRequisicoesDetalhes().remove(requisicaoDetalhe);
		requisicaoDetalhe.setProduto(null);

		return requisicaoDetalhe;
	}

	public List<VendaDetalheEntity> getVendasDetalhes() {
		return this.vendasDetalhes;
	}

	public void setVendasDetalhes(List<VendaDetalheEntity> vendasDetalhes) {
		this.vendasDetalhes = vendasDetalhes;
	}

	public VendaDetalheEntity addVendaDetalhe(VendaDetalheEntity vendaDetalhe) {
		getVendasDetalhes().add(vendaDetalhe);
		vendaDetalhe.setProduto(this);

		return vendaDetalhe;
	}

	public VendaDetalheEntity removeVendaDetalhe(VendaDetalheEntity vendaDetalhe) {
		getVendasDetalhes().remove(vendaDetalhe);
		vendaDetalhe.setProduto(null);

		return vendaDetalhe;
	}

}