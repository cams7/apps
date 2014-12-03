/**
 * 
 */
package cams7.desk.ui.table;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JTable;

import org.apache.log4j.Logger;

import cams7.desk.util.AppResourceBundleMessage;
import cams7.jpa.domain.BaseEntity;
import cams7.jpa.domain.util.EntityAttribute;
import cams7.util.ApplicationException;
import cams7.util.ApplicationUtil;

/**
 * @author cesar
 *
 */
public abstract class BaseTable extends JTable {

	private static final long serialVersionUID = 1L;

	private final Logger LOG = Logger.getLogger(this.getClass());

	private BaseTableModel model;

	public BaseTable(AppResourceBundleMessage bundleMessage,
			Map<String, EntityAttribute> entityFields) {

		model = new BaseTableModel(bundleMessage, entityFields) {

			private static final long serialVersionUID = 1L;

			private List<String> fieldsName;

			private List<String> columnsName;
			private List<Class<?>> columnsType;

			@Override
			protected void initComponents() {
				fieldsName = new ArrayList<String>();

				columnsName = new ArrayList<String>();
				columnsType = new ArrayList<Class<?>>();

				for (String fieldName : getEntityFields().keySet()) {
					EntityAttribute entityField = getEntityFields().get(fieldName);
					if (!entityField.isVisible())
						continue;

					fieldsName.add(fieldName);
					columnsName.add(getBundleMessage().getEntityFieldName(
							fieldName));
					columnsType.add(entityField.getType());
				}

			}

			@Override
			public Object getValueAt(int linha, int coluna) {
				BaseEntity<?> entity = getEntities().get(linha);
				for (int i = 0; i < fieldsName.size(); i++) {
					if (i == coluna)
						try {
							return ApplicationUtil.getEntityValue(entity,
									fieldsName.get(i));
						} catch (ApplicationException e) {
							LOG.error(e.getMessage());
							break;
						}
				}

				return null;
			}

			@Override
			public int getColumnCount() {
				return fieldsName.size();
			}

			@Override
			protected Class<?>[] getColumnsType() {
				Class<?>[] array = columnsType.toArray(new Class<?>[columnsType
						.size()]);
				return array;
			}

			@Override
			protected String[] getColumnsName() {
				String[] array = columnsName.toArray(new String[columnsName
						.size()]);
				return array;
			}

		};

		setModel(model);
	}

	/**
	 * @return <code>Entidade</code> selecionada na tabela. Caso a tabela não
	 *         tenha elementos, retorna <code>null</code>.
	 */
	public BaseEntity<?> getEntitySelected() {
		int count = getSelectedRow();
		if (count < 0) {
			return null;
		}
		BaseEntity<?> entity = model.getEntityAt(count);
		return entity;
	}

	/**
	 * Recarrega a tabela de <code>Entidade</code> com a lista
	 * <code>entidades</code>.
	 * 
	 * @param entities
	 *            <code>List</code> com os elementos <code>Entidade</code> que
	 *            devem ser exibidos na tabela.
	 */
	public void reload(List<BaseEntity<?>> entities) {
		model.reload(entities);
	}

	public void reload(Map<String, EntityAttribute> entityFields) {
		model.reload(entityFields);
	}

}
