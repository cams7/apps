package br.com.cams7.apps.desk.event;

/**
 * Define um componente básico como estrutura de evento.
 * 
 * <p>
 * Um evento é um objeto que está relacionado a alguma ação no sistema, que
 * normalmente ocasiona em um reação na interface gráfica.
 * </p>
 *
 * <p>
 * O <code>AbstractEvent</code>, quando gerado deve ser encaminhado a algum
 * <code>AbstractEventListener</code>. Ambos os componentes implementam o design
 * pattern <strong>Observer</strong>.
 * </p>
 * 
 * <p>
 * <code>AbstractEventListener</code> atua como <i>observado</i>.
 * </p>
 * 
 * @see br.com.cams7.apps.desk.event.AbstractEventListener
 * 
 * @author YaW Tecnologia
 *
 * @param <T>
 *            tipo do elemento relacionado a geração do evento.
 */
public abstract class AbstractEvent<T> {

	private T target;

	public AbstractEvent(T target) {
		this.target = target;
	}

	public T getTarget() {
		return target;
	}
}
