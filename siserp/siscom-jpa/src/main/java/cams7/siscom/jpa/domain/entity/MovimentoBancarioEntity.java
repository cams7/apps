package cams7.siscom.jpa.domain.entity;

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

import org.hibernate.validator.constraints.NotEmpty;

import cams7.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.Cheque;

/**
 * The persistent class for the movimento database table.
 * 
 */
@Entity
@Table(name = "movimento")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_movimento")),
		@AttributeOverride(name = "data", column = @Column(name = "data_cheque")),
		@AttributeOverride(name = "historico", column = @Column(name = "historico_movimento")),
		@AttributeOverride(name = "mesAno", column = @Column(name = "mes_ano_movimento")),
		@AttributeOverride(name = "numero", column = @Column(name = "num_cheque_movimento")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_documento", precision = 2)) })
@SequenceGenerator(name = "CHEQUE_GENERATOR", sequenceName = "movimento_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "MovimentoBancario.findAll", query = "SELECT m FROM MovimentoBancarioEntity m")
public class MovimentoBancarioEntity extends Cheque<Long> {
	private static final long serialVersionUID = 1L;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(name = "data_lancamento_movimento")
	private Date lancamentoData;

	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "num_documento_movimento")
	private String documentoNumero;

	// bi-directional many-to-one association to PlanoContaEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_plano_conta")
	private PlanoContaEntity planoConta;

	public MovimentoBancarioEntity() {
		super();
	}

	public MovimentoBancarioEntity(Long id) {
		super(id);
	}

	public Date getLancamentoData() {
		return this.lancamentoData;
	}

	public void setLancamentoData(Date lancamentoData) {
		this.lancamentoData = lancamentoData;
	}

	public String getDocumentoNumero() {
		return this.documentoNumero;
	}

	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
	}

	public PlanoContaEntity getPlanoConta() {
		return this.planoConta;
	}

	public void setPlanoConta(PlanoContaEntity planoConta) {
		this.planoConta = planoConta;
	}

}