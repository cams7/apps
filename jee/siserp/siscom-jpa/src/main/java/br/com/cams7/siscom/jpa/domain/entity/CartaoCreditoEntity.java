package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * The persistent class for the cartao_credito database table.
 * 
 * @author cesar
 *
 */
@Entity
@Table(name = "cartao_credito", uniqueConstraints = @UniqueConstraint(columnNames = { "empresa" }))
@NamedQuery(name = "CartaoCredito.findAll", query = "SELECT c FROM CartaoCreditoEntity c")
public class CartaoCreditoEntity extends br.com.cams7.apps.jpa.domain.BaseEntity<Short> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "cartao_credito_seq", sequenceName = "cartao_credito_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartao_credito_seq")
	@Column(name = "id_cartao_credito")
	private Short id;

	@NotEmpty
	@Size(min = 5, max = 50)
	@Column(name = "empresa")
	private String empresaNome;

	@NotNull
	@Column(name = "juros_parcelado")
	private Float juroParceladoPercentual;

	@NotEmpty
	@Size(min = 2, max = 50)
	@Column(name = "juros_rotativo")
	private String juroRotativoPercentual;

	@Column(name = "multa")
	private Float multaPercentual;

	@Column(name = "multa_cobrada_dia")
	private Byte multaCobradaDia;

	@Enumerated(EnumType.STRING)
	@Column(name = "multa_sem_minimo_total", length = 6)
	private TipoMulta tipoMulta;

	@NotNull
	@Column(name = "pagto_minimo")
	private Float pgtoMinimoPercentual;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@OneToMany(mappedBy = "cartaoCredito")
	private List<VendaCabecarioEntity> vendas;

	public CartaoCreditoEntity() {
		super();
	}

	public CartaoCreditoEntity(Short id) {
		super(id);
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getEmpresaNome() {
		return this.empresaNome;
	}

	public void setEmpresaNome(String empresaNome) {
		this.empresaNome = empresaNome;
	}

	public Float getJuroParceladoPercentual() {
		return this.juroParceladoPercentual;
	}

	public void setJuroParceladoPercentual(Float juroParceladoPercentual) {
		this.juroParceladoPercentual = juroParceladoPercentual;
	}

	public String getJuroRotativoPercentual() {
		return this.juroRotativoPercentual;
	}

	public void setJuroRotativoPercentual(String juroRotativoPercentual) {
		this.juroRotativoPercentual = juroRotativoPercentual;
	}

	public Float getMultaPercentual() {
		return this.multaPercentual;
	}

	public void setMultaPercentual(Float multaPercentual) {
		this.multaPercentual = multaPercentual;
	}

	public Byte getMultaCobradaDia() {
		return this.multaCobradaDia;
	}

	public void setMultaCobradaDia(Byte multaCobradaDia) {
		this.multaCobradaDia = multaCobradaDia;
	}

	public TipoMulta getTipoMulta() {
		return this.tipoMulta;
	}

	public void setTipoMulta(TipoMulta tipoMulta) {
		this.tipoMulta = tipoMulta;
	}

	public Float getPgtoMinimoPercentual() {
		return this.pgtoMinimoPercentual;
	}

	public void setPgtoMinimoPercentual(Float pgtoMinimoPercentual) {
		this.pgtoMinimoPercentual = pgtoMinimoPercentual;
	}

	public List<VendaCabecarioEntity> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<VendaCabecarioEntity> vendas) {
		this.vendas = vendas;
	}

	public VendaCabecarioEntity addVenda(VendaCabecarioEntity venda) {
		getVendas().add(venda);
		venda.setCartaoCredito(this);

		return venda;
	}

	public VendaCabecarioEntity removeVenda(VendaCabecarioEntity venda) {
		getVendas().remove(venda);
		venda.setCartaoCredito(null);

		return venda;
	}

	public enum TipoMulta {
		TOTAL, MINIMO;
	}
}