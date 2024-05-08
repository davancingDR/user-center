package com.rqh.user.utils;

import com.rqh.user.utils.dic.DicTranslateUtil;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 拷贝工具类
 *
 * 1、属性名称、类型都相同的字段，拷贝成功
 * 2、属性名称相同、类型不同的字段，拷贝失败，基础类型和包装类型视为不同类型
 * 3、setter、getter 要一一对应，否则出现拷贝丢失
 *
 */
public class CopyUtils {
    private static final Map<String, BeanCopier> CACHE_COPIER_MAP = new ConcurrentHashMap<>();

    private static final Converter DEFAULT_CONVERTER = null;

    /**
     * 构造器私有化
     */
    private CopyUtils() {
    }

    public static <S, T> T copyObject(S source, T target, boolean dicTranslate, Converter converter) {
        if (source == null || target == null){
            return null;
        }
        BeanCopier bc = getBeanCopierInstance(source.getClass(), target.getClass(), converter);
        bc.copy(source, target, converter);
        if (dicTranslate) {
            return DicTranslateUtil.translate(target);
        }
        return target;
    }
}
