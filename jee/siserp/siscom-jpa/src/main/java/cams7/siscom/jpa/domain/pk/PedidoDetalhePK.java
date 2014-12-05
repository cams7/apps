package cams7.siscom.jpa.domain.pk;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

import cams7.siscom.jpa.domain.entity.ProdutoEntity;

/**
 * The primary key class for the pedido_det database table.
 * 
 */
@Embeddable
public class PedidoDetalhePK implements Serializable {
	// default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@NotNull
	@Column(name = "cod_req_cab", insertable = false, updatable = false)
	private Long requisicaoId;

	@NotEmpty
	@Size(min = ProdutoEntity.ID_SIZE_MIN, max = ProdutoEntity.ID_SIZE_MAX)
	@Column(name = "cod_produto", length = ProdutoEntity.ID_SIZE_MAX, insertable = false, updatable = false)
	private String produtoId;

	@NotNull
	@Column(name = "cod_cot_cab", insertable = false, updatable = false)
	private Long cotacaoId;

	@NotNull
	@Column(name = "cod_pedido_cab", insertable = false, updatable = false)
	private Long pedidoId;

	public PedidoDetalhePK() {
		super();
	}

	public PedidoDetalhePK(Long requisicaoId, String produtoId, Long cotacaoId,
			Long pedidoId) {
		this();

		this.requisicaoId = requisicaoId;
		this.produtoId = produtoId;
		this.cotacaoId = cotacaoId;
		this.pedidoId = pedidoId;
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

	public Long getCotacaoId() {
		return this.cotacaoId;
	}

	public void setCotacaoId(Long cotacaoId) {
		this.cotacaoId = cotacaoId;
	}

	public Long getPedidoId() {
		return this.pedidoId;
	}

	public void setPedidoId(Long pedidoId) {
		this.pedidoId = pedidoId;
	}

	public boolean equals(Object entityPK) {
		if (this == entityPK) {
			return true;
		}
		if (!(entityPK instanceof PedidoDetalhePK)) {
			return false;
		}
		PedidoDetalhePK pk = (PedidoDetalhePK) entityPK;
		return this.requisicaoId.equals(pk.requisicaoId)
				&& this.produtoId.equals(pk.produtoId)
				&& this.cotacaoId.equals(pk.cotacaoId)
				&& this.pedidoId.equals(pk.pedidoId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.requisicaoId.hashCode();
		hash = hash * prime + this.produtoId.hashCode();
		hash = hash * prime + this.cotacaoId.hashCode();
		hash = hash * prime + this.pedidoId.hashCode();

		return hash;
	}
}