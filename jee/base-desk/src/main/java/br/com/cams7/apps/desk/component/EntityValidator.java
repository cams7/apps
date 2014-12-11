package br.com.cams7.apps.desk.component;

import static javax.validation.Validation.buildDefaultValidatorFactory;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ValidatorFactory;

import org.springframework.stereotype.Component;

import br.com.cams7.apps.desk.util.AppResourceBundleMessage;
import br.com.cams7.apps.jpa.domain.BaseEntity;

/**
 * Implementa componente para validar os dados da entidade
 * <code>Mercadoria</code>.
 * 
 * <p>
 * A validação ocorre através do Bean Validations, mecanismo de validação padrão
 * do Java baseado em anotações.
 * </p>
 * 
 * @author YaW Tecnologia
 */
@Component
public class EntityValidator {

	private static ValidatorFactory factory;

	static {
		factory = buildDefaultValidatorFactory();
	}

	protected static ValidatorFactory getFactory() {
		return factory;
	}

	/**
	 * Método que aplica o mecanismo de validação a entidade.
	 * 
	 * @param e
	 *            entidade a ser validada.
	 * @return String <i>vazia</i>, caso não exista problemas de validação. Ou
	 *         retorna uma string com as mensagens de validação.
	 */
	// @Override
	public <E extends BaseEntity<?>> String validate(
			AppResourceBundleMessage bundleMessage, E entity) {
		StringBuilder sb = new StringBuilder();
		if (entity != null) {
			javax.validation.Validator validator = getFactory().getValidator();

			Set<ConstraintViolation<E>> constraintViolations = validator
					.validate(entity);

			if (!constraintViolations.isEmpty()) {
				sb.append(bundleMessage.getEntityName());
				for (ConstraintViolation<E> constraint : constraintViolations) {
					sb.append(String.format("%n%s: %s", bundleMessage
							.getEntityFieldName(constraint.getPropertyPath()),
							constraint.getMessage()));
				}
			}
		}
		return sb.toString();
	}

}
