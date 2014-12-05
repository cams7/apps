package cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import cams7.siscom.jpa.domain.Detalhe;
import cams7.siscom.jpa.domain.pk.PedidoDetalhePK;

/**
 * The persistent class for the pedido_det database table.
 * 
 */
@Entity
@Table(name = "pedido_det")
@AttributeOverrides({
		@AttributeOverride(name = "quantidade", column = @Column(name = "qtde_pedido_det")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_pedido_det")),
		@AttributeOverride(name = "unitario", column = @Column(name = "vlr_unit_pedido_det")) })
@NamedQuery(name = "PedidoDetalhe.findAll", query = "SELECT p FROM PedidoDetalheEntity p")
public class PedidoDetalheEntity extends Detalhe<PedidoDetalhePK> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to CotacaoDetalheEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "cod_cot_cab", referencedColumnName = "cod_cot_cab", insertable = false, updatable = false),
			@JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto", insertable = false, updatable = false),
			@JoinColumn(name = "cod_req_cab", referencedColumnName = "cod_req_cab", insertable = false, updatable = false) })
	private CotacaoDetalheEntity cotacaoDetalhe;

	// bi-directional many-to-one association to PedidoCabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_pedido_cab", insertable = false, updatable = false)
	private PedidoCabecarioEntity pedido;

	public PedidoDetalheEntity() {
		super();
	}

	public PedidoDetalheEntity(PedidoDetalhePK id) {
		super(id);
	}

	public PedidoDetalheEntity(Long requisicaoId, String produtoId,
			Long cotacaoId, Long pedidoId) {
		this(new PedidoDetalhePK(requisicaoId, produtoId, cotacaoId, pedidoId));
	}

	public CotacaoDetalheEntity getCotacaoDetalhe() {
		return this.cotacaoDetalhe;
	}

	public void setCotacaoDetalhe(CotacaoDetalheEntity cotacaoDetalhe) {
		this.cotacaoDetalhe = cotacaoDetalhe;
	}

	public PedidoCabecarioEntity getPedido() {
		return this.pedido;
	}

	public void setPedido(PedidoCabecarioEntity pedido) {
		this.pedido = pedido;
	}

}