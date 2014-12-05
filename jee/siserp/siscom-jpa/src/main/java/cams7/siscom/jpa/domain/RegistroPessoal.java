package cams7.siscom.jpa.domain;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CNPJ;
import org.hibernate.validator.constraints.br.CPF;

@MappedSuperclass
public abstract class RegistroPessoal<PK extends Serializable> extends Usuario<PK> {

	private static final long serialVersionUID = 1L;

	@Size(min = 5, max = 15)
	private String rg;

	@Size(min = 3, max = 10)
	private String orgaoExpedidor;

	@NotEmpty
	@CPF
	@Size(min = 11, max = 15)
	private String cpf;

	@CNPJ
	@Size(min = 14, max = 20)
	private String cnpj;

	public RegistroPessoal() {
		super();
	}

	public RegistroPessoal(PK id) {
		super(id);
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getOrgaoExpedidor() {
		return orgaoExpedidor;
	}

	public void setOrgaoExpedidor(String orgaoExpedidor) {
		this.orgaoExpedidor = orgaoExpedidor;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
