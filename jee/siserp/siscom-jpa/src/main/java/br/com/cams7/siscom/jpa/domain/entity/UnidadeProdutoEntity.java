package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the unidade database table.
 * 
 */
@Entity
@Table(name = "unidade")
@NamedQuery(name = "UnidadeProduto.findAll", query = "SELECT u FROM UnidadeProdutoEntity u")
public class UnidadeProdutoEntity extends br.com.cams7.apps.jpa.domain.BaseEntity<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "unidade_seq", sequenceName = "unidade_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "unidade_seq")
	@Column(name = "cod_unidade")
	private Long id;

	@NotEmpty
	@Size(max = 5)
	@Column(name = "descricao_unidade", length = 5, columnDefinition = "char(5)")
	private String descricao;

	// bi-directional many-to-one association to ProdutoEntity
	@OneToMany(mappedBy = "unidade")
	private List<ProdutoEntity> produtos;

	public UnidadeProdutoEntity() {
		super();
	}

	public UnidadeProdutoEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<ProdutoEntity> getProdutos() {
		return this.produtos;
	}

	public void setProdutos(List<ProdutoEntity> produtos) {
		this.produtos = produtos;
	}

	public ProdutoEntity addProduto(ProdutoEntity produto) {
		getProdutos().add(produto);
		produto.setUnidade(this);

		return produto;
	}

	public ProdutoEntity removeProduto(ProdutoEntity produto) {
		getProdutos().remove(produto);
		produto.setUnidade(null);

		return produto;
	}

}