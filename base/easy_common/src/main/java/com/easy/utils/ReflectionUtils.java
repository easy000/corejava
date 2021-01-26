//package com.xiong.core.utils;
//
//import java.lang.reflect.Field;
//import java.lang.reflect.InvocationTargetException;
//import java.lang.reflect.Method;
//import java.lang.reflect.Modifier;
//import java.lang.reflect.ParameterizedType;
//import java.lang.reflect.Type;
//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.Date;
//import java.util.List;
//
//import org.apache.commons.beanutils.ConvertUtils;
//import org.apache.commons.beanutils.PropertyUtils;
//import org.apache.commons.beanutils.converters.DateConverter;
//import org.apache.commons.lang.StringUtils;
//
///**
// *
// * ����� Utils ��������
// * �ṩ����˽�б���, ��ȡ�������� Class, ��ȡ������Ԫ�����Ե� Utils ����
// *
// */
//public class ReflectionUtils {
//
//	/**
//	 * ������ʱ�� "����쳣" ת��Ϊ "����ʱ�쳣"
//	 * @return
//	 */
//	public static IllegalArgumentException convertToUncheckedException(Exception ex){
//		if(ex instanceof IllegalAccessException || ex instanceof IllegalArgumentException
//				|| ex instanceof NoSuchMethodException){
//			throw new IllegalArgumentException("�����쳣", ex);
//		}else{
//			throw new IllegalArgumentException(ex);
//		}
//	}
//
//	/**
//	 * ת���ַ������͵� toType ����, �� toType תΪ�ַ���
//	 * @param value:  ��ת�����ַ���
//	 * @param toType: �ṩ������Ϣ�� Class, �����ǻ����������͵İ�װ���ָ����ʽ������
//	 * @return
//	 */
//	public static Object convertValue(Object value, Class<?> toType){
//		try {
//			DateConverter dc = new DateConverter();
//
//			dc.setUseLocaleFormat(true);
//			dc.setPatterns(new String[]{"yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss"});
//
//			ConvertUtils.register(dc, Date.class);
//
//			return ConvertUtils.convert(value, toType);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw convertToUncheckedException(e);
//		}
//	}
//
//	/**
//	 * ��ȡ�����еĶ��������(ͨ�� getter ����), ��� List
//	 * @param collection: ��Դ����
//	 * @param propertyName: Ҫ��ȡ��������
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static List fetchElementPropertyToList(Collection collection, String propertyName){
//		List list = new ArrayList();
//
//		try {
//			for(Object obj: collection){
//				list.add(PropertyUtils.getProperty(obj, propertyName));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//			convertToUncheckedException(e);
//		}
//
//		return list;
//	}
//
//	/**
//	 * ��ȡ�����еĶ�������(ͨ�� getter ����), ��ϳ��ɷָ����ָ����ַ���
//	 * @param collection: ��Դ����
//	 * @param propertyName: Ҫ��ȡ��������
//	 * @param seperator: �ָ���
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static String fetchElementPropertyToString(Collection collection, String propertyName,
//			String seperator){
//		List list = fetchElementPropertyToList(collection, propertyName);
//		return StringUtils.join(list, seperator);
//	}
//
//	/**
//	 * ͨ������, ��ö��� Class ʱ�����ĸ���ķ��Ͳ���������
//	 * ��: public EmployeeDao extends BaseDao<Employee, String>
//	 * @param clazz
//	 * @param index
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static Class getSuperClassGenricType(Class clazz, int index){
//		Type genType = clazz.getGenericSuperclass();
//
//		if(!(genType instanceof ParameterizedType)){
//			return Object.class;
//		}
//
//		Type [] params = ((ParameterizedType)genType).getActualTypeArguments();
//
//		if(index >= params.length || index < 0){
//			return Object.class;
//		}
//
//		if(!(params[index] instanceof Class)){
//			return Object.class;
//		}
//
//		return (Class) params[index];
//	}
//
//	/**
//	 * ͨ������, ��� Class �����������ĸ���ķ��Ͳ�������
//	 * ��: public EmployeeDao extends BaseDao<Employee, String>
//	 * @param <T>
//	 * @param clazz
//	 * @return
//	 */
//	@SuppressWarnings("unchecked")
//	public static<T> Class<T> getSuperGenericType(Class clazz){
//		return getSuperClassGenricType(clazz, 0);
//	}
//
//	/**
//	 * ѭ������ת��, ��ȡ����� DeclaredMethod
//	 * @param object
//	 * @param methodName
//	 * @param parameterTypes
//	 * @return
//	 */
//	public static Method getDeclaredMethod(Object object, String methodName, Class<?>[] parameterTypes){
//
//		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
//			try {
//				//superClass.getMethod(methodName, parameterTypes);
//				return superClass.getDeclaredMethod(methodName, parameterTypes);
//			} catch (NoSuchMethodException e) {
//				//Method ���ڵ�ǰ�ඨ��, ��������ת��
//			}
//			//..
//		}
//
//		return null;
//	}
//
//	/**
//	 * ʹ filed ��Ϊ�ɷ���
//	 * @param field
//	 */
//	public static void makeAccessible(Field field){
//		if(!Modifier.isPublic(field.getModifiers())){
//			field.setAccessible(true);
//		}
//	}
//
//	/**
//	 * ѭ������ת��, ��ȡ����� DeclaredField
//	 * @param object
//	 * @param filedName
//	 * @return
//	 */
//	public static Field getDeclaredField(Object object, String filedName){
//
//		for(Class<?> superClass = object.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()){
//			try {
//				return superClass.getDeclaredField(filedName);
//			} catch (NoSuchFieldException e) {
//				//Field ���ڵ�ǰ�ඨ��, ��������ת��
//			}
//		}
//		return null;
//	}
//
//	/**
//	 * ֱ�ӵ��ö��󷽷�, ���������η�(private, protected)
//	 * @param object
//	 * @param methodName
//	 * @param parameterTypes
//	 * @param parameters
//	 * @return
//	 * @throws InvocationTargetException
//	 * @throws IllegalArgumentException
//	 */
//	public static Object invokeMethod(Object object, String methodName, Class<?> [] parameterTypes,
//			Object [] parameters) throws InvocationTargetException{
//
//		Method method = getDeclaredMethod(object, methodName, parameterTypes);
//
//		if(method == null){
//			throw new IllegalArgumentException("Could not find method [" + methodName + "] on target [" + object + "]");
//		}
//
//		method.setAccessible(true);
//
//		try {
//			return method.invoke(object, parameters);
//		} catch(IllegalAccessException e) {
//
//		}
//
//		return null;
//	}
//
//	/**
//	 * ֱ�����ö�������ֵ, ���� private/protected ���η�, Ҳ������ setter
//	 * @param object
//	 * @param fieldName
//	 * @param value
//	 */
//	public static void setFieldValue(Object object, String fieldName, Object value){
//		Field field = getDeclaredField(object, fieldName);
//
//		if (field == null)
//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
//
//		makeAccessible(field);
//
//		try {
//			field.set(object, value);
//		} catch (IllegalAccessException e) {
//
//		}
//	}
//
//	/**
//	 * ֱ�Ӷ�ȡ���������ֵ, ���� private/protected ���η�, Ҳ������ getter
//	 * @param object
//	 * @param fieldName
//	 * @return
//	 */
//	public static Object getFieldValue(Object object, String fieldName){
//		Field field = getDeclaredField(object, fieldName);
//
//		if (field == null)
//			throw new IllegalArgumentException("Could not find field [" + fieldName + "] on target [" + object + "]");
//
//		makeAccessible(field);
//
//		Object result = null;
//
//		try {
//			result = field.get(object);
//		} catch (IllegalAccessException e) {
//
//		}
//
//		return result;
//	}
//}
