/**
 * 
 */
package cams7.apps.util;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import cams7.apps.jpa.domain.BaseEntity;
import cams7.apps.jpa.domain.util.EntityAttribute;

/**
 * @author cesar
 *
 */
public final class ApplicationUtil {

	public static final String EMPTY = "";

	public static final Locale DEFAULT_LOCALE = new Locale("pt", "BR");
	public static final NumberFormat NUMBER_FORMAT = NumberFormat
			.getNumberInstance(DEFAULT_LOCALE);

	public static Class<?> getType(Class<?> type, byte argumentNumber) {
		Class<?> returnType = (Class<?>) ((ParameterizedType) type
				.getGenericSuperclass()).getActualTypeArguments()[argumentNumber];
		return returnType;
	}

	public static Class<?> getType(Object object, byte argumentNumber) {
		Class<?> type = getType(object.getClass(), argumentNumber);
		return type;
	}

	private static String getClassName(Class<?> type) {
		return type.getSimpleName();
	}

	public static String getEntityName(Class<BaseEntity<?>> entityType) {
		String entityName = getClassName(entityType);
		entityName = entityName.substring(0, entityName.lastIndexOf("Entity"));
		return entityName;
	}

	public static BaseEntity<?> getNewEntity(Class<BaseEntity<?>> entityType)
			throws ApplicationException {
		try {
			BaseEntity<?> entity = entityType.newInstance();
			return entity;
		} catch (InstantiationException | IllegalAccessException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		}
	}

	public static <ID extends Serializable> BaseEntity<ID> getNewEntity(
			Class<BaseEntity<?>> entityType, ID id) throws ApplicationException {

		@SuppressWarnings("unchecked")
		BaseEntity<ID> entity = (BaseEntity<ID>) getNewEntity(entityType);
		entity.setId(id);

		return entity;
	}

	@SuppressWarnings({ "unchecked" })
	public static Serializable getId(Class<BaseEntity<?>> entityType,
			String value) throws ApplicationException {
		Class<Serializable> idType = (Class<Serializable>) getType(entityType,
				(byte) 0);

		if (value == null)
			return null;

		value = value.trim();

		if (EMPTY.equals(value))
			return null;

		try {
			Constructor<Serializable> c = idType
					.getConstructor(new Class[] { String.class });
			Serializable id = c.newInstance(value);

			return id;
		} catch (InstantiationException | IllegalAccessException
				| NoSuchMethodException | SecurityException
				| IllegalArgumentException | InvocationTargetException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		}
	}

	public static URL getURLImage(Class<?> classType, String img) {
		URL imageURL = classType.getClassLoader().getResource("img/" + img);
		return imageURL;
	}

	public static String convertDoubleToString(Double value) {
		if (value == null)
			return null;
		return NUMBER_FORMAT.format(value);
	}

	public static String convertFloatToString(Float value) {
		if (value == null)
			return null;
		return convertDoubleToString(value.doubleValue());
	}

	public static Double formatStringToDouble(String value)
			throws ParseException {
		return NUMBER_FORMAT.parse(value).doubleValue();
	}

	public static Float formatStringToFloat(String value) throws ParseException {
		return formatStringToDouble(value).floatValue();
	}

	public static <E extends BaseEntity<?>> Map<String, EntityAttribute> getEntityFields(
			Class<E> entityType) {
		Map<String, EntityAttribute> entityFields = new HashMap<String, EntityAttribute>();

		byte count = 0;

		Field[] fields = entityType.getDeclaredFields();
		for (Field field : fields) {
			String name = field.getName();

			if ("serialVersionUID".equals(name))
				continue;

			if (field.isAccessible())
				continue;

			if (field.isAnnotationPresent(OneToMany.class))
				continue;

			Class<?> type = field.getType();

			boolean visible = true;
			if ("id".equals(name))
				visible = false;

			if (field.isAnnotationPresent(ManyToOne.class))
				visible = false;

			EntityAttribute entityField = new EntityAttribute(type, count++, visible);

			entityFields.put(name, entityField);
		}

		return entityFields;
	}

	public static Object getEntityValue(BaseEntity<?> entity, String fieldName)
			throws ApplicationException {
		@SuppressWarnings("unchecked")
		Class<BaseEntity<?>> entityType = (Class<BaseEntity<?>>) entity
				.getClass();
		try {
			Method method = entityType.getDeclaredMethod(getMethod(fieldName));
			return method.invoke(entity);
		} catch (SecurityException | IllegalArgumentException
				| NoSuchMethodException | IllegalAccessException
				| InvocationTargetException e) {
			throw new ApplicationException(e.getMessage(), e.getCause());
		}

	}

	private static String getMethod(String fieldName) {
		String getMethod = "get" + fieldName.substring(0, 1).toUpperCase()
				+ fieldName.substring(1);
		return getMethod;
	}

}
