/**
 * 
 */
package cams7.apps.jpa.domain.util;

/**
 * @author cesar
 *
 */
public class EntityAttribute {

	private Class<?> type;
	private byte index;
	private boolean visible;

	private Object value;

	public EntityAttribute(Class<?> type, byte index, boolean visible) {
		super();

		this.type = type;
		this.index = index;

		setVisible(visible);
	}

	public Class<?> getType() {
		return type;
	}

	public byte getIndex() {
		return index;
	}

	public void setIndex(byte index) {
		this.index = index;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Object getValue() {
		return value;
	}

	public void setValue(Object value) {
		this.value = value;
	}

}
