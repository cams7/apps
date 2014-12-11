package br.com.cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import br.com.cams7.siscom.jpa.domain.Detalhe;
import br.com.cams7.siscom.jpa.domain.pk.OrcamentoDetalhePK;

/**
 * The persistent class for the orc_det database table.
 * 
 */
@Entity
@Table(name = "orc_det")
@AttributeOverrides({
		@AttributeOverride(name = "quantidade", column = @Column(name = "qtd_orc_det")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_orc_det")),
		@AttributeOverride(name = "unitario", column = @Column(name = "vlr_unit_orc_det")) })
@NamedQuery(name = "OrcamentoDetalhe.findAll", query = "SELECT o FROM OrcamentoDetalheEntity o")
public class OrcamentoDetalheEntity extends Detalhe<OrcamentoDetalhePK> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to OrcamentoCabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_orc_cab", insertable = false, updatable = false)
	private OrcamentoCabecarioEntity orcamento;

	// bi-directional many-to-one association to ProdutoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_produto", insertable = false, updatable = false)
	private ProdutoEntity produto;

	public OrcamentoDetalheEntity() {
		super();
	}

	public OrcamentoDetalheEntity(OrcamentoDetalhePK id) {
		super(id);
	}

	public OrcamentoDetalheEntity(Long orcamentoId, String produtoId) {
		this(new OrcamentoDetalhePK(orcamentoId, produtoId));
	}

	public OrcamentoCabecarioEntity getOrcamento() {
		return this.orcamento;
	}

	public void setOrcamento(OrcamentoCabecarioEntity orcamento) {
		this.orcamento = orcamento;
	}

	public ProdutoEntity getProduto() {
		return this.produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

}