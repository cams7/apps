package cams7.desk.event;

import cams7.jpa.domain.BaseEntity;

/**
 * Evento deve ser gerado durante a exclusão de uma <code>Entidade</code>.
 * 
 * <p>
 * Recebe a referência da <code>Entidade</code> que foi removida.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class DeleteEvent extends AbstractEvent<BaseEntity<?>> {

	public DeleteEvent(BaseEntity<?> entity) {
		super(entity);
	}

}
