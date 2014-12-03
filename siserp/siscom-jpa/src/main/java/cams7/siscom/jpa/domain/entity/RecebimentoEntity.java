package cams7.siscom.jpa.domain.entity;

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
import javax.validation.constraints.NotNull;

import cams7.apps.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.PgtoRbto;

/**
 * The persistent class for the recebimento database table.
 * 
 */
@Entity
@Table(name = "recebimento")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_recebimento")),
		@AttributeOverride(name = "data", column = @Column(name = "data_recebimento")),
		@AttributeOverride(name = "emissao", column = @Column(name = "emissao_recebimento")),
		@AttributeOverride(name = "lancamentoData", column = @Column(name = "lancamento_recebimento")),
		@AttributeOverride(name = "documentoNumero", column = @Column(name = "num_documento")),
		@AttributeOverride(name = "vencimento", column = @Column(name = "vencimento_recebimento")),
		@AttributeOverride(name = "desconto", column = @Column(name = "vlr_desconto_recebimento")),
		@AttributeOverride(name = "juro", column = @Column(name = "vlr_juro_recebimento")),
		@AttributeOverride(name = "multa", column = @Column(name = "vlr_multa_recebimento")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_recebido")),
		@AttributeOverride(name = "total", column = @Column(name = "vlr_total_recebimento")) })
@SequenceGenerator(name = "PGTO_RBTO_GENERATOR", sequenceName = "recebimento_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "Recebimento.findAll", query = "SELECT r FROM RecebimentoEntity r")
public class RecebimentoEntity extends PgtoRbto<Long> {
	private static final long serialVersionUID = 1L;

	// bi-directional many-to-one association to ClienteEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cliente")
	private ClienteEntity cliente;

	public RecebimentoEntity() {
		super();
	}

	public RecebimentoEntity(Long id) {
		super(id);
	}

	public ClienteEntity getCliente() {
		return this.cliente;
	}

	public void setCliente(ClienteEntity cliente) {
		this.cliente = cliente;
	}

}