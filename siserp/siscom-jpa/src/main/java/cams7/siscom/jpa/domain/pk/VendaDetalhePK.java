package cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the venda_det database table.
 * 
 */
@Embeddable
public class VendaDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	@NotNull
	@Column(name = "cod_venda_cab", insertable = false, updatable = false)
	private Long vendaId;

	public VendaDetalhePK() {
		super();
	}

	public VendaDetalhePK(String produtoId, Long vendaId) {
		this();

		this.produtoId = produtoId;
		this.vendaId = vendaId;
	}

	public String getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public Long getVendaId() {
		return vendaId;
	}

	public void setVendaId(Long vendaId) {
		this.vendaId = vendaId;
	}

	public boolean equals(Object entityPK) {
		if (this == entityPK) {
			return true;
		}
		if (!(entityPK instanceof VendaDetalhePK)) {
			return false;
		}
		VendaDetalhePK pk = (VendaDetalhePK) entityPK;
		return this.produtoId.equals(pk.produtoId)
				&& this.vendaId.equals(pk.vendaId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.produtoId.hashCode();
		hash = hash * prime + this.vendaId.hashCode();

		return hash;
	}
}