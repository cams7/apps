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
@StaticMetamodel(CfopEntity.class)
public class CfopEntity_ {
	public static volatile SingularAttribute<CfopEntity, Short> id;
	public static volatile SingularAttribute<CfopEntity, String> descricao;
	public static volatile SingularAttribute<CfopEntity, CfopEntity> cfop;
	public static volatile ListAttribute<CfopEntity, NFECabecarioEntity> nfes;
	public static volatile ListAttribute<CfopEntity, VendaCabecarioEntity> vendas;
	public static volatile ListAttribute<CfopEntity, CfopEntity> cfops;
}
