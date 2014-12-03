package cams7.siscom.jpa.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Embeddable
public class Endereco {
	public static final byte LOGRADOURO_LENGTH = 100;
	public static final byte BAIRRO_LENGTH = 50;
	public static final byte CEP_LENGTH = 10;
	public static final byte CIDADE_LENGTH = 50;
	public static final byte UF_LENGTH = 2;

	@NotEmpty
	@Size(min = 5, max = LOGRADOURO_LENGTH)
	private String logradouro;

	@Size(min = 5, max = BAIRRO_LENGTH)
	private String bairro;

	@Size(min = 8, max = CEP_LENGTH)
	private String cep;

	@NotEmpty
	@Size(min = 5, max = CIDADE_LENGTH)
	private String cidade;

	@NotNull
	@Enumerated(EnumType.STRING)
	private UF uf;

	public Endereco() {
		super();
	}

	public String getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getBairro() {
		return this.bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return this.cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return this.cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public UF getUf() {
		return this.uf;
	}

	public void setUf(UF uf) {
		this.uf = uf;
	}

	public enum UF {
		AC("Acre", "Rio Branco", "Norte"),

		AL("Alagoas", "Maceió", "Nordeste"),

		AP("Amapá", "Macapá", "Norte"),

		AM("Amazonas", "Manaus", "Norte"),

		BA("Bahia", "Salvador", "Nordeste"),

		CE("Ceará", "Fortaleza", "Nordeste"),

		DF("Distrito Federal", "Brasília", "Centro-Oeste"),

		ES("Espírito Santo", "Vitória", "Sudeste"),

		GO("Goiás", "Goiânia", "Centro-Oeste"),

		MA("Maranhão", "São Luís", "Nordeste"),

		MT("Mato Grosso", "Cuiabá", "Centro-Oeste"),

		MS("Mato Grosso do Sul", "Campo Grande", "Centro-Oeste"),

		MG("Minas Gerais", "Belo Horizonte", "Sudeste"),

		PA("Pará", "Belém", "Norte"),

		PB("Paraíba", "João Pessoa", "Nordeste"),

		PR("Paraná", "Curitiba", "Sul"),

		PE("Pernambuco", "Recife", "Nordeste"),

		PI("Piauí", "Teresina", "Nordeste"),

		RJ("Rio de Janeiro", "Rio de Janeiro", "Sudeste"),

		RN("Rio Grande do Norte", "Natal", "Nordeste"),

		RS("Rio Grande do Sul", "Porto Alegre", "Sul"),

		RO("Rondônia", "Porto Velho", "Norte"),

		RR("Roraima", "Boa Vista", "Norte"),

		SC("Santa Catarina", "Florianópolis", "Sul"),

		SP("São Paulo", "São Paulo", "Sudeste"),

		SE("Sergipe", "Aracaju", "Nordeste"),

		TO("Tocantins", "Palmas", "Norte");

		private String estado;
		private String capital;
		private String regiao;

		private UF(String estado, String capital, String regiao) {
			this.estado = estado;
			this.capital = capital;
			this.regiao = regiao;
		}

		public String getEstado() {
			return estado;
		}

		public String getCapital() {
			return capital;
		}

		public String getRegiao() {
			return regiao;
		}

	}

}