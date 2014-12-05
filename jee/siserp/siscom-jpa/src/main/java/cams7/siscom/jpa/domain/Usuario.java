package cams7.siscom.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import cams7.apps.jpa.domain.BaseEntity;

@MappedSuperclass
public abstract class Usuario<PK extends Serializable> extends BaseEntity<PK> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USUARIO_GENERATOR")
	private PK id;

	@NotEmpty
	@Size(min = 3, max = 50)
	private String nome;

	@Email
	@Size(min = 5, max = 50)
	private String email;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	private Date cadastro;

	public Usuario() {
		super();
	}

	public Usuario(PK id) {
		super(id);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getCadastro() {
		return cadastro;
	}

	public void setCadastro(Date cadastro) {
		this.cadastro = cadastro;
	}

}
