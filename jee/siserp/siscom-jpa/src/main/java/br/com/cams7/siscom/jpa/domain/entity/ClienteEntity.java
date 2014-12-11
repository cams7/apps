package br.com.cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.siscom.jpa.domain.RegistroPessoal;

/**
 * The persistent class for the cliente database table.
 * 
 */
@Entity
@Table(name = "cliente", uniqueConstraints = @UniqueConstraint(columnNames = {
		"email", "cpf_cliente", "cnpj_cliente" }))
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_cliente")),
		@AttributeOverride(name = "nome", column = @Column(name = "nome_cliente")),
		@AttributeOverride(name = "email", column = @Column(name = "email")),
		@AttributeOverride(name = "cadastro", column = @Column(name = "desde_cliente")),
		@AttributeOverride(name = "rg", column = @Column(name = "rg_cliente")),
		@AttributeOverride(name = "orgaoExpedidor", column = @Column(name = "orgao_rg_cliente")),
		@AttributeOverride(name = "cpf", column = @Column(name = "cpf_cliente")),
		@AttributeOverride(name = "cnpj", column = @Column(name = "cnpj_cliente")) })
@SequenceGenerator(name = "USUARIO_GENERATOR", sequenceName = "cliente_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "Cliente.findAll", query = "SELECT c FROM ClienteEntity c")
public class ClienteEntity extends RegistroPessoal<Integer> {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "nascimento_cliente")
	private Date nascimento;

	@Size(min = 5, max = 40)
	@Column(name = "profissao_cliente")
	private String profissao;

	@Column(name = "renda_cliente")
	private Double rendaMensal;

	@Size(min = 5, max = 50)
	@Column(name = "empresa_cliente")
	private String empresaNome;

	@Size(min = 8, max = 15)
	@Column(name = "fone_empresa")
	private String empresaFone;

	@Size(min = 5, max = 50)
	@Column(name = "referencia_cliente")
	private String referenciaNome;

	@Size(min = 8, max = 15)
	@Column(name = "fone_referencia_cliente")
	private String referenciaFone;

	@NotNull
	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo_cliente", length = 1, columnDefinition = "char(1)")
	private TipoCliente tipo;

	// bi-directional many-to-one association to EnderecoEntity
	@OneToMany(mappedBy = "cliente")
	private List<ClienteEnderecoEntity> enderecos;

	// bi-directional many-to-one association to TelefoneEntity
	@OneToMany(mappedBy = "cliente")
	private List<ClienteTelefoneEntity> telefones;

	// bi-directional many-to-one association to CobrancaEntity
	@OneToMany(mappedBy = "cliente")
	private List<CobrancaEntity> cobrancas;

	// bi-directional many-to-one association to OrcamentoCabecarioEntity
	@OneToMany(mappedBy = "cliente")
	private List<OrcamentoCabecarioEntity> orcamentos;

	// bi-directional many-to-one association to RecebimentoEntity
	@OneToMany(mappedBy = "cliente")
	private List<RecebimentoEntity> recebimentos;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@OneToMany(mappedBy = "cliente")
	private List<VendaCabecarioEntity> vendas;

	public ClienteEntity() {
		super();
	}

	public ClienteEntity(Integer id) {
		super(id);
	}

	public Date getNascimento() {
		return this.nascimento;
	}

	public void setNascimento(Date nascimento) {
		this.nascimento = nascimento;
	}

	public String getProfissao() {
		return this.profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public Double getRendaMensal() {
		return this.rendaMensal;
	}

	public void setRendaMensal(Double rendaMensal) {
		this.rendaMensal = rendaMensal;
	}

	public String getEmpresaNome() {
		return this.empresaNome;
	}

	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}

	public String getEmpresaFone() {
		return this.empresaFone;
	}

	public void setEmpresaFone(String empresaFone) {
		this.empresaFone = empresaFone;
	}

	public String getReferenciaNome() {
		return this.referenciaNome;
	}

	public void setReferenciaNome(String referenciaNome) {
		this.referenciaNome = referenciaNome;
	}

	public String getReferenciaFone() {
		return this.referenciaFone;
	}

	public void setReferenciaFone(String referenciaFone) {
		this.referenciaFone = referenciaFone;
	}

	public TipoCliente getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo;
	}

	public List<ClienteEnderecoEntity> getEnderecos() {
		return this.enderecos;
	}

	public void setEnderecos(List<ClienteEnderecoEntity> enderecos) {
		this.enderecos = enderecos;
	}

	public ClienteEnderecoEntity addEndereco(ClienteEnderecoEntity endereco) {
		getEnderecos().add(endereco);
		endereco.setCliente(this);

		return endereco;
	}

	public ClienteEnderecoEntity removeEndereco(ClienteEnderecoEntity endereco) {
		getEnderecos().remove(endereco);
		endereco.setCliente(null);

		return endereco;
	}

	public List<ClienteTelefoneEntity> getTelefones() {
		return this.telefones;
	}

	public void setTelefones(List<ClienteTelefoneEntity> telefones) {
		this.telefones = telefones;
	}

	public ClienteTelefoneEntity addTelefone(ClienteTelefoneEntity telefone) {
		getTelefones().add(telefone);
		telefone.setCliente(this);

		return telefone;
	}

	public ClienteTelefoneEntity removeTelefone(ClienteTelefoneEntity telefone) {
		getTelefones().remove(telefone);
		telefone.setCliente(null);

		return telefone;
	}

	public List<CobrancaEntity> getCobrancas() {
		return this.cobrancas;
	}

	public void setCobrancas(List<CobrancaEntity> cobrancas) {
		this.cobrancas = cobrancas;
	}

	public CobrancaEntity addCobranca(CobrancaEntity cobranca) {
		getCobrancas().add(cobranca);
		cobranca.setCliente(this);

		return cobranca;
	}

	public CobrancaEntity removeCobranca(CobrancaEntity cobranca) {
		getCobrancas().remove(cobranca);
		cobranca.setCliente(null);

		return cobranca;
	}

	public List<OrcamentoCabecarioEntity> getOrcamentos() {
		return this.orcamentos;
	}

	public void setOrcamentos(List<OrcamentoCabecarioEntity> orcamentos) {
		this.orcamentos = orcamentos;
	}

	public OrcamentoCabecarioEntity addOrcamento(
			OrcamentoCabecarioEntity orcamento) {
		getOrcamentos().add(orcamento);
		orcamento.setCliente(this);

		return orcamento;
	}

	public OrcamentoCabecarioEntity removeOrcamento(
			OrcamentoCabecarioEntity orcamento) {
		getOrcamentos().remove(orcamento);
		orcamento.setCliente(null);

		return orcamento;
	}

	public List<RecebimentoEntity> getRecebimentos() {
		return this.recebimentos;
	}

	public void setRecebimentos(List<RecebimentoEntity> recebimentos) {
		this.recebimentos = recebimentos;
	}

	public RecebimentoEntity addRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().add(recebimento);
		recebimento.setCliente(this);

		return recebimento;
	}

	public RecebimentoEntity removeRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().remove(recebimento);
		recebimento.setCliente(null);

		return recebimento;
	}

	public List<VendaCabecarioEntity> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<VendaCabecarioEntity> vendas) {
		this.vendas = vendas;
	}

	public VendaCabecarioEntity addVenda(VendaCabecarioEntity venda) {
		getVendas().add(venda);
		venda.setCliente(this);

		return venda;
	}

	public VendaCabecarioEntity removeVenda(VendaCabecarioEntity venda) {
		getVendas().remove(venda);
		venda.setCliente(null);

		return venda;
	}

	public enum TipoCliente {
		EMPRESA, PESSOA_FISICA
	}

}