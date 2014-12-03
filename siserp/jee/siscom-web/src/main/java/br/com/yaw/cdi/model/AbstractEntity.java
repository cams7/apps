package br.com.yaw.cdi.model;

import java.io.Serializable;

/**
 * Estipula um contrato base para as entidades persistentes da aplicação.
 * 
 * <p>Esse contrato é utilizado pelo componente base de persistência: <code>AbstractPersistence</code>.</p>
 * 
 * @see br.com.yaw.cdi.service.AbstractPersistence
 * 
 * @author YaW Tecnologia
 */
public interface AbstractEntity extends Serializable {

	/**
	 * @return A referência para a chave primária (Primary Key) de cada objeto persistido.
	 * 		   Caso o objeto ainda não tenha sido persistido, deve retornar <code>null</code>.
	 */
	public Number getId();
	
}
