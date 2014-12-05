package cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the cot_det database table.
 * 
 */
@Embeddable
public class CotacaoDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cod_cot_cab", insertable = false, updatable = false)
	private Long cotacaoId;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	@NotNull
	@Column(name = "cod_req_cab", insertable = false, updatable = false)
	private Long requisicaoId;

	public CotacaoDetalhePK() {
		super();
	}

	/**
	 * @param cotacaoId
	 * @param produtoId
	 * @param requisicaoId
	 */
	public CotacaoDetalhePK(Long cotacaoId, String produtoId, Long requisicaoId) {
		this();

		this.cotacaoId = cotacaoId;
		this.produtoId = produtoId;
		this.requisicaoId = requisicaoId;
	}

	public Long getCotacaoId() {
		return this.cotacaoId;
	}

	public void setCotacaoId(Long cotacaoId) {
		this.cotacaoId = cotacaoId;
	}

	public String getProdutoId() {
		return this.produtoId;
	}

	public void setProdutoId(String produtoId) {
		this.produtoId = produtoId;
	}

	public Long getRequisicaoId() {
		return this.requisicaoId;
	}

	public void setRequisicaoId(Long requisicaoId) {
		this.requisicaoId = requisicaoId;
	}

	public boolean equals(Object entityPK) {
		if (this == entityPK) {
			return true;
		}
		if (!(entityPK instanceof CotacaoDetalhePK)) {
			return false;
		}
		CotacaoDetalhePK pk = (CotacaoDetalhePK) entityPK;
		return this.cotacaoId.equals(pk.cotacaoId)
				&& this.produtoId.equals(pk.produtoId)
				&& this.requisicaoId.equals(pk.requisicaoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.cotacaoId.hashCode();
		hash = hash * prime + this.produtoId.hashCode();
		hash = hash * prime + this.requisicaoId.hashCode();

		return hash;
	}
}