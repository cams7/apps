/**
 * 
 */
package cams7.desk.ui.table;

import java.util.List;
import java.util.Map;

import javax.swing.table.AbstractTableModel;

import cams7.desk.util.AppResourceBundleMessage;
import cams7.jpa.domain.BaseEntity;
import cams7.jpa.domain.util.EntityAttribute;

/**
 * @author cesar
 *
 */
public abstract class BaseTableModel extends AbstractTableModel {

	private static final long serialVersionUID = 1L;

	private List<BaseEntity<?>> entities;

	private AppResourceBundleMessage bundleMessage;
	private Map<String, EntityAttribute> entityFields;

	public BaseTableModel(AppResourceBundleMessage bundleMessage,
			Map<String, EntityAttribute> entityFields) {
		super();

		this.bundleMessage = bundleMessage;
		this.entityFields = entityFields;

		initComponents();
	}

	protected abstract void initComponents();

	public void reload(List<BaseEntity<?>> entities) {
		this.entities = entities;
		// atualiza o componente na tela
		fireTableDataChanged();
	}

	public void reload(Map<String, EntityAttribute> entityFields) {
		this.entityFields = entityFields;
		initComponents();

		fireTableStructureChanged();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.TableModel#getRowCount()
	 */
	public int getRowCount() {
		if (getEntities() == null) {
			return 0;
		}
		int count = getEntities().size();
		return count;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#isCellEditable(int, int)
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		return false;
	}

	public BaseEntity<?> getEntityAt(int index) {
		BaseEntity<?> entity = getEntities().get(index);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnName(int)
	 */
	@Override
	public String getColumnName(int coluna) {
		return getColumnsName()[coluna];
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.table.AbstractTableModel#getColumnClass(int)
	 */
	@Override
	public Class<?> getColumnClass(int coluna) {
		return getColumnsType()[coluna];
	}

	protected abstract String[] getColumnsName();

	protected abstract Class<?>[] getColumnsType();

	protected List<BaseEntity<?>> getEntities() {
		return entities;
	}

	protected AppResourceBundleMessage getBundleMessage() {
		return bundleMessage;
	}

	protected Map<String, EntityAttribute> getEntityFields() {
		return entityFields;
	}

}
