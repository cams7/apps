package cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import cams7.siscom.jpa.domain.ItemCotacaoCabecario;

/**
 * The persistent class for the cot_cab database table.
 * 
 */
@Entity
@Table(name = "cot_cab")
@NamedQuery(name = "CotacaoCabecario.findAll", query = "SELECT c FROM CotacaoCabecarioEntity c")
public class CotacaoCabecarioEntity extends cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cot_cab_seq", sequenceName = "cot_cab_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cot_cab_seq")
	@Column(name = "cod_cot_cab")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "data_cot_cab")
	private Date data;

	@NotNull
	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "condicoes", column = @Column(name = "condicoes1", length = ItemCotacaoCabecario.CONDICOES_SIZE_MAX, nullable = false)),
			@AttributeOverride(name = "desconto", column = @Column(name = "desconto1")),
			@AttributeOverride(name = "entrega", column = @Column(name = "entrega1", length = ItemCotacaoCabecario.ENTREGA_SIZE_MAX, nullable = false)),
			@AttributeOverride(name = "fornecedorId", column = @Column(name = "forn1", nullable = false)),
			@AttributeOverride(name = "total", column = @Column(name = "total1", nullable = false)) })
	private ItemCotacaoCabecario item1;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "condicoes", column = @Column(name = "condicoes2", length = ItemCotacaoCabecario.CONDICOES_SIZE_MAX)),
			@AttributeOverride(name = "desconto", column = @Column(name = "desconto2")),
			@AttributeOverride(name = "entrega", column = @Column(name = "entrega2", length = ItemCotacaoCabecario.ENTREGA_SIZE_MAX)),
			@AttributeOverride(name = "fornecedorId", column = @Column(name = "forn2")),
			@AttributeOverride(name = "total", column = @Column(name = "total2")) })
	private ItemCotacaoCabecario item2;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "condicoes", column = @Column(name = "condicoes3", length = ItemCotacaoCabecario.CONDICOES_SIZE_MAX)),
			@AttributeOverride(name = "desconto", column = @Column(name = "desconto3")),
			@AttributeOverride(name = "entrega", column = @Column(name = "entrega3", length = ItemCotacaoCabecario.ENTREGA_SIZE_MAX)),
			@AttributeOverride(name = "fornecedorId", column = @Column(name = "forn3")),
			@AttributeOverride(name = "total", column = @Column(name = "total3")) })
	private ItemCotacaoCabecario item3;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "condicoes", column = @Column(name = "condicoes4", length = ItemCotacaoCabecario.CONDICOES_SIZE_MAX)),
			@AttributeOverride(name = "desconto", column = @Column(name = "desconto4")),
			@AttributeOverride(name = "entrega", column = @Column(name = "entrega4", length = ItemCotacaoCabecario.ENTREGA_SIZE_MAX)),
			@AttributeOverride(name = "fornecedorId", column = @Column(name = "forn4")),
			@AttributeOverride(name = "total", column = @Column(name = "total4")) })
	private ItemCotacaoCabecario item4;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "condicoes", column = @Column(name = "condicoes5", length = ItemCotacaoCabecario.CONDICOES_SIZE_MAX)),
			@AttributeOverride(name = "desconto", column = @Column(name = "desconto5")),
			@AttributeOverride(name = "entrega", column = @Column(name = "entrega5", length = ItemCotacaoCabecario.ENTREGA_SIZE_MAX)),
			@AttributeOverride(name = "fornecedorId", column = @Column(name = "forn5")),
			@AttributeOverride(name = "total", column = @Column(name = "total5")) })
	private ItemCotacaoCabecario item5;

	// bi-directional many-to-one association to CotacaoDetalheEntity
	@OneToMany(mappedBy = "cotacao")
	private List<CotacaoDetalheEntity> detalhes;

	// bi-directional many-to-one association to PedidoCabecarioEntity
	@OneToMany(mappedBy = "cotacao")
	private List<PedidoCabecarioEntity> pedidos;

	public CotacaoCabecarioEntity() {
		super();
	}

	public CotacaoCabecarioEntity(Long id) {
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

	public ItemCotacaoCabecario getItem1() {
		return item1;
	}

	public void setItem1(ItemCotacaoCabecario item1) {
		this.item1 = item1;
	}

	public ItemCotacaoCabecario getItem2() {
		return item2;
	}

	public void setItem2(ItemCotacaoCabecario item2) {
		this.item2 = item2;
	}

	public ItemCotacaoCabecario getItem3() {
		return item3;
	}

	public void setItem3(ItemCotacaoCabecario item3) {
		this.item3 = item3;
	}

	public ItemCotacaoCabecario getItem4() {
		return item4;
	}

	public void setItem4(ItemCotacaoCabecario item4) {
		this.item4 = item4;
	}

	public ItemCotacaoCabecario getItem5() {
		return item5;
	}

	public void setItem5(ItemCotacaoCabecario item5) {
		this.item5 = item5;
	}

	public List<CotacaoDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<CotacaoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public CotacaoDetalheEntity addDetalhe(CotacaoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setCotacao(this);

		return detalhe;
	}

	public CotacaoDetalheEntity removeDetalhe(CotacaoDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setCotacao(null);

		return detalhe;
	}

	public List<PedidoCabecarioEntity> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<PedidoCabecarioEntity> pedidos) {
		this.pedidos = pedidos;
	}

	public PedidoCabecarioEntity addPedido(PedidoCabecarioEntity pedido) {
		getPedidos().add(pedido);
		pedido.setCotacao(this);

		return pedido;
	}

	public PedidoCabecarioEntity removePedido(PedidoCabecarioEntity pedido) {
		getPedidos().remove(pedido);
		pedido.setCotacao(null);

		return pedido;
	}

}