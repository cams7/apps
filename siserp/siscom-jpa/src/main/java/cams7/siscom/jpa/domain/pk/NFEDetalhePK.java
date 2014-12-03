package cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the nfe_det database table.
 * 
 */
@Embeddable
public class NFEDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	@NotNull
	@Column(name = "numero_nfe_cab", insertable = false, updatable = false)
	private Long nfeId;

	public NFEDetalhePK() {
		super();
	}

	public NFEDetalhePK(String produtoId, Long nfeId) {
		this();

		this.produtoId = produtoId;
		this.nfeId = nfeId;
	}

	public String getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public Long getNfeId() {
		return this.nfeId;
	}

	public void setNfeId(Long nfeId) {
		this.nfeId = nfeId;
	}

	public boolean equals(Object entityPK) {
		if (this == entityPK) {
			return true;
		}
		if (!(entityPK instanceof NFEDetalhePK)) {
			return false;
		}
		NFEDetalhePK pk = (NFEDetalhePK) entityPK;
		return this.produtoId.equals(pk.produtoId)
				&& this.nfeId.equals(pk.nfeId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.produtoId.hashCode();
		hash = hash * prime + this.nfeId.hashCode();

		return hash;
	}
}