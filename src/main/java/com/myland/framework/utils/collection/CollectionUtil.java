package com.myland.framework.utils.collection;

import java.util.Collection;
import java.util.Map;

/**
 * 集合工具类
 * 
 * @author HyouSeki
 * @version 1.0.0 2018-12-25 15:14:48 初始创建
 */
public final class CollectionUtil {
    
    private CollectionUtil() {
    }
    
    /**
     * Return {@code true} if the supplied Collection is {@code null} or empty.
     * Otherwise, return {@code false}.
     * 
     * @param <T> 集合元素类型
     * @param collection the Collection to check
     * @return whether the given Collection is empty
     */
    public static <T> boolean isEmpty(Collection<T> collection) {
        return collection == null || collection.isEmpty();
    }
    
    /**
     * Return {@code true} if the supplied Collection is not {@code null} and empty.
     * Otherwise, return {@code false}.
     * 
     * @param <T> 集合元素类型
     * @param collection the Collection to check
     * @return whether the given Collection is not empty
     */
    public static <T> boolean isNotEmpty(Collection<T> collection) {
        return !isEmpty(collection);
    }
    
    /**
     * Return {@code true} if the supplied Map is {@code null} or empty.
     * Otherwise, return {@code false}.
     * 
     * @param <K> Key元素类型
     * @param <V> 集合元素类型
     * @param map the Map to check
     * @return whether the given Map is empty
     */
    public static <K, V> boolean isEmpty(Map<K, V> map) {
        return map == null || map.isEmpty();
    }
    
    /**
     * Return {@code true} if the supplied Map is not {@code null} and empty.
     * Otherwise, return {@code false}.
     * 
     * @param <K> Key元素类型
     * @param <V> 集合元素类型
     * @param map the Map to check
     * @return whether the given Map is not empty
     */
    public static <K, V> boolean isNotEmpty(Map<K, V> map) {
        return !isEmpty(map);
    }
    
}
