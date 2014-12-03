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
 * Tipo de Pagamento ou Recebimento
 * 
 */
@Entity
@Table(name = "tipo_pgto")
@NamedQuery(name = "TipoPgto.findAll", query = "SELECT t FROM TipoPgtoEntity t")
public class TipoPgtoEntity extends cams7.jpa.domain.BaseEntity<Short> {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "tipo_pgto_seq", sequenceName = "tipo_pgto_seq", initialValue = INITIAL_VALUE, allocationSize = ALLOCATION_SIZE)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tipo_pgto_seq")
	@Column(name = "cod_tipo_pgto")
	private Short id;

	@NotEmpty
	@Size(min = 5, max = 20)
	@Column(name = "descricao_tipo_pgto")
	private String descricao;

	// bi-directional many-to-one association to PagamentoEntity
	@OneToMany(mappedBy = "tipoPgto")
	private List<PagamentoEntity> pagamentos;

	// bi-directional many-to-one association to RecebimentoEntity
	@OneToMany(mappedBy = "tipoPgto")
	private List<RecebimentoEntity> recebimentos;

	// bi-directional many-to-one association to VendaCabecarioEntity
	@OneToMany(mappedBy = "tipoPgto")
	private List<VendaCabecarioEntity> vendas;

	public TipoPgtoEntity() {
		super();
	}

	public TipoPgtoEntity(Short id) {
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

	public List<PagamentoEntity> getPagamentos() {
		return this.pagamentos;
	}

	public void setPagamentos(List<PagamentoEntity> pagamentos) {
		this.pagamentos = pagamentos;
	}

	public PagamentoEntity addPagamento(PagamentoEntity pagamento) {
		getPagamentos().add(pagamento);
		pagamento.setTipoPgto(this);

		return pagamento;
	}

	public PagamentoEntity removePagamento(PagamentoEntity pagamento) {
		getPagamentos().remove(pagamento);
		pagamento.setTipoPgto(null);

		return pagamento;
	}

	public List<RecebimentoEntity> getRecebimentos() {
		return this.recebimentos;
	}

	public void setRecebimentos(List<RecebimentoEntity> recebimentos) {
		this.recebimentos = recebimentos;
	}

	public RecebimentoEntity addRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().add(recebimento);
		recebimento.setTipoPgto(this);

		return recebimento;
	}

	public RecebimentoEntity removeRecebimento(RecebimentoEntity recebimento) {
		getRecebimentos().remove(recebimento);
		recebimento.setTipoPgto(null);

		return recebimento;
	}

	public List<VendaCabecarioEntity> getVendas() {
		return this.vendas;
	}

	public void setVendas(List<VendaCabecarioEntity> vendas) {
		this.vendas = vendas;
	}

	public VendaCabecarioEntity addVenda(VendaCabecarioEntity venda) {
		getVendas().add(venda);
		venda.setTipoPgto(this);

		return venda;
	}

	public VendaCabecarioEntity removeVenda(VendaCabecarioEntity venda) {
		getVendas().remove(venda);
		venda.setTipoPgto(null);

		return venda;
	}

}