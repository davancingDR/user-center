package com.rqh.user.utils.dic;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 字段转换工具类
 */
public class DicTranslateUtil {

    /**
     * 类对象的缓存
     */
    private static final Map<String, Class<?>> CLASS_CACHE_MAP = new ConcurrentHashMap<>();

    public static final String DIC = "Dic";

    private DicTranslateUtil() {
    }

    /**
     * 添加字典属性
     *
     * @param target
     * @return
     * @param <T>
     */
    public static <T> T translate(T target) {
        Map<String, Dic> dicMap =DicTranslateUtil.getTranslateMap(target);
        return DynamicBean.addProperties(target, dicMap, false);
    }

    /**
     * 转换方法
     *
     * @param source
     * @return
     * @param <S>
     */
    public static <S> Map<String, Dic> getTranslateMap(S source) {
        Map<String, Dic> dicMap = new HashMap<>(16);
        if (source == null) {
            return dicMap;
        }
        Class<?> chacheClass = getCacheClass(source.toString());
        if (chacheClass == null){
            chacheClass = source.getClass();
            putCacheClass(source.toString(), chacheClass);
        }
        for (Field field : chacheClass.getDeclaredFields()) {
            try {
                // 获取注解的信息
                DicTranslate annotation = field.getAnnotation(DicTranslate.class);
                if (annotation != null){
                   field.setAccessible(true);
                   // 通过反射的字段获取实体中的值
                   Object value = field.get(source);
                   Dic dic = new Dic();
                   dic.setKey(value.toString());
                   if (value != null) {
                       Object valueResult = getValue(annotation.enumClass(), value.toString(),
                               annotation.from(), annotation.to(), annotation.returnType(), annotation.defaultValue());
                       dic.setValue(valueResult.toString());
                   } else {
                       dic.setValue(annotation.defaultValue());
                   }
                   dicMap.put(field.getName() + DIC, dic);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return dicMap;
    }

    /**
     * 枚举类转 List 对象
     *
     * @param enumClazz
     * @return
     * @param <T>
     */
    public static <T> List<Map<String, Object>> toList(Class<T> enumClazz) {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try {
            // 获取方法
            Method[] methods = enumClazz.getDeclaredMethods();
            Field[] fields = enumClazz.getDeclaredFields();
            List<Field> fieldList = new ArrayList<>();
            for (Method method : methods) {
                for (Field field : fields) {
                    if (method.getName().endsWith(toUpperCaseFirstOne(field.getName()))) {
                        fieldList.add(field);
                    }
                }
            }
            // 获取值
            T[] enums = enumClazz.getEnumConstants();
            for (T e : enums) {
                Map<String, Object> eMap = new HashMap<>(8);
                for (Field field : fieldList) {
                    field.setAccessible(true);
                    eMap.put(field.getName(), field.get(e));
                }
                resultList.add(eMap);
            }
        } catch (Exception ignored){
        }
        return resultList;
    }

    public static <T, R> R getValue(Class<T> enumClazz, String input, String from,
                                    String to, Class<R> resultClazz,  Object defaultValue) {
        Object result = null;
        List<Map<String, Object>> maps = toList(enumClazz);
        for (Map<String, Object> map : maps) {
            if (StringUtils.equals(String.valueOf(map.get(from)), input)) {
                result = map.get(to);
                break;
            }
        }
        return result == null ? resultClazz.cast(defaultValue): resultClazz.cast(result);
    }

    public static <T, R> R getValue(Class<T> enumClazz, String input, String from, String to,Class<R> resultClazz) {
        return getValue(enumClazz, input, from, to, resultClazz, null);
    }

    public static <T> String getValue(Class<T> enumClazz, String input, String from, String to) {
        return getValue(enumClazz, input, from, to, String.class, null);
    }

    public static <T> String getValue(Class<T> enumClazz, String input) {
        return getValue(enumClazz, input, BaseEnum.KEY, BaseEnum.VALUE, String.class, null);
    }

    /**
     * 首字母转大写
     * @param s 需要操作的字符串
     * @return 转换后结果
     */
    private static String toUpperCaseFirstOne(String s) {
        if (Character.isUpperCase(s.charAt(0))) {
            return s;
        } else {
            return Character.toUpperCase(s.charAt(0)) + s.substring(1);
        }
    }

    /**
     * 获取类对象缓存
     *
     * @param name
     * @return
     */
    private static Class<?> getCacheClass(String name) {
        return CLASS_CACHE_MAP.get(name);
    }

    /**
     * 存放类对象缓存
     *
     * @param name
     * @param clazz
     */
    private static void putCacheClass(String name, Class<?> clazz) {
        CLASS_CACHE_MAP.put(name, clazz);
    }
}
