/**
 * 
 */
package cams7.siscom.jpa.domain.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author cesar
 *
 */
@StaticMetamodel(TipoPgtoEntity.class)
public class TipoPgtoEntity_ {
	public static volatile SingularAttribute<TipoPgtoEntity, Short> id;
	public static volatile SingularAttribute<TipoPgtoEntity, String> descricao;
	public static volatile ListAttribute<TipoPgtoEntity, PagamentoEntity> pagamentos;
	public static volatile ListAttribute<TipoPgtoEntity, RecebimentoEntity> recebimentos;
	public static volatile ListAttribute<TipoPgtoEntity, VendaCabecarioEntity> vendas;
}
