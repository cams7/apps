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
@StaticMetamodel(ContaBancariaEntity.class)
public class ContaBancariaEntity_ {
	public static volatile SingularAttribute<ContaBancariaEntity, Long> id;
	public static volatile SingularAttribute<ContaBancariaEntity, Integer> agenciaNumero;
	public static volatile SingularAttribute<ContaBancariaEntity, Integer> contaNumero;
	public static volatile SingularAttribute<ContaBancariaEntity, String> bancoFone;
	public static volatile SingularAttribute<ContaBancariaEntity, String> bancoGerente;
	public static volatile SingularAttribute<ContaBancariaEntity, BancoEntity> banco;
	public static volatile ListAttribute<ContaBancariaEntity, PagamentoEntity> pagamentos;
	public static volatile ListAttribute<ContaBancariaEntity, RecebimentoEntity> recebimentos;
}
