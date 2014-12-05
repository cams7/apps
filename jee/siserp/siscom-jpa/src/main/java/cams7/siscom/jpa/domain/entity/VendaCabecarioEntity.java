package cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
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

import cams7.siscom.jpa.domain.Cabecario;

/**
 * The persistent class for the venda_cab database table.
 * 
 */
@Entity
@Table(name = "venda_cab")
@AttributeOverrides({
		@AttributeOverride(name = "data", column = @Column(name = "data_venda_cab")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_venda_cab")) })
@NamedQuery(name = "VendaCabecario.findAll", query = "SELECT v FROM VendaCabecarioEntity v")
public class VendaCabecarioEntity extends Cabecario<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "venda_cab_seq", sequenceName = "venda_cab_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "venda_cab_seq")
	@Column(name = "cod_venda_cab")
	private Long id;

	@Column(name = "desconto_venda_cab")
	private Double desconto;

	@Column(name = "num_nf_venda_cab")
	private Long numeroNF;

	@NotNull
	@Column(name = "num_parcelas_venda_cab")
	private Integer qtdParcelas;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "pri_venci_venda_cab")
	private Date vencimento;

	@NotNull
	@Column(name = "total_venda_cab")
	private Double total;

	// bi-directional many-to-one association to CartaoCreditoEntity
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_cartao_credito")
	private CartaoCreditoEntity cartaoCredito;

	// bi-directional many-to-one association to CfopEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cfop")
	private CfopEntity cfop;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	// bi-directional many-to-one association to FuncionarioEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_funcionario")
	private FuncionarioEntity funcionario;

	// bi-directional many-to-one association to TipoPgtoEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_pgto")
	private TipoPgtoEntity tipoPgto;

	// bi-directional many-to-one association to VendaDetatheEntity
	@OneToMany(mappedBy = "venda")
	private List<VendaDetalheEntity> detalhes;

	public VendaCabecarioEntity() {
		super();
	}

	public VendaCabecarioEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getDesconto() {
		return this.desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Long getNumeroNF() {
		return this.numeroNF;
	}

	public void setNumeroNF(Long numeroNF) {
		this.numeroNF = numeroNF;
	}

	public Integer getQtdParcelas() {
		return this.qtdParcelas;
	}

	public void setQtdParcelas(Integer qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public Date getVencimento() {
		return this.vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public CartaoCreditoEntity getCartaoCredito() {
		return this.cartaoCredito;
	}

	public void setCartaoCredito(CartaoCreditoEntity cartaoCredito) {
		this.cartaoCredito = cartaoCredito;
	}

	public CfopEntity getCfop() {
		return this.cfop;
	}

	public void setCfop(CfopEntity cfop) {
		this.cfop = cfop;
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

	public FuncionarioEntity getFuncionario() {
		return this.funcionario;
	}

	public void setFuncionario(FuncionarioEntity funcionario) {
		this.funcionario = funcionario;
	}

	public TipoPgtoEntity getTipoPgto() {
		return this.tipoPgto;
	}

	public void setTipoPgto(TipoPgtoEntity tipoPgto) {
		this.tipoPgto = tipoPgto;
	}

	public List<VendaDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<VendaDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public VendaDetalheEntity addDetalhe(VendaDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setVenda(this);

		return detalhe;
	}

	public VendaDetalheEntity removeDetalhe(VendaDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setVenda(null);

		return detalhe;
	}

}