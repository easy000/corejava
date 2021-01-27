package com.easy.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author zhouym
 * @version [1.0, 2018/3/5]
 */
public class MideaCommonUtils {


    public static Map<?, ?> objectToMap(Object obj) {
        if (obj == null) {
            return new HashMap<Object,Object>();
        }
        return new org.apache.commons.beanutils.BeanMap(obj);
    }

    public static Map<String, Object> object2Map(Object obj) {
        Map<String, Object> map = new HashMap<String,Object>();
        Class<?> clazz = obj.getClass();
        System.out.println(clazz);
        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            String fieldName = field.getName();
            Object value = null;
            try {
                value = field.get(obj);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            map.put(fieldName, value);
        }
        return map;
    }

    //    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {
//        if (map == null) {
//            return null;
//        }
//        Object obj = beanClass.newInstance();
//
//        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
//        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
//        for (PropertyDescriptor property : propertyDescriptors) {
//            Method setter = property.getWriteMethod();
//            if (setter != null) {
//                setter.invoke(obj, map.get(property.getName()));
//            }
//        }
//
//        return obj;
//    }
    public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) {
        if (map == null) {
            return null;
        }

        Object obj = null;
        try {
            obj = beanClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        try {
            org.apache.commons.beanutils.BeanUtils.populate(obj, map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return obj;
    }

    public static List<Object> mapToObjectList(List<Map> mapList, Class<?> beanClass) throws Exception {
        if (CollectionUtils.isEmpty(mapList)) {
            return null;
        }
        List<Object> list = new ArrayList<Object>();
        for (Map<String, Object> map : mapList) {
            Object obj = mapToObject(map, beanClass);
            list.add(obj);
        }
        return list;
    }

    public static String createUniqueId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        int random = (int) ((Math.random() * 9 + 1) * 100000);
        return sdf.format(new Date()) + random;
    }

    public static String uniqueId() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        int random = (int) ((Math.random() * 9 + 1) * 1000);
        return sdf.format(new Date()) + random;
    }

    public static List<?> mideaRestResponseToDto(MideaRestResponse mideaRestResponse, Class dtoClass) {
        if (mideaRestResponse.isSuccess()) {
            Object resultData = mideaRestResponse.getResultData();
            if (resultData != null && !StringUtils.isEmpty(resultData.toString())) {
                return JSON.parseArray(resultData.toString(), dtoClass);
            }
        }
        return null;
    }


    public static Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName(clazzName);
        return clazz.newInstance();
    }

    public static Object praiseFiledTime(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //获取字段中包含fieldMeta的注解
            IsDateTimeType fieldAnno = field.getAnnotation((IsDateTimeType.class));
            if (fieldAnno != null && fieldAnno.isDateTime()) {
                try {
                    String str = (String) field.get(obj);
                    if (!StringUtils.isEmpty(str)) {
                        Date date = new Date(Long.valueOf(str));
                        str = formatDateToString(date, "yyyy-MM-dd HH:mm:ss");
                        field.set(obj, str);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
        return obj;
    }
    public static void praiseFiledTime(Object obj,Map map) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            //获取字段中包含fieldMeta的注解
            IsDateTimeType fieldAnno = field.getAnnotation((IsDateTimeType.class));
            if (fieldAnno != null && fieldAnno.isDateTime()) {
                try {
                    String str = (String) field.get(obj);
                    if (!StringUtils.isEmpty(str)) {
                        Date date = new Date(Long.valueOf(str));
                        str = formatDateToString(date, "yyyy-MM-dd HH:mm:ss");
                        map.put(field.getName(), str);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }
        }
    }
    public static String formatDateToString(Date date, String format) {
        if (date == null) {
            return "";
        }
        SimpleDateFormat dateFormat = new SimpleDateFormat(format);
        try {
            return dateFormat.format(date);
        } catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }

}