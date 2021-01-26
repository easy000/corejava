//package com.xiong.core.utils;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.Field;
//import java.lang.reflect.Method;
//
//import org.apache.log4j.Logger;
//import org.apache.log4j.spi.LoggerFactory;
//
///**
// * ���乤����
// *
// * @Author:zhangguangliang
// * @Time:2017��9��16��14:44:10
// */
//public class ReflectionUtil extends ReflectionUtils {
//
//    private static Logger logger = LoggerFactory.getLogger(ReflectionUtil.class);
//
//    /**
//     * ��ȡobj����fieldName��Field
//     *
//     * @param obj
//     * @param fieldName
//     * @return
//     */
//    public static Field getFieldByFieldName(Object obj, String fieldName) {
//        if (obj == null || fieldName == null) {
//            return null;
//        }
//        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
//            try {
//                return superClass.getDeclaredField(fieldName);
//            } catch (Exception e) {
//
//            }
//        }
//        return null;
//    }
//
//    /**
//     * ��ȡobj����fieldName������ֵ
//     *
//     * @param obj
//     * @param fieldName
//     * @return
//     */
//    public static Object getValueByFieldName(Object obj, String fieldName) {
//        Object value = null;
//        try {
//            Field field = getFieldByFieldName(obj, fieldName);
//            if (field != null) {
//                if (field.isAccessible()) {
//                    value = field.get(obj);
//                } else {
//                    field.setAccessible(true);
//                    value = field.get(obj);
//                    field.setAccessible(false);
//                }
//            }
//        } catch (Exception e) {
//            logger.error("�����������ƻ�ȡ����ֵ�쳣��");
//        }
//        return value;
//    }
//
//    /**
//     * ��ȡobj����fieldName������ֵ
//     *
//     * @param obj
//     * @param fieldName
//     * @return
//     */
//    @SuppressWarnings("unchecked")
//    public static <T> T getValueByFieldType(Object obj, Class<T> fieldType) {
//        Object value = null;
//        for (Class<?> superClass = obj.getClass(); superClass != Object.class; superClass = superClass.getSuperclass()) {
//            try {
//                Field[] fields = superClass.getDeclaredFields();
//                for (Field f : fields) {
//                    if (f.getType() == fieldType) {
//                        if (f.isAccessible()) {
//                            value = f.get(obj);
//                            break;
//                        } else {
//                            f.setAccessible(true);
//                            value = f.get(obj);
//                            f.setAccessible(false);
//                            break;
//                        }
//                    }
//                }
//                if (value != null) {
//                    break;
//                }
//            } catch (Exception e) {
//                 logger.error("�����������ͻ�ȡ����ֵ�쳣��");
//            }
//        }
//        return (T) value;
//    }
//
//    /**
//     * ����obj����fieldName������ֵ
//     *
//     * @param obj
//     * @param fieldName
//     * @param value
//     * @throws SecurityException
//     * @throws NoSuchFieldException
//     * @throws IllegalArgumentException
//     * @throws IllegalAccessException
//     */
//    public static boolean setValueByFieldName(Object obj, String fieldName, Object value) {
//        try {
//            // java.lang.Class.getDeclaredField()�����÷�ʵ���̳� -
//            // ��������һ��Field��������ӳ��Class��������ʾ�����ӿڵ�ָ���������ֶΡ�
//            // �˷�������������е�ָ���ֶε�Field����
//            Field field = obj.getClass().getDeclaredField(fieldName);
//            /**
//             * public void setAccessible(boolean flag) throws SecurityException���˶���� accessible ��־����Ϊָʾ�Ĳ���ֵ��ֵΪ true ��ָʾ����Ķ�����ʹ��ʱӦ��ȡ�� Java ���Է��ʼ�顣ֵΪ false ��ָʾ����Ķ���Ӧ��ʵʩ Java ���Է��ʼ�顣 ���ȣ�������ڰ�ȫ������������
//             * ReflectPermission("suppressAccessChecks") Ȩ���µ��� checkPermission ������ ��� flag Ϊ true�����Ҳ��ܸ��Ĵ˶���Ŀɷ����ԣ����磬�����Ԫ�ض����� Class ��� Constructor ���󣩣�������� SecurityException�� ����˶����� java.lang.Class ��� Constructor ���󣬲���
//             * flag Ϊ true��������� SecurityException�� ������ flag - accessible ��־����ֵ �׳��� SecurityException - ������󱻾ܾ���
//             */
//            if (field.isAccessible()) {// ��ȡ�˶���� accessible ��־��ֵ��
//                field.set(obj, value);// ��ָ����������ϴ� Field �����ʾ���ֶ�����Ϊָ������ֵ
//            } else {
//                field.setAccessible(true);
//                field.set(obj, value);
//                field.setAccessible(false);
//            }
//            return true;
//        } catch (Exception e) {
//            logger.error("��������������������ֵ�쳣��");
//        }
//        return false;
//    }
//
//    /**
//     * ִ��ĳ����ķ���
//     *
//     * @param owner
//     * @param methodName
//     * @param args
//     * @return
//     * @throws Exception
//     */
//    public Object invokeMethod(Object owner, String methodName, Object[] args) throws Exception {
//        Class<? extends Object> cls = owner.getClass();
//        @SuppressWarnings("rawtypes")
//        Class[] argclass = new Class[args.length];
//        for (int i = 0, j = argclass.length; i < j; i++) {
//            argclass[i] = args[i].getClass();
//        }
//        Method method = cls.getMethod(methodName, argclass);
//        return method.invoke(owner, args);
//    }
//
//    /**
//     * ִ�о�̬��ķ���
//     *
//     * @param className
//     * @param methodName
//     * @param args
//     * @return
//     * @throws Exception
//     */
//    public Object invokeStaticMethod(String className, String methodName, Object[] args) throws Exception {
//        Class<?> cls = Class.forName(className);
//        @SuppressWarnings("rawtypes")
//        Class[] argclass = new Class[args.length];
//        for (int i = 0, j = argclass.length; i < j; i++) {
//            argclass[i] = args[i].getClass();
//        }
//        Method method = cls.getMethod(methodName, argclass);
//        return method.invoke(null, args);
//    }
//
//    public Object newInstance(String className, Object[] args) throws Exception {
//        Class<?> clss = Class.forName(className);
//        @SuppressWarnings("rawtypes")
//        Class[] argclass = new Class[args.length];
//        for (int i = 0, j = argclass.length; i < j; i++) {
//            argclass[i] = args[i].getClass();
//        }
//        Constructor<?> cons = clss.getConstructor(argclass);
//        return cons.newInstance();
//    }
//
//}