package cams7.siscom.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import cams7.jpa.domain.BaseEntity;
import cams7.siscom.jpa.domain.entity.ContaBancariaEntity;
import cams7.siscom.jpa.domain.entity.TipoPgtoEntity;

/**
 * 
 * @author cesar
 *
 * @param <PK>
 * 
 *            Pagamento ou Recebimento
 */
@MappedSuperclass
public abstract class PgtoRbto<PK extends Serializable> extends BaseEntity<PK> {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PGTO_RBTO_GENERATOR")
	private PK id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date data;

	@Temporal(TemporalType.TIMESTAMP)
	private Date emissao;

	@Temporal(TemporalType.TIMESTAMP)
	private Date lancamentoData;

	@Size(min = 5, max = 20)
	private String documentoNumero;

	@Temporal(TemporalType.TIMESTAMP)
	private Date vencimento;

	private Double desconto;

	private Double juro;

	private Double multa;

	/**
	 * Valor pago ou recebido
	 */
	private Double valor;

	private Double total;

	// bi-directional many-to-one association to TipoPgtoModel
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "cod_tipo_pgto")
	private TipoPgtoEntity tipoPgto;

	// bi-directional many-to-one association to ContaBancariaModel
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_conta_bancaria")
	private ContaBancariaEntity contaBancaria;

	public PgtoRbto() {
		super();
	}

	public PgtoRbto(PK id) {
		super(id);
	}

	public PK getId() {
		return id;
	}

	public void setId(PK id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getEmissao() {
		return emissao;
	}

	public void setEmissao(Date emissao) {
		this.emissao = emissao;
	}

	public Date getLancamentoData() {
		return lancamentoData;
	}

	public void setLancamentoData(Date lancamentoData) {
		this.lancamentoData = lancamentoData;
	}

	public String getDocumentoNumero() {
		return documentoNumero;
	}

	public void setDocumentoNumero(String documentoNumero) {
		this.documentoNumero = documentoNumero;
	}

	public Date getVencimento() {
		return vencimento;
	}

	public void setVencimento(Date vencimento) {
		this.vencimento = vencimento;
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Double getJuro() {
		return juro;
	}

	public void setJuro(Double juro) {
		this.juro = juro;
	}

	public Double getMulta() {
		return multa;
	}

	public void setMulta(Double multa) {
		this.multa = multa;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public TipoPgtoEntity getTipoPgto() {
		return tipoPgto;
	}

	public void setTipoPgto(TipoPgtoEntity tipoPgto) {
		this.tipoPgto = tipoPgto;
	}

	public ContaBancariaEntity getContaBancaria() {
		return contaBancaria;
	}

	public void setContaBancaria(ContaBancariaEntity contaBancaria) {
		this.contaBancaria = contaBancaria;
	}

}
