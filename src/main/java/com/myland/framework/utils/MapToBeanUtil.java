package com.myland.framework.utils;

import com.myland.framework.base.BaseBean;
import com.myland.framework.utils.collection.CollectionUtil;
import org.springframework.cglib.beans.BeanMap;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * @author ZHANGBIN
 * @version 1.0
 * @Description Map to VO
 * @Date 2018/11/13 9:51
 **/
public class MapToBeanUtil {

    private MapToBeanUtil() {
    }
    
    @SuppressWarnings("unchecked")
    public static Map<String, Object> toMap(Object obj) {
        Map<String, Object> map = new HashMap<>();
        if (obj == null) {
            return map;
        }

        if (obj instanceof HashMap) {
            map.putAll((Map<String, Object>) obj);
        }

        BeanMap beanMap = BeanMap.create(obj);
        for (Object keyObj : beanMap.keySet()) {
            String key = String.valueOf(keyObj);
            Object value = beanMap.get(key);
            map.put(key, value);
        }
        return map;
    }

    /**
     * BigInteger、BigDecimal类型转换
     * @param beanMap
     * @return
     */
    public static Map<String, Object> mapConvert(Map<String, Object> beanMap){
        Iterator<Map.Entry<String, Object>> it = beanMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, Object> e = it.next();
            String key = e.getKey();
            Object value = e.getValue();
            if (value instanceof BigInteger) {
                beanMap.put(key, ((BigInteger) value).longValue());
            }
            if(value instanceof BigDecimal){
                beanMap.put(key, ((BigDecimal) value).doubleValue());
            }
        }
        return beanMap;
    }

    public static <T extends BaseBean> T toBean(Map<String, Object> beanMap, T bean) {
        BeanMap tempMap = BeanMap.create(bean);
        tempMap.putAll(mapConvert(beanMap));
        return bean;
    }


    public static <T extends BaseBean> T toBean(Map<String, Object> beanMap, Class<T> beanClasses)
            throws InstantiationException, IllegalAccessException {
        T bean = beanClasses.newInstance();
        BeanMap tempMap = BeanMap.create(bean);
        tempMap.putAll(mapConvert(beanMap));
        return bean;

    }

    public static <T extends BaseBean> List<T> toList(List<Map<String, Object>> list, Class<T> beanClasses)
            throws InstantiationException, IllegalAccessException {
        if (CollectionUtil.isNotEmpty(list)) {
            List<T> beanlist = new ArrayList<>();
            for (Map<String, Object> map : list) {
                beanlist.add(toBean(map, beanClasses));
            }
            return beanlist;
        }
        return Collections.emptyList();
    }

}
