package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.cams7.siscom.jpa.domain.Detalhe;
import br.com.cams7.siscom.jpa.domain.pk.RequisicaoDetalhePK;

/**
 * The persistent class for the req_det database table.
 * 
 */
@Entity
@Table(name = "req_det")
@AttributeOverrides({
		@AttributeOverride(name = "quantidade", column = @Column(name = "qtde_req_det")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_req_det")),
		@AttributeOverride(name = "unitario", column = @Column(name = "vlr_unit_req_det")) })
@NamedQuery(name = "RequisicaoDetalhe.findAll", query = "SELECT r FROM RequisicaoDetalheEntity r")
public class RequisicaoDetalheEntity extends Detalhe<RequisicaoDetalhePK> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to CotacaoDetalheEntity
	@OneToMany(mappedBy = "requisicaoDetalhe")
	private List<CotacaoDetalheEntity> cotacoesDetalhes;

	// bi-directional many-to-one association to ProdutoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_produto", insertable = false, updatable = false)
	private ProdutoEntity produto;

	// bi-directional many-to-one association to RequisicaoCabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_req_cab", insertable = false, updatable = false)
	private RequisicaoCabecarioEntity requisicao;

	public RequisicaoDetalheEntity() {
		super();
	}

	public RequisicaoDetalheEntity(RequisicaoDetalhePK id) {
		super(id);
	}

	public RequisicaoDetalheEntity(Long requisicaoId, String produtoId) {
		super(new RequisicaoDetalhePK(requisicaoId, produtoId));
	}

	public List<CotacaoDetalheEntity> getCotacoesDetalhes() {
		return this.cotacoesDetalhes;
	}

	public void setCotacoesDetalhes(List<CotacaoDetalheEntity> cotacoesDetalhes) {
		this.cotacoesDetalhes = cotacoesDetalhes;
	}

	public CotacaoDetalheEntity addCotacaoDetalhe(
			CotacaoDetalheEntity cotacaoDetalhe) {
		getCotacoesDetalhes().add(cotacaoDetalhe);
		cotacaoDetalhe.setRequisicaoDetalhe(this);

		return cotacaoDetalhe;
	}

	public CotacaoDetalheEntity removeCotacaoDetalhe(
			CotacaoDetalheEntity cotacaoDetalhe) {
		getCotacoesDetalhes().remove(cotacaoDetalhe);
		cotacaoDetalhe.setRequisicaoDetalhe(null);

		return cotacaoDetalhe;
	}

	public ProdutoEntity getProduto() {
		return this.produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public RequisicaoCabecarioEntity getRequisicao() {
		return this.requisicao;
	}

	public void setRequisicao(RequisicaoCabecarioEntity requisicao) {
		this.requisicao = requisicao;
	}

}