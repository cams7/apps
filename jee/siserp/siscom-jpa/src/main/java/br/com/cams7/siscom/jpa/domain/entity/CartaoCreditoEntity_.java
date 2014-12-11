/**
 * 
 */
package br.com.cams7.siscom.jpa.domain.entity;

import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author cesar
 *
 */
@StaticMetamodel(CartaoCreditoEntity.class)
public class CartaoCreditoEntity_ {
	public static volatile SingularAttribute<CartaoCreditoEntity, Short> id;
	public static volatile SingularAttribute<CartaoCreditoEntity, String> empresaNome;
	public static volatile SingularAttribute<CartaoCreditoEntity, Float> juroParceladoPercentual;
	public static volatile SingularAttribute<CartaoCreditoEntity, String> juroRotativoPercentual;
	public static volatile SingularAttribute<CartaoCreditoEntity, Float> multaPercentual;
	public static volatile SingularAttribute<CartaoCreditoEntity, Byte> multaCobradaDia;
	public static volatile SingularAttribute<CartaoCreditoEntity, CartaoCreditoEntity.TipoMulta> tipoMulta;
	public static volatile SingularAttribute<CartaoCreditoEntity, Float> pgtoMinimoPercentual;
	public static volatile ListAttribute<CartaoCreditoEntity, VendaCabecarioEntity> vendas;
}
