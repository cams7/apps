package cams7.apps.desk.event;

import java.util.List;

import cams7.apps.jpa.domain.BaseEntity;

/**
 * Evento deve ser gerado durante a pesquisa de entidades.
 * 
 * <p>
 * Recebe um <code>List</code> com a(s) <code>Entidade<code>(s) encontrada(s).
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class SearchEvent extends AbstractEvent<List<BaseEntity<?>>> {

	public SearchEvent(List<BaseEntity<?>> entities) {
		super(entities);
	}

}
