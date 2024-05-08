package com.rqh.user.utils.dic;

import java.lang.annotation.*;

/**
 * 字典注解
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DicTranslate {

    /**
     * 需要转换的枚举对象
     */
    Class<?> enumClass();

    /**
     * 从哪个字段匹配
     */
    String from() default BaseEnum.KEY;

    /**
     * 获取哪个字段的值
     */
    String to() default BaseEnum.VALUE;

    /**
     * 字段值的类
     */
    Class<?> returnType() default String.class;

    /**
     * 值为null时的默认值
     */
    String defaultValue() default "";
}
