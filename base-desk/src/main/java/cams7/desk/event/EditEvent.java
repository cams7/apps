package cams7.desk.event;

import cams7.jpa.domain.BaseEntity;

/**
 * Evento deve ser gerado durante a inclusão de uma <code>Entidade</code>.
 * 
 * <p>
 * Recebe a referência da <code>Entidade</code> que foi incluida.
 * </p>
 * 
 * @author YaW Tecnologia
 */
public class EditEvent extends AbstractEvent<BaseEntity<?>> {

	public EditEvent(BaseEntity<?> entity) {
		super(entity);
	}
}
