package br.com.cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import br.com.cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the req_det database table.
 * 
 */
@Embeddable
public class RequisicaoDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cod_req_cab", insertable = false, updatable = false)
	private Long requisicaoId;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	public RequisicaoDetalhePK() {
		super();
	}

	public RequisicaoDetalhePK(Long requisicaoId, String produtoId) {
		this();

		this.requisicaoId = requisicaoId;
		this.produtoId = produtoId;
	}

	public Long getRequisicaoId() {
		return this.requisicaoId;
	}

	public void setRequisicaoId(Long requisicaoId) {
		this.requisicaoId = requisicaoId;
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
		if (!(entityPK instanceof RequisicaoDetalhePK)) {
			return false;
		}
		RequisicaoDetalhePK pk = (RequisicaoDetalhePK) entityPK;
		return this.requisicaoId.equals(pk.requisicaoId)
				&& this.produtoId.equals(pk.produtoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.requisicaoId.hashCode();
		hash = hash * prime + this.produtoId.hashCode();

		return hash;
	}
}