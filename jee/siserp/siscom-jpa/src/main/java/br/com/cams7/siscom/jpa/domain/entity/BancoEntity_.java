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
@StaticMetamodel(BancoEntity.class)
public class BancoEntity_ {
	public static volatile SingularAttribute<BancoEntity, String> id;
	public static volatile SingularAttribute<BancoEntity, String> nome;
	public static volatile SingularAttribute<BancoEntity, String> site;
	public static volatile ListAttribute<BancoEntity, ContaBancariaEntity> contas;
}
