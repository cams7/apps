package br.com.cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.cams7.siscom.jpa.domain.FechamentoCheque;

/**
 * The persistent class for the fechamento database table.
 * 
 */
@Entity
@Table(name = "fechamento")
@AttributeOverrides({ @AttributeOverride(name = "mesAno", column = @Column(name = "mes_ano_fechamento")) })
@NamedQuery(name = "Fechamento.findAll", query = "SELECT f FROM FechamentoEntity f")
public class FechamentoEntity extends FechamentoCheque<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "fechamento_seq", sequenceName = "fechamento_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "fechamento_seq")
	@Column(name = "cod_fechamento")
	private Long id;

	@NotNull
	@Column(name = "saldo_anterior")
	private Double saldoAnterior;

	@Column(name = "vlr_cheque_ncomp")
	private Double chequeNaoCompesadoVlr;

	@NotNull
	@Column(name = "vlr_pagamentos")
	private Double pagamentosVlr;

	@NotNull
	@Column(name = "vlr_recebimentos")
	private Double recebimentosVlr;

	@NotNull
	@Column(name = "vlr_saldo_conta")
	private Double saldoConta;

	@NotNull
	@Column(name = "vlr_saldo_real")
	private Double saldoReal;

	public FechamentoEntity() {
		super();
	}

	public FechamentoEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getSaldoAnterior() {
		return this.saldoAnterior;
	}

	public void setSaldoAnterior(Double saldoAnterior) {
		this.saldoAnterior = saldoAnterior;
	}

	public Double getChequeNaoCompesadoVlr() {
		return this.chequeNaoCompesadoVlr;
	}

	public void setChequeNaoCompesadoVlr(Double chequeNaoCompesadoVlr) {
		this.chequeNaoCompesadoVlr = chequeNaoCompesadoVlr;
	}

	public Double getPagamentosVlr() {
		return this.pagamentosVlr;
	}

	public void setPagamentosVlr(Double pagamentosVlr) {
		this.pagamentosVlr = pagamentosVlr;
	}

	public Double getRecebimentosVlr() {
		return this.recebimentosVlr;
	}

	public void setRecebimentosVlr(Double recebimentosVlr) {
		this.recebimentosVlr = recebimentosVlr;
	}

	public Double getSaldoConta() {
		return this.saldoConta;
	}

	public void setSaldoConta(Double saldoConta) {
		this.saldoConta = saldoConta;
	}

	public Double getSaldoReal() {
		return this.saldoReal;
	}

	public void setSaldoReal(Double saldoReal) {
		this.saldoReal = saldoReal;
	}

}