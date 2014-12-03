package cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cams7.apps.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.Endereco;
import cams7.siscom.jpa.domain.RegistroPessoal;

/**
 * The persistent class for the fornecedor database table.
 * 
 */
@Entity
@Table(name = "fornecedor", uniqueConstraints = @UniqueConstraint(columnNames = {
		"email_fornecedor", "cpf_fornecedor", "cnpj_fornecedor" }))
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_fornecedor")),
		@AttributeOverride(name = "nome", column = @Column(name = "nome_fornecedor")),
		@AttributeOverride(name = "email", column = @Column(name = "email_fornecedor")),
		@AttributeOverride(name = "rg", column = @Column(name = "rg_fornecedor")),
		@AttributeOverride(name = "orgaoExpedidor", column = @Column(name = "orgao_rg_fornecedor")),
		@AttributeOverride(name = "cpf", column = @Column(name = "cpf_fornecedor")),
		@AttributeOverride(name = "cnpj", column = @Column(name = "cnpj_fornecedor")) })
@SequenceGenerator(name = "USUARIO_GENERATOR", sequenceName = "fornecedor_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "Fornecedor.findAll", query = "SELECT f FROM FornecedorEntity f")
public class FornecedorEntity extends RegistroPessoal<Integer> {
	private static final long serialVersionUID = 1L;

	@Size(min = 8, max = 15)
	@Column(name = "fone_fornecedor")
	private String fone;

	@Size(min = 8, max = 15)
	@Column(name = "celular_fornecedor")
	private String celular;

	@Size(min = 8, max = 15)
	@Column(name = "fax_fornecedor")
	private String fax;

	@Size(min = 10, max = 100)
	@Column(name = "site_fornecedor")
	private String site;

	@Embedded
	@AttributeOverrides({
			@AttributeOverride(name = "bairro", column = @Column(name = "bairro_fornecedor", length = Endereco.BAIRRO_LENGTH)),
			@AttributeOverride(name = "cep", column = @Column(name = "cep_fornecedor", length = Endereco.CEP_LENGTH)),
			@AttributeOverride(name = "cidade", column = @Column(name = "cidade_fornecedor", length = Endereco.CIDADE_LENGTH, nullable = false)),
			@AttributeOverride(name = "logradouro", column = @Column(name = "endereco_fornecedor", length = Endereco.LOGRADOURO_LENGTH, nullable = false)),
			@AttributeOverride(name = "uf", column = @Column(name = "uf_fornecedor", length = Endereco.UF_LENGTH, nullable = false, columnDefinition = "char(2)")) })
	private Endereco endereco;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_fornecedor", length = 1, columnDefinition = "char(1)")
	private TipoFornecedor tipo;

	@Size(min = 5, max = 50)
	@Column(name = "contato_fornecedor")
	private String contatoNome;

	// bi-directional many-to-one association to NFECabecarioEntity
	@OneToMany(mappedBy = "fornecedor")
	private List<NFECabecarioEntity> nfes;

	// bi-directional many-to-one association to PagamentoEntity
	@OneToMany(mappedBy = "fornecedor")
	private List<PagamentoEntity> pagamentos;

	// bi-directional many-to-one association to PedidoCabecarioEntity
	@OneToMany(mappedBy = "fornecedor")
	private List<PedidoCabecarioEntity> pedidos;

	// bi-directional many-to-one association to ProdutoEntity
	@OneToMany(mappedBy = "fornecedor")
	private List<ProdutoEntity> produtos;

	public FornecedorEntity() {
		super();
	}

	public FornecedorEntity(Integer id) {
		super(id);
	}

	public String getFone() {
		return this.fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCelular() {
		return this.celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getFax() {
		return this.fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public TipoFornecedor getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoFornecedor tipo) {
		this.tipo = tipo;
	}

	public String getContatoNome() {
		return this.contatoNome;
	}

	public void setContatoNome(String contatoNome) {
		this.contatoNome = contatoNome;
	}

	public List<NFECabecarioEntity> getNfes() {
		return this.nfes;
	}

	public void setNfes(List<NFECabecarioEntity> nfes) {
		this.nfes = nfes;
	}

	public NFECabecarioEntity addNfe(NFECabecarioEntity nfe) {
		getNfes().add(nfe);
		nfe.setFornecedor(this);

		return nfe;
	}

	public NFECabecarioEntity removeNfe(NFECabecarioEntity nfe) {
		getNfes().remove(nfe);
		nfe.setFornecedor(null);

		return nfe;
	}

	public List<PagamentoEntity> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<PagamentoEntity> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public PagamentoEntity addPagamento(PagamentoEntity pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setFornecedor(this);

		return pagamento;
	}

	public PagamentoEntity removePagamento(PagamentoEntity pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setFornecedor(null);

		return pagamento;
	}

	public List<PedidoCabecarioEntity> getPedidos() {
		return this.pedidos;
	}

	public void setPedidos(List<PedidoCabecarioEntity> pedidos) {
		this.pedidos = pedidos;
	}

	public PedidoCabecarioEntity addPedido(PedidoCabecarioEntity pedido) {
		getPedidos().add(pedido);
		pedido.setFornecedor(this);

		return pedido;
	}

	public PedidoCabecarioEntity removePedido(PedidoCabecarioEntity pedido) {
		getPedidos().remove(pedido);
		pedido.setFornecedor(null);

		return pedido;
	}

	public List<ProdutoEntity> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}

	public ProdutoEntity addProduto(ProdutoEntity produto) {
		getProdutos().add(produto);
		produto.setFornecedor(this);

		return produto;
	}

	public ProdutoEntity removeProduto(ProdutoEntity produto) {
		getProdutos().remove(produto);
		produto.setFornecedor(null);

		return produto;
	}

	public enum TipoFornecedor {
		VAREJO, ATACADO;
	}

}