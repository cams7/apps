package cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import cams7.siscom.jpa.domain.ItemCotacaoDetalhe;
import cams7.siscom.jpa.domain.pk.CotacaoDetalhePK;

/**
 * The persistent class for the cot_det database table.
 * 
 */
@Entity
@Table(name = "cot_det")
@NamedQuery(name = "CotacaoDetalhe.findAll", query = "SELECT c FROM CotacaoDetalheEntity c")
public class CotacaoDetalheEntity extends
		cams7.apps.jpa.domain.BaseEntity<CotacaoDetalhePK> {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private CotacaoDetalhePK id;

	@NotNull
	@Column(name = "qtd_cot_det")
	private Integer quantidade;

	@NotNull
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total1", nullable = false)),
			@AttributeOverride(name = "unitario", column = @Column(name = "unit1", nullable = false)) })
	private ItemCotacaoDetalhe item1;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total2")),
			@AttributeOverride(name = "unitario", column = @Column(name = "unit2")) })
	private ItemCotacaoDetalhe item2;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total3")),
			@AttributeOverride(name = "unitario", column = @Column(name = "unit3")) })
	private ItemCotacaoDetalhe item3;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total4")),
			@AttributeOverride(name = "unitario", column = @Column(name = "unit4")) })
	private ItemCotacaoDetalhe item4;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "total", column = @Column(name = "total5")),
			@AttributeOverride(name = "unitario", column = @Column(name = "unit5")) })
	private ItemCotacaoDetalhe item5;

	// bi-directional many-to-one association to CotacaoCabecarioEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cot_cab", insertable = false, updatable = false)
	private CotacaoCabecarioEntity cotacao;

	// bi-directional many-to-one association to RequisicaoDetalheEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumns({
			@JoinColumn(name = "cod_produto", referencedColumnName = "cod_produto", insertable = false, updatable = false),
			@JoinColumn(name = "cod_req_cab", referencedColumnName = "cod_req_cab", insertable = false, updatable = false) })
	private RequisicaoDetalheEntity requisicaoDetalhe;

	// bi-directional many-to-one association to PedidoDetalheEntity
	@OneToMany(mappedBy = "cotacaoDetalhe")
	private List<PedidoDetalheEntity> pedidosDetalhes;

	public CotacaoDetalheEntity() {
		super();
	}

	public CotacaoDetalheEntity(CotacaoDetalhePK id) {
		super(id);
	}

	public CotacaoDetalheEntity(Long cotacaoId, String produtoId,
			Long requisicaoId) {
		this(new CotacaoDetalhePK(cotacaoId, produtoId, requisicaoId));
	}

	public CotacaoDetalhePK getId() {
		return this.id;
	}

	public void setId(CotacaoDetalhePK id) {
		this.id = id;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public ItemCotacaoDetalhe getItem1() {
		return item1;
	}

	public void setItem1(ItemCotacaoDetalhe item1) {
		this.item1 = item1;
	}

	public ItemCotacaoDetalhe getItem2() {
		return item2;
	}

	public void setItem2(ItemCotacaoDetalhe item2) {
		this.item2 = item2;
	}

	public ItemCotacaoDetalhe getItem3() {
		return item3;
	}

	public void setItem3(ItemCotacaoDetalhe item3) {
		this.item3 = item3;
	}

	public ItemCotacaoDetalhe getItem4() {
		return item4;
	}

	public void setItem4(ItemCotacaoDetalhe item4) {
		this.item4 = item4;
	}

	public ItemCotacaoDetalhe getItem5() {
		return item5;
	}

	public void setItem5(ItemCotacaoDetalhe item5) {
		this.item5 = item5;
	}

	public CotacaoCabecarioEntity getCotacao() {
		return this.cotacao;
	}

	public void setCotacao(CotacaoCabecarioEntity cotacao) {
		this.cotacao = cotacao;
	}

	public RequisicaoDetalheEntity getRequisicaoDetalhe() {
		return this.requisicaoDetalhe;
	}

	public void setRequisicaoDetalhe(RequisicaoDetalheEntity requisicaoDetalhe) {
		this.requisicaoDetalhe = requisicaoDetalhe;
	}

	public List<PedidoDetalheEntity> getPedidosDetalhes() {
		return this.pedidosDetalhes;
	}

	public void setPedidosDetalhes(List<PedidoDetalheEntity> pedidosDetalhes) {
		this.pedidosDetalhes = pedidosDetalhes;
	}

	public PedidoDetalheEntity addPedidoDetalhe(
			PedidoDetalheEntity pedidoDetalhe) {
		getPedidosDetalhes().add(pedidoDetalhe);
		pedidoDetalhe.setCotacaoDetalhe(this);

		return pedidoDetalhe;
	}

	public PedidoDetalheEntity removePedidoDetalhe(
			PedidoDetalheEntity pedidoDetalhe) {
		getPedidosDetalhes().remove(pedidoDetalhe);
		pedidoDetalhe.setCotacaoDetalhe(null);

		return pedidoDetalhe;
	}

}