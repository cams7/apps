package cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import cams7.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.Cheque;

/**
 * The persistent class for the conciliacao database table.
 * 
 */
@Entity
@Table(name = "conciliacao")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_conciliacao")),
		@AttributeOverride(name = "data", column = @Column(name = "data_cheque_conciliacao")),
		@AttributeOverride(name = "historico", column = @Column(name = "historico_conciliacao")),
		@AttributeOverride(name = "mesAno", column = @Column(name = "mes_ano_conciliacao")),
		@AttributeOverride(name = "numero", column = @Column(name = "num_cheque_conciliacao")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_cheque_conciliacao", precision = 2)) })
@SequenceGenerator(name = "CHEQUE_GENERATOR", sequenceName = "conciliacao_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "ConciliacaoBancaria.findAll", query = "SELECT c FROM ConciliacaoBancariaEntity c")
public class ConciliacaoBancariaEntity extends Cheque<Long> {
	private static final long serialVersionUID = 1L;

	public ConciliacaoBancariaEntity() {
		super();
	}

	public ConciliacaoBancariaEntity(Long id) {
		super(id);
	}

}