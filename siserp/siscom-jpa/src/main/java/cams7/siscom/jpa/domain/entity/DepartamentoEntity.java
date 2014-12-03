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
 * The persistent class for the departamento database table.
 * 
 */
@Entity
@Table(name = "departamento")
@NamedQuery(name = "Departamento.findAll", query = "SELECT d FROM DepartamentoEntity d")
public class DepartamentoEntity extends cams7.jpa.domain.BaseEntity<Short> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "departamento_seq", sequenceName = "departamento_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "departamento_seq")
	@Column(name = "cod_departamento")
	private Short id;

	@NotEmpty
	@Size(min = 5, max = 50)
	@Column(name = "nome_departamento")
	private String nome;

	// bi-directional many-to-one association to FuncionarioEntity
	@OneToMany(mappedBy = "departamento")
	private List<FuncionarioEntity> funcionarios;

	public DepartamentoEntity() {
		super();
	}

	public DepartamentoEntity(Short id) {
		super(id);
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<FuncionarioEntity> getFuncionarios() {
		return this.funcionarios;
	}

	public void setFuncionarios(List<FuncionarioEntity> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public FuncionarioEntity addFuncionario(FuncionarioEntity funcionario) {
		getFuncionarios().add(funcionario);
		funcionario.setDepartamento(this);

		return funcionario;
	}

	public FuncionarioEntity removeFuncionario(FuncionarioEntity funcionario) {
		getFuncionarios().remove(funcionario);
		funcionario.setDepartamento(null);

		return funcionario;
	}

}