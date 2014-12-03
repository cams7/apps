package cams7.siscom.jpa.domain.entity;

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
 * The persistent class for the plano_conta database table.
 * 
 */
@Entity
@Table(name = "plano_conta")
@NamedQuery(name = "PlanoConta.findAll", query = "SELECT p FROM PlanoContaEntity p")
public class PlanoContaEntity extends cams7.jpa.domain.BaseEntity<Integer> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "plano_conta_seq", sequenceName = "plano_conta_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_conta_seq")
	@Column(name = "cod_plano_conta")
	private Integer id;

	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "descricao_plano_conta")
	private String descricao;

	// bi-directional many-to-one association to MovimentoBancarioEntity
	@OneToMany(mappedBy = "planoConta")
	private List<MovimentoBancarioEntity> movimentos;

	// bi-directional many-to-one association to PagamentoEntity
	@OneToMany(mappedBy = "planoConta")
	private List<PagamentoEntity> pagamentos;

	public PlanoContaEntity() {
		super();
	}

	public PlanoContaEntity(Integer id) {
		super(id);
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public List<MovimentoBancarioEntity> getMovimentos() {
		return this.movimentos;
	}

	public void setMovimentos(List<MovimentoBancarioEntity> movimentos) {
		this.movimentos = movimentos;
	}

	public MovimentoBancarioEntity addMovimento(
			MovimentoBancarioEntity movimento) {
		getMovimentos().add(movimento);
		movimento.setPlanoConta(this);

		return movimento;
	}

	public MovimentoBancarioEntity removeMovimento(
			MovimentoBancarioEntity movimento) {
		getMovimentos().remove(movimento);
		movimento.setPlanoConta(null);

		return movimento;
	}

	public List<PagamentoEntity> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<PagamentoEntity> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public PagamentoEntity addPagamento(PagamentoEntity pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setPlanoConta(this);

		return pagamento;
	}

	public PagamentoEntity removePagamento(PagamentoEntity pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setPlanoConta(null);

		return pagamento;
	}

}