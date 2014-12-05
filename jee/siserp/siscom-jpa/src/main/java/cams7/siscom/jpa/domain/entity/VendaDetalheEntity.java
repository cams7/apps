package cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cams7.siscom.jpa.domain.Detalhe;
import cams7.siscom.jpa.domain.pk.VendaDetalhePK;

/**
 * The persistent class for the venda_det database table.
 * 
 */
@Entity
@Table(name = "venda_det")
@AttributeOverrides({
		@AttributeOverride(name = "quantidade", column = @Column(name = "qtd_venda_det")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_venda_det")),
		@AttributeOverride(name = "unitario", column = @Column(name = "vlr_unit_venda_det")) })
@NamedQuery(name = "VendaDetalhe.findAll", query = "SELECT v FROM VendaDetalheEntity v")
public class VendaDetalheEntity extends Detalhe<VendaDetalhePK> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to ProdutoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_produto", insertable = false, updatable = false)
	private ProdutoEntity produto;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_venda_cab", insertable = false, updatable = false)
	private VendaCabecarioEntity venda;

	public VendaDetalheEntity() {
		super();
	}

	public VendaDetalheEntity(VendaDetalhePK id) {
		super(id);
	}

	public VendaDetalheEntity(String produtoId, Long vendaId) {
		super(new VendaDetalhePK(produtoId, vendaId));
	}

	public ProdutoEntity getProduto() {
		return this.produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

	public VendaCabecarioEntity getVenda() {
		return this.venda;
	}

	public void setVenda(VendaCabecarioEntity venda) {
		this.venda = venda;
	}

}