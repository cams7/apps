package br.com.cams7.siscom.jpa.domain.entity;

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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cams7.siscom.jpa.domain.Cabecario;

/**
 * The persistent class for the pedido_cab database table.
 * 
 */
@Entity
@Table(name = "pedido_cab")
@AttributeOverrides({
		@AttributeOverride(name = "data", column = @Column(name = "data_pedido")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_pedido_cab")) })
@NamedQuery(name = "PedidoCabecario.findAll", query = "SELECT p FROM PedidoCabecarioEntity p")
public class PedidoCabecarioEntity extends Cabecario<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "pedido_cab_seq", sequenceName = "pedido_cab_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pedido_cab_seq")
	@Column(name = "cod_pedido_cab")
	private Long id;

	@Column(name = "desconto_pedido_cab")
	private Double desconto;

	@NotEmpty
	@Size(min = 10, max = 100)
	@Column(name = "endereco_cobranca")
	private String cobrancaEndereco;

	@NotEmpty
	@Size(min = 10, max = 100)
	@Column(name = "endereco_entrega")
	private String entregaEndereco;

	@NotNull
	@Column(name = "total_pedodo_cab")
	private Double total;

	// bi-directional many-to-one association to CotacaoCabecarioEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cot_cab")
	private CotacaoCabecarioEntity cotacao;

	// bi-directional many-to-one association to FornecedorEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_fornecedor")
	private FornecedorEntity fornecedor;

	// bi-directional many-to-one association to PedidoDetalheEntity
	@OneToMany(mappedBy = "pedido")
	private List<PedidoDetalheEntity> detalhes;

	public PedidoCabecarioEntity() {
		super();
	}

	public PedidoCabecarioEntity(Long id) {
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

	public String getCobrancaEndereco() {
		return this.cobrancaEndereco;
	}

	public void setCobrancaEndereco(String cobrancaEndereco) {
		this.cobrancaEndereco = cobrancaEndereco;
	}

	public String getEntregaEndereco() {
		return this.entregaEndereco;
	}

	public void setEntregaEndereco(String entregaEndereco) {
		this.entregaEndereco = entregaEndereco;
	}

	public Double getTotal() {
		return this.total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public CotacaoCabecarioEntity getCotacao() {
		return this.cotacao;
	}

	public void setCotacao(CotacaoCabecarioEntity cotacao) {
		this.cotacao = cotacao;
	}

	public FornecedorEntity getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<PedidoDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<PedidoDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public PedidoDetalheEntity addDetalhe(PedidoDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setPedido(this);

		return detalhe;
	}

	public PedidoDetalheEntity removeDetalhe(PedidoDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setPedido(null);

		return detalhe;
	}

}