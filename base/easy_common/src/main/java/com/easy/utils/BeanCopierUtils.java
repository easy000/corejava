package com.xiong.core.utils;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jersey.repackaged.com.google.common.collect.Lists;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BeanCopierUtils {
	private static final Logger logger = LoggerFactory
			.getLogger(BeanCopier.class);
	private static final Map<String, BeanCopier> beanCopierMap = new HashMap();
	public static final String FORMAT_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

	public static void copyProperties(Object source, Object target) {
		copyProperties(source, target, false);
	}

	public static <T> T copyProperties(Object source, Class<T> targetClass) {
		return copyProperties(source, targetClass, false);
	}

	public static void copyProperties(Object source, Object target,
			boolean useConverter) {
		String beanKey = generateKey(source.getClass(), target.getClass());
		BeanCopier copier = null;
		if (!(beanCopierMap.containsKey(beanKey))) {
			copier = BeanCopier.create(source.getClass(), target.getClass(),
					useConverter);
			beanCopierMap.put(beanKey, copier);
		} else {
			copier = (BeanCopier) beanCopierMap.get(beanKey);
		}
		if (useConverter) {
			SimpleConverter converter = new SimpleConverter();
			copier.copy(source, target, converter);
		} else {
			copier.copy(source, target, null);
		}
	}

	public static <T> T copyProperties(Object source, Class<T> targetClass,
			boolean useConverter) {
		return copyProperties(source, targetClass, useConverter, null);
	}

	public static <T> T copyProperties(Object source, Class<T> targetClass,
			boolean useConverter, String dateFormate) {
		T target = null;
		try {
			target = targetClass.newInstance();

			String beanKey = generateKey(source.getClass(), targetClass);
			BeanCopier copier = null;
			if (!(beanCopierMap.containsKey(beanKey))) {
				copier = BeanCopier.create(source.getClass(), targetClass,
						useConverter);
				beanCopierMap.put(beanKey, copier);
			} else {
				copier = (BeanCopier) beanCopierMap.get(beanKey);
			}
			if (useConverter) {
				SimpleConverter converter = new SimpleConverter(dateFormate);
				copier.copy(source, target, converter);
			} else {
				copier.copy(source, target, null);
			}
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}

		return  target;
	}

	public static <T> T copyProperties(Object source, Class<T> targetClass,
			Converter useConverter) {
		T target = null;
		try {
			target = targetClass.newInstance();

			String beanKey = generateKey(source.getClass(), targetClass);
			BeanCopier copier = null;
			if (!(beanCopierMap.containsKey(beanKey))) {
				copier = BeanCopier
						.create(source.getClass(), targetClass, true);
				beanCopierMap.put(beanKey, copier);
			} else {
				copier = (BeanCopier) beanCopierMap.get(beanKey);
			}
			copier.copy(source, target, useConverter);
		} catch (InstantiationException e) {
			logger.error("", e);
		} catch (IllegalAccessException e) {
			logger.error("", e);
		}

		return target;
	}

	public static <T, E> List<T> copyList2List(List<E> froms, Class<T> to,
			boolean useConverter) {
		if ((null == froms) || (froms.size() == 0)) {
			return Lists.newArrayListWithCapacity(0);
		}
		List targetList = Lists.newArrayListWithCapacity(froms.size());
		for (Iterator i$ = froms.iterator(); i$.hasNext();) {
			Object item = i$.next();
			Object targetObj = copyProperties(item, to, useConverter);
			targetList.add(targetObj);
		}

		return targetList;
	}

	static class SimpleConverter implements Converter {
		private SimpleDateFormat sdf = null;

		public SimpleConverter() {
			this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		public SimpleConverter(String format) {
			if (StringUtils.isNotEmpty(format))
				this.sdf = new SimpleDateFormat(format);
			else
				this.sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}

		public Object convert(Object value, Class target, Object context) {
			try {
				if ((value == null)
						|| (target.equals(value.getClass()))
						|| (target.getName().equalsIgnoreCase(value.getClass()
								.getSimpleName()))) {
					return value;
				}

				if (value instanceof Integer) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}
					if (target.equals(Integer.TYPE)) {
						return Integer.valueOf(((Integer) value).intValue());
					}

					return value;
				}

				if (Integer.TYPE.equals(value.getClass())) {
					if (target.equals(String.class)) {
						return String.valueOf(value);
					}

					return value;
				}

				if (value instanceof String) {
					if ((target.equals(Integer.class))
							|| (target.equals(Integer.TYPE))) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Integer.valueOf(Integer
									.parseInt((String) value));
						}
						return null;
					}

					if ((target.equals(Long.class))
							|| (target.equals(Long.TYPE))) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return Long.valueOf((String) value);
						}
						return null;
					}

					if (target.equals(Date.class)) {
						if (StringUtils.isNotEmpty(value.toString())) {
							return this.sdf.parseObject(value.toString());
						}
						return null;
					}

					return value;
				}

				if (value instanceof Date) {
					if (target.equals(String.class)) {
						return this.sdf.format((Date) value);
					}

					if ((target.equals(Long.class))
							|| (target.equals(Long.TYPE))) {
						return Long.valueOf(((Date) value).getTime());
					}

					if ((target.equals(Integer.class))
							|| (target.equals(Integer.TYPE))) {
						return Integer.valueOf(new Long(((Date) value)
								.getTime()).intValue());
					}

					return value;
				}

				if (value.getClass().isArray()) {
					if (target.equals(List.class)) {
						List list = new ArrayList();
						for (Object obj : (Object[]) (Object[]) value) {
							list.add(BeanCopierUtils.copyProperties(obj,
									obj.getClass(), true));
						}
						return list;
					}

				} else if ((value instanceof List) && (target.isArray()))
					return ((List) value).toArray();
			} catch (Exception e) {
				BeanCopierUtils.logger.error(" Ù–‘∏¥÷∆“Ï≥£", e);
			}
			return null;
		}
	}
}