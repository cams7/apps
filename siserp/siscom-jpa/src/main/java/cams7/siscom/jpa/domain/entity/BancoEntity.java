package cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the banco database table.
 * 
 */
@Entity
@Table(name = "banco")
@NamedQuery(name = "Banco.findAll", query = "SELECT b FROM BancoEntity b")
public class BancoEntity extends cams7.jpa.domain.BaseEntity<String> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_banco", length = 5/*, columnDefinition = "char(5)"*/)
	private String id;

	@NotEmpty
	@Size(min = 5, max = 100)
	@Column(name = "nome_banco")
	private String nome;

	@Size(min = 10, max = 100)
	@Column(name = "site_banco")
	private String site;

	// bi-directional many-to-one association to ContaBancariaEntity
	@OneToMany(mappedBy = "banco")
	private List<ContaBancariaEntity> contas;

	public BancoEntity() {
		super();
	}

	/**
	 * @param id
	 */
	public BancoEntity(String id) {
		super(id);
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSite() {
		return this.site;
	}

	public void setSite(String site) {
		this.site = site;
	}

	public List<ContaBancariaEntity> getContas() {
		return this.contas;
	}

	public void setContas(List<ContaBancariaEntity> contas) {
		this.contas = contas;
	}

	public ContaBancariaEntity addConta(ContaBancariaEntity conta) {
		getContas().add(conta);
		conta.setBanco(this);

		return conta;
	}

	public ContaBancariaEntity removeConta(ContaBancariaEntity conta) {
		getContas().remove(conta);
		conta.setBanco(null);

		return conta;
	}

}