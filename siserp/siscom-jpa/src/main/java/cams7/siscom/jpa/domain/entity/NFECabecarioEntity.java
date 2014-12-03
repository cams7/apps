package cams7.siscom.jpa.domain.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import cams7.siscom.jpa.domain.Cabecario;

/**
 * The persistent class for the nfe_cab database table.
 * 
 */
@Entity
@Table(name = "nfe_cab")
@AttributeOverrides({
		@AttributeOverride(name = "data", column = @Column(name = "entrada_nfe_cab")),
		@AttributeOverride(name = "valor", column = @Column(name = "total_nfe_cab")) })
@NamedQuery(name = "NFECabecario.findAll", query = "SELECT n FROM NFECabecarioEntity n")
public class NFECabecarioEntity extends Cabecario<Long> {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "numero_nfe_cab")
	private Long id;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "emissao_nfe_cab")
	private Date emissaoNFE;

	// bi-directional many-to-one association to CfopEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_cfop")
	private CfopEntity cfop;

	// bi-directional many-to-one association to FornecedorEntity
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_fornecedor")
	private FornecedorEntity fornecedor;

	// bi-directional many-to-one association to NFEDetalheEntity
	@OneToMany(mappedBy = "nfe")
	private List<NFEDetalheEntity> detalhes;

	public NFECabecarioEntity() {
		super();
	}

	public NFECabecarioEntity(Long id) {
		super(id);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getEmissaoNFE() {
		return this.emissaoNFE;
	}

	public void setEmissaoNFE(Date emissaoNFE) {
		this.emissaoNFE = emissaoNFE;
	}

	public CfopEntity getCfop() {
		return this.cfop;
	}

	public void setCfop(CfopEntity cfop) {
		this.cfop = cfop;
	}

	public FornecedorEntity getFornecedor() {
		return this.fornecedor;
	}

	public void setFornecedor(FornecedorEntity fornecedor) {
		this.fornecedor = fornecedor;
	}

	public List<NFEDetalheEntity> getDetalhes() {
		return this.detalhes;
	}

	public void setDetalhes(List<NFEDetalheEntity> detalhes) {
		this.detalhes = detalhes;
	}

	public NFEDetalheEntity addDetalhe(NFEDetalheEntity detalhe) {
		getDetalhes().add(detalhe);
		detalhe.setNfe(this);

		return detalhe;
	}

	public NFEDetalheEntity removeDetalhe(NFEDetalheEntity detalhe) {
		getDetalhes().remove(detalhe);
		detalhe.setNfe(null);

		return detalhe;
	}

}