package br.com.cams7.siscom.jpa.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * CFOP - Código Fiscal de Operações e Prestação.
 * 
 */
@Entity
@Table(name = "cfop")
@NamedQuery(name = "Cfop.findAll", query = "SELECT c FROM CfopEntity c")
public class CfopEntity extends br.com.cams7.apps.jpa.domain.BaseEntity<Short> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "cod_cfop")
	private Short id;

	// @Lob
	// @Basic(fetch = FetchType.LAZY)
	@NotEmpty
	@Size(min = 10, max = 100)
	@Column(name = "descricao_cfop")
	private String descricao;

	@ManyToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "cod_cfop_principal", referencedColumnName = "cod_cfop")
	private CfopEntity cfop;

	// bi-directional many-to-one association to NFECabecarioEntity
	@OneToMany(mappedBy = "cfop")
	private List<NFECabecarioEntity> nfes;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@OneToMany(mappedBy = "cfop")
	private List<VendaCabecarioEntity> vendas;

	@OneToMany(mappedBy = "cfop")
	private List<CfopEntity> cfops;

	public CfopEntity() {
		super();
	}

	public CfopEntity(Short id) {
		super(id);
	}

	public Short getId() {
		return this.id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getDescricao() {
		return this.descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public CfopEntity getCfop() {
		return cfop;
	}

	public void setCfop(CfopEntity cfop) {
		this.cfop = cfop;
	}

	public List<NFECabecarioEntity> getNfes() {
		return this.nfes;
	}

	public void setNfes(List<NFECabecarioEntity> nfes) {
		this.nfes = nfes;
	}

	public NFECabecarioEntity addNfe(NFECabecarioEntity nfe) {
		getNfes().add(nfe);
		nfe.setCfop(this);

		return nfe;
	}

	public NFECabecarioEntity removeNfe(NFECabecarioEntity nfe) {
		getNfes().remove(nfe);
		nfe.setCfop(null);

		return nfe;
	}

	public List<VendaCabecarioEntity> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<VendaCabecarioEntity> vendas) {
		this.vendas = vendas;
	}

	public VendaCabecarioEntity addVenda(VendaCabecarioEntity venda) {
		getVendas().add(venda);
		venda.setCfop(this);

		return venda;
	}

	public VendaCabecarioEntity removeVenda(VendaCabecarioEntity venda) {
		getVendas().remove(venda);
		venda.setCfop(null);

		return venda;
	}

	public List<CfopEntity> getCfops() {
		return cfops;
	}

	public void setCfops(List<CfopEntity> cfops) {
		this.cfops = cfops;
	}

}