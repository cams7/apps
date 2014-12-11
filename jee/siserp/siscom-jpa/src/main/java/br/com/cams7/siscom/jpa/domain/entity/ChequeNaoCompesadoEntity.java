package br.com.cams7.siscom.jpa.domain.entity;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.cams7.apps.jpa.domain.BaseEntity;
import br.com.cams7.siscom.jpa.domain.Cheque;

/**
 * The persistent class for the cheque_ncomp database table.
 * 
 */
@Entity
@Table(name = "cheque_ncomp")
@AttributeOverrides({
		@AttributeOverride(name = "id", column = @Column(name = "cod_cheque_ncomp")),
		@AttributeOverride(name = "data", column = @Column(name = "data_cheque_ncomp")),
		@AttributeOverride(name = "historico", column = @Column(name = "historico_ncomp")),
		@AttributeOverride(name = "mesAno", column = @Column(name = "mes_ano_ncomp")),
		@AttributeOverride(name = "numero", column = @Column(name = "num_cheque_ncomp")),
		@AttributeOverride(name = "valor", column = @Column(name = "vlr_cheque_ncomp", precision = 2)) })
@SequenceGenerator(name = "CHEQUE_GENERATOR", sequenceName = "cheque_ncomp_seq", initialValue = BaseEntity.INITIAL_VALUE, allocationSize = BaseEntity.ALLOCATION_SIZE)
@NamedQuery(name = "ChequeNaoCompesado.findAll", query = "SELECT c FROM ChequeNaoCompesadoEntity c")
public class ChequeNaoCompesadoEntity extends Cheque<Long> {
	private static final long serialVersionUID = 1L;

	public ChequeNaoCompesadoEntity() {
		super();
	}

	public ChequeNaoCompesadoEntity(Long id) {
		super(id);
	}

}