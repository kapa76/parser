package org.parser.persistence.repository.hibernate;


import java.io.UnsupportedEncodingException;
import java.util.*;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.type.BasicType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTemplate;

public abstract class HibernateRepositoryBase {

	private static final String CHARSET_NAME = "UTF-8";

	private static final LinkedHashMap<String, Object> EMPTY_PARAMETERS_MAP = new LinkedHashMap<>(0);

	@Autowired
	private HibernateTemplate hibernateTemplate;

	protected HibernateTemplate getHibernateTemplate() {
		return hibernateTemplate;
	}

	protected void put(LinkedHashMap<String, Object> parameters, String name, Object value, BasicType type) {
		parameters.put(name, value == null ? type : value);
	}

	protected SQLQuery createSQLQuery(Session session, String sql, LinkedHashMap<String, Object> parameters) {
		final SQLQuery query = session.createSQLQuery(sql);
		bindParameters(query, parameters);
		return query;
	}

	protected SQLQuery createSQLQuery(Session session, String sql) {
		return createSQLQuery(session, sql, EMPTY_PARAMETERS_MAP);
	}

	protected Query createQuery(Session session, String sql, LinkedHashMap<String, Object> parameters) {
		final Query query = session.createQuery(sql);
		bindParameters(query, parameters);
		return query;
	}

	protected Query createQuery(Session session, String sql) {
		return createQuery(session, sql, EMPTY_PARAMETERS_MAP);
	}

	private void bindParameters(Query query, LinkedHashMap<String, Object> parameters) {
		for (Map.Entry<String, Object> entry : parameters.entrySet()) {
			final String name = entry.getKey();
			final Object value = entry.getValue();
			if (value instanceof Collection) {
				query.setParameterList(name, (Collection) value);
			} else if (value instanceof BasicType) {
				query.setParameter(name, null, ((BasicType) value));
			} else {
				query.setParameter(name, value);
			}
		}
	}

	protected String inSqlLong(Collection<Long> values, String paramNamePrefix, LinkedHashMap<String, Object> params) {
		return inSql(Arrays.asList(values.toArray(new Object[values.size()])), paramNamePrefix, params);
	}

    protected String inSqlShort(Collection<Short> values, String paramNamePrefix, LinkedHashMap<String, Object> params) {
		return inSql(Arrays.asList(values.toArray(new Object[values.size()])), paramNamePrefix, params);
	}

	protected String inSql(Collection<Object> values, String paramNamePrefix, LinkedHashMap<String, Object> params) {
		int i = 1;
		StringBuilder result = new StringBuilder("(");
		for (Object v : values) {
			if (i > 1) {
				result.append(",");
			}
			String name = paramNamePrefix + i;
			result.append(":").append(name);
			params.put(name, v);
			i++;
		}
		result.append(")");
		return result.toString();
	}

	@SuppressWarnings("unchecked")
	protected static <T> List<T> cast(List list) {
		return (List<T>) list;
	}

	public static String convert(byte[] bytes) {
		try {
			return bytes == null ? null : new String(bytes, CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}

	public static byte[] convert(String string) {
		try {
			return string == null ? null : string.getBytes(CHARSET_NAME);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException();
		}
	}
}
