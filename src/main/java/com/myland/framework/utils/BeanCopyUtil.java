package com.myland.framework.utils;

import com.myland.framework.utils.beancopier.FormConverter;
import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Bean对象复制工具类
 * 基于Spring的BeanCopier实现Bean对象复制。号称性能较高。
 * 如果是普通的性能要求不高的Bean对象复制，也可以考虑使用Spring的BeanUtils工具实现
 * 
 * @author FengChangshuo
 * @version 1.0.0 2018-03-29 17:04:52 初始创建
 * 
 * @see org.springframework.cglib.beans.BeanCopier
 * @see org.springframework.beans.BeanUtils#copyProperties(Object, Object)
 */
public class BeanCopyUtil {
    
    /**
     * BeanCopier缓存
     */
    private static final Map<String, BeanCopier> BEAN_COPIERS = new ConcurrentHashMap<>();
    
    private static final Map<String, Converter> FORM_CONVERTERS = new ConcurrentHashMap<>();
    
    private BeanCopyUtil() {
    }
    
    /**
     * <pre>
     * Bean数据对象复制。
     * 
     * 该方法适用于源Bean对象与目标Bean对象各属性类型一致的场景，不需要对某属性进行类型转换处理。
     * 
     * 版本修改历史记录：
     * 1) 1.0.0 2018-03-29 17:07:49 FengChangshuo 初始创建
     * </pre>
     * 
     * @param srcObj 复制源。
     * @param destObj 复制目标。
     */
    public static void copy(Object srcObj, Object destObj) {
        copy(srcObj, destObj, null);
    }
    
    /**
     * <pre>
     * Bean表单数据对象复制。
     * 从表单数据对象中将各数据元素复制到DTO对象中，并进行数据类型的转换。
     * 
     * 版本修改历史记录：
     * 1) 1.0.0 2018-03-29 17:08:07 FengChangshuo 初始创建
     * </pre>
     * 
     * @param srcObj 复制源。表单数据对象，该Form对象中数据由调用方传入，基于HTTP传输，Form元素默认全为String类型。
     * @param destObj 复制目标。传入Service层或Dao层的数据对象，数据类型较为多样化。
     */
    public static void copyFormObject(Object srcObj, Object destObj) {
        String key = genFormConverterKey(destObj.getClass());
        
        Converter converter;
        if (!FORM_CONVERTERS.containsKey(key)) {
            converter = new FormConverter(destObj.getClass());
            FORM_CONVERTERS.put(key, converter);
        } else {
            converter = FORM_CONVERTERS.get(key);
        }
        
        copy(srcObj, destObj, converter);
    }
    
    /**
     * <pre>
     * Bean数据对象复制。
     * 
     * 该方法适用于源Bean对象与目标Bean对象各属性类型不完全一致的场景，需要通过实现Converter接口对某属性进行类型转换处理。
     * 
     * 版本修改历史记录：
     * 1) 1.0.0 2018-03-29 17:08:25 FengChangshuo 初始创建
     * </pre>
     * 
     * @param srcObj 复制源
     * @param destObj 复制目标
     * @param converter 类型转换器实现类
     */
    public static void copy(Object srcObj, Object destObj, Converter converter) {
        boolean useConverter = (converter != null);
        
        String key = genBeanCopierKey(srcObj.getClass(), destObj.getClass(), useConverter);
        
        BeanCopier copier;
        if (!BEAN_COPIERS.containsKey(key)) {
            copier = BeanCopier.create(srcObj.getClass(), destObj.getClass(), useConverter);
            BEAN_COPIERS.put(key, copier);
        } else {
            copier = BEAN_COPIERS.get(key);
        }
        
        copier.copy(srcObj, destObj, converter);
    }
    
    private static String genBeanCopierKey(Class<?> srcClazz, Class<?> destClazz, boolean useConverter) {
        return srcClazz.getName() + destClazz.getName() + useConverter;
    }
    
    private static String genFormConverterKey(Class<?> destClazz) {
        return destClazz.getName();
    }
    
}
