package com.rqh.user.utils.dic;

import lombok.Getter;
import org.apache.commons.beanutils.PropertyUtilsBean;
import org.apache.logging.log4j.util.PropertiesUtil;
import org.springframework.cglib.beans.BeanGenerator;
import org.springframework.cglib.beans.BeanMap;

import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态Bean
 */
public class DynamicBean<T> {

    /**
     * 目标对象
     */
    @Getter
    private final T target;

    /**
     * 属性集合
     */
    private final BeanMap beanMap;

    public DynamicBean(T target, Map<String, Class<?>> propertyMap) {
        this.target = generateBean(target.getClass(), propertyMap);
        this.beanMap = BeanMap.create(this.target);
    }

    /**
     * bean 添加属性和值
     *
     * @param property 属性
     * @param value 值
     */
    public void setValue(String property, Object value) {
        beanMap.put(property, value);
    }

    /**
     * 获取属性值
     *
     * @param property 属性
     * @return 值
     */
    public Object getValue(String property) {
        return beanMap.get(property);
    }

    /**
     * 根据属性生成对象
     *
     * @param superClass 超类
     * @param propertyMap 属性集合
     * @return 对象
     */
    private T generateBean(Class<?> superClass, Map<String, Class<?>> propertyMap) {
        BeanGenerator generator = new BeanGenerator();
        if (superClass != null) {
            generator.setSuperclass(superClass);
        }
        BeanGenerator.addProperties(generator, propertyMap);
        return ((T) generator.create());
    }

    /**
     * 为实体添加属性
     *
     * @param target 要增加属性的对象
     * @param addProperties 要增加的属性名及值
     * @param discardOldValue 是否舍弃原有属性的值，如果为 true，则原有属性的值都将被设为默认值
     * @return
     * @param <T>
     */
    public static <T> T addProperties(T target, Map<String, ?> addProperties, boolean discardOldValue) {

        // 1. 获取原对象的字段数组
        PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
        PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(target);

        // 2.遍历原对象的字段数组，并将其封装到 Map
        Map<String, Class<?>> propertyMap = new HashMap<>(16);
        for (PropertyDescriptor d : descriptors) {
            if (!"class".equalsIgnoreCase(d.getName())) {
                propertyMap.put(d.getName(), d.getPropertyType());
            }
        }

        // 3. 将拓展字段 Map 合并到到原字段 Map 中
        addProperties.forEach((k, v) -> propertyMap.put(k, v.getClass()));

        // 4. 根据新的字段组合生成子类对象
        DynamicBean<T> dynamicBean = new DynamicBean<T>(target, propertyMap);

        // 5. 添加旧属性值
        if (!discardOldValue) {
            propertyMap.forEach((k, v) -> {
                try {
                    if (!addProperties.containsKey(k)) {
                        dynamicBean.setValue(k, propertyUtilsBean.getNestedProperty(target, k));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }

        // 6. 放回合并后的属性集合
        addProperties.forEach((k, v) -> {
            try {
                dynamicBean.setValue(k, v);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return dynamicBean.getTarget();
    }
}
