package com.myland.framework.utils.beancopier;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.core.Converter;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.util.Date;

/**
 * <pre>
 * 基于BeanCopier实现Bean对象复制。
 * 实现自定义的Field数据类型转换，用于将表单数据对象中的各元素赋值给指定的DO对象。
 *
 * 其中，表单对象中数据来源于request请求中的数据，程序中全部定义为String类型。
 * DO对象用于service调用和数据库交互，数据类型不固定（String、Long、Integer、Date等）
 * </pre>
 *
 * @author FengChangshuo
 * @version 1.0.0 2018-03-14 17:14:17 初始创建
 *
 * @see org.springframework.cglib.beans.BeanCopier
 */
public class FormConverter implements Converter {

    private static final Logger log = LoggerFactory.getLogger(FormConverter.class);

    private static final String DEFAULT_DATE_STYLE = "yyyy-MM-dd HH:mm:ss";

    private Class<?> destClass;

    public FormConverter() {

    }

    public FormConverter(Class<?> destClass) {
        this.destClass = destClass;
    }

    /**
     * @param srcValue 源对象属性值
     * @param destSetterMethodName 目标对象setter方法名
     */
    @SuppressWarnings("rawtypes")
    @Override
    public Object convert(Object srcValue, Class destField, Object destSetterMethodName) {
        if (null == srcValue || "".equals(srcValue)) {
            return null;
        }

        if (String.class.isAssignableFrom(destField)) {
            return srcValue;
        }

        String src = String.valueOf(srcValue);

        if (Long.class.isAssignableFrom(destField)) {
            return Long.valueOf(src);
        }

        if (Integer.class.isAssignableFrom(destField)) {
            return Integer.valueOf(src);
        }

        if (Double.class.isAssignableFrom(destField)) {
            return Double.valueOf(src);
        }

        if (long.class.isAssignableFrom(destField)) {
            return Long.parseLong(src);
        }

        if (int.class.isAssignableFrom(destField)) {
            return Integer.parseInt(src);
        }

        if (double.class.isAssignableFrom(destField)) {
            return Double.parseDouble(src);
        }

        if (Date.class.isAssignableFrom(destField)) {

            try {
                String style = getDateFomatterStyle(String.valueOf(destSetterMethodName));
                return DateUtils.parseDate(src, style);
            } catch (ParseException e) {
//                throw new YtbsException(e);
            }
        }

        return srcValue;
    }

    private String getDateFomatterStyle(String setterMethodName) {

        if (destClass == null) {
            return DEFAULT_DATE_STYLE;
        }

        String fieldName = String.valueOf(setterMethodName).substring(3);
        fieldName = fieldName.substring(0, 1).toLowerCase() + fieldName.substring(1);

        Field field = ReflectionUtils.findField(destClass, fieldName);
        if (null == field) {
            log.debug("Class : {} not found Field {}, try to find date style from method : {}",
                    destClass.getClass().getName(), fieldName, setterMethodName);
            return getDateFomatterStyleFromSetterMethod(setterMethodName);
        }

        DateTimeFormat dateTimeFormat = field.getAnnotation(DateTimeFormat.class);
        if (dateTimeFormat != null) {
            return dateTimeFormat.style();
        }

        log.debug("Class : {} Field {} has no @DateTimeFormat annotation, try to find date style from method : {}",
                destClass.getClass().getName(), fieldName, setterMethodName);
        return getDateFomatterStyleFromSetterMethod(setterMethodName);
    }

    private String getDateFomatterStyleFromSetterMethod(String setterMethodName) {
        Method method = ReflectionUtils.findMethod(destClass, setterMethodName, Date.class);

        DateTimeFormat dateTimeFormat = method.getAnnotation(DateTimeFormat.class);
        if (dateTimeFormat != null) {
            return dateTimeFormat.style();
        }

        log.debug("Class : {} will use default date formatter : yyyy-MM-dd HH:mm:ss", destClass.getClass().getName());
        return DEFAULT_DATE_STYLE;
    }

}
