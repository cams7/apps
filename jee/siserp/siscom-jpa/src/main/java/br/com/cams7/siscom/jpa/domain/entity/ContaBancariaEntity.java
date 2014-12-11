package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

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

/**
 * The persistent class for the conta_bancaria database table.
 * 
 */
@Entity
@Table(name = "conta_bancaria")
@NamedQuery(name = "ContaBancaria.findAll", query = "SELECT c FROM ContaBancariaEntity c")
public class ContaBancariaEntity extends br.com.cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "conta_bancaria_seq", sequenceName = "conta_bancaria_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "conta_bancaria_seq")
	@Column(name = "id_conta_bancaria")
	private Long id;

	@NotNull
	@Column(name = "agencia_banco")
	private Integer agenciaNumero;

	@NotNull
	@Column(name = "conta_banco")
	private Integer contaNumero;

	@Size(min = 8, max = 15)
	@Column(name = "fone_banco")
	private String bancoFone;

	@Size(min = 5, max = 30)
	@Column(name = "gerente_banco")
	private String bancoGerente;

	// bi-directional many-to-one association to BancoEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_banco")
	private BancoEntity banco;

	// bi-directional many-to-one association to PagamentoEntity
	@OneToMany(mappedBy = "contaBancaria")
	private List<PagamentoEntity> pagamentos;

	// bi-directional many-to-one association to RecebimentoEntity
	@OneToMany(mappedBy = "contaBancaria")
	private List<RecebimentoEntity> recebimentos;

	public ContaBancariaEntity() {
		super();
	}

	public ContaBancariaEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getAgenciaNumero() {
		return this.agenciaNumero;
	}

	public void setAgenciaNumero(Integer agenciaNumero) {
		this.agenciaNumero = agenciaNumero;
	}

	public Integer getContaNumero() {
		return this.contaNumero;
	}

	public void setContaNumero(Integer contaNumero) {
		this.contaNumero = contaNumero;
	}

	public String getBancoFone() {
		return this.bancoFone;
	}

	public void setBancoFone(String bancoFone) {
		this.bancoFone = bancoFone;
	}

	public String getBancoGerente() {
		return this.bancoGerente;
	}

	public void setBancoGerente(String bancoGerente) {
		this.bancoGerente = bancoGerente;
	}

	public BancoEntity getBanco() {
		return this.banco;
	}

	public void setBanco(BancoEntity banco) {
		this.banco = banco;
	}

	public List<PagamentoEntity> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<PagamentoEntity> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public PagamentoEntity addPagamento(PagamentoEntity pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setContaBancaria(this);

		return pagamento;
	}

	public PagamentoEntity removePagamento(PagamentoEntity pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setContaBancaria(null);

		return pagamento;
	}

	public List<RecebimentoEntity> getRecebimentos() {
		return this.recebimentos;
	}

	public void setRecebimentos(List<RecebimentoEntity> recebimentos) {
		this.recebimentos = recebimentos;
	}

	public RecebimentoEntity addRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().add(recebimento);
		recebimento.setContaBancaria(this);

		return recebimento;
	}

	public RecebimentoEntity removeRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().remove(recebimento);
		recebimento.setContaBancaria(null);

		return recebimento;
	}

}