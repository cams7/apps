package br.com.cams7.apps.desk.event;

/**
 * Contrato para componentes com a capacidade de definir tratamento adequado para um evento.
 * 
 * <p>
 *  Em conjunto com <code>BaseCtrl</code> e <code>AbstractEvent</code>, esse componente 
 *  é parte do trecho que implementa o design pattern <strong>Observer</strong>.
 * </p>
 * 
 * <p><code>AbstractEventListener</code> atua como <i>observador</i>.</p>
 * 
 * @see br.com.cams7.apps.desk.event.AbstractEvent
 * 
 * @author YaW Tecnologia
 *
 * @param <E> tipo do Evento que deverá ser tratado.
 */
public interface AbstractEventListener<E extends AbstractEvent<?>> {

	 public void handleEvent(E event);

}
