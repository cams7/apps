package br.com.cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the orc_det database table.
 * 
 */
@Embeddable
public class OrcamentoDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cod_orc_cab", insertable = false, updatable = false)
	private Long orcamentoId;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	public OrcamentoDetalhePK() {
		super();
	}

	public OrcamentoDetalhePK(Long orcamentoId, String produtoId) {
		this();

		this.orcamentoId = orcamentoId;
		this.produtoId = produtoId;
	}

	public Long getOrcamentoId() {
		return this.orcamentoId;
	}

	public void setOrcamentoId(Long orcamentoId) {
		this.orcamentoId = orcamentoId;
	}

	public String getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public boolean equals(Object entityPK) {
		if (this == entityPK) {
			return true;
		}
		if (!(entityPK instanceof OrcamentoDetalhePK)) {
			return false;
		}
		OrcamentoDetalhePK pk = (OrcamentoDetalhePK) entityPK;
		return this.orcamentoId.equals(pk.orcamentoId)
				&& this.produtoId.equals(pk.produtoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.orcamentoId.hashCode();
		hash = hash * prime + this.produtoId.hashCode();

		return hash;
	}
}