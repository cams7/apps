package br.com.cams7.siscom.jpa.domain.entity;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.siscom.jpa.domain.PgtoRbto;

/**
 * The persistent class for the pagamento database table.
 * 
 */
@Entity
@Table(name = "pagamento")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_pagamento")),
		@AttributeOverride(name = "data", column = @Column(name = "data_pagamento")),
		@AttributeOverride(name = "emissao", column = @Column(name = "emissao_pagamento")),
		@AttributeOverride(name = "lancamentoData", column = @Column(name = "lancamento_pagamento")),
		@AttributeOverride(name = "documentoNumero", column = @Column(name = "num_doc_pagamento")),
		@AttributeOverride(name = "vencimento", column = @Column(name = "vencimento_pagamento")),
		@AttributeOverride(name = "desconto", column = @Column(name = "vlr_desconto_pagamento")),
		@AttributeOverride(name = "juro", column = @Column(name = "vlr_juro_pagamento")),
		@AttributeOverride(name = "multa", column = @Column(name = "vlr_multa_pagamento")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_pago_pagamento")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_pagamento")) })
@SequenceGenerator(name = "PGTO_RBTO_GENERATOR", sequenceName = "pagamento_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "Pagamento.findAll", query = "SELECT p FROM PagamentoEntity p")
public class PagamentoEntity extends PgtoRbto<Long> {
	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	@Column(name = "data_cheque_pagamento")
	private Date chequeData;

	@Size(min = 5, max = 50)
	@Column(name = "nominal_pagamento")
	private String nominal;

	@Column(name = "num_cheque_pagamento")
	private Integer chequeNumero;

	// bi-directional many-to-one association to FornecedorEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_fornecedor")
	private FornecedorEntity fornecedor;

	// bi-directional many-to-one association to PlanoContaEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_plano_conta")
	private PlanoContaEntity planoConta;

	public PagamentoEntity() {
		super();
	}

	public PagamentoEntity(Long id) {
		super(id);
	}

	public Date getChequeData() {
		return this.chequeData;
	}

	public void setChequeData(Date chequeData) {
		this.chequeData = chequeData;
	}

	public String getNominal() {
		return this.nominal;
	}

	public void setNominal(String nominal) {
		this.nominal = nominal;
	}

	public Integer getChequeNumero() {
		return this.chequeNumero;
	}

	public void setChequeNumero(Integer chequeNumero) {
		this.chequeNumero = chequeNumero;
	}

	public FornecedorEntity getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public PlanoContaEntity getPlanoConta() {
		return this.planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

}