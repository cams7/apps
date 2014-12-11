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
import br.com.cams7.siscom.jpa.domain.pk.NFEDetalhePK;

/**
 * The persistent class for the nfe_det database table.
 * 
 */
@Entity
@Table(name = "nfe_det")
@AttributeOverrides({
		@AttributeOverride(name = "quantidade", column = @Column(name = "qtd_nfe_det")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_nfe_det")),
		@AttributeOverride(name = "unitario", column = @Column(name = "vlr_unit_nfe_det")) })
@NamedQuery(name = "NFEDetalhe.findAll", query = "SELECT n FROM NFEDetalheEntity n")
public class NFEDetalheEntity extends Detalhe<NFEDetalhePK> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to NFECabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "numero_nfe_cab", insertable = false, updatable = false)
	private NFECabecarioEntity nfe;

	// bi-directional many-to-one association to ProdutoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_produto", insertable = false, updatable = false)
	private ProdutoEntity produto;

	public NFEDetalheEntity() {
		super();
	}

	public NFEDetalheEntity(NFEDetalhePK id) {
		super(id);
	}

	public NFEDetalheEntity(String produtoId, Long nfeId) {
		this(new NFEDetalhePK(produtoId, nfeId));
	}

	public NFECabecarioEntity getNfe() {
		return this.nfe;
	}

	public void setNfe(NFECabecarioEntity nfe) {
		this.nfe = nfe;
	}

	public ProdutoEntity getProduto() {
		return this.produto;
	}

	public void setProduto(ProdutoEntity produto) {
		this.produto = produto;
	}

}