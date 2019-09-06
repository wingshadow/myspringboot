package com.myland.framework.utils.redis;

import com.myland.framework.utils.SerializeUtils;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Set;

/**
 * @description:
 * @author: zhb
 * @create: 2018/3/9 0009
 */
public class RedisCache<K, V> implements Cache<K, V> {
    private static final Logger log = LoggerFactory.getLogger(RedisCache.class);

    private String keyPrefix = "myland_shiro_redis_session:";

    private RedisManager cache;

    /**
     * 通过一个RedisManager实例构造RedisCache
     *
     * @param cache
     *
     */
    public RedisCache(RedisManager cache) {
        this.cache = cache;
    }

    /**
     * 通过一个RedisManager实例 和 key 标识 构造RedisCache
     *
     * @param cache
     * @param keyPrefix
     */
    public RedisCache(RedisManager cache, String keyPrefix) {
        this.cache = cache;
        this.keyPrefix = keyPrefix;
    }

    @Override
    public void clear() throws CacheException {
        log.debug("从redis中删除所有元素 ! ");
    }

    @Override
    public V get(K key) throws CacheException {

        if (key == null) {
            return null;
        }

        try {
            @SuppressWarnings("unchecked")
            V deserialize = (V) SerializeUtils.deserialize(cache.get(getByteKey(key)));
            //log.debug("从redis中取key :[{}] 的value {}", key, JSONObject.toJSONString(deserialize));
            return deserialize;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CacheException(e);
        }
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public V put(K key, V value) throws CacheException {

        //log.debug("存入 key : [{}] , value : [{}]", key, JSONObject.toJSONString(value));

        try {
            cache.set(getByteKey(key), SerializeUtils.serialize(value), cache.getTimout());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CacheException(e);
        }

        return value;
    }

    @Override
    public V remove(K key) throws CacheException {

        log.debug("从redis中删除 key [{}]", key);

        try {
            V previous = get(key);
            cache.del(getByteKey(key));
            return previous;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            throw new CacheException(e);
        }
    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Collection<V> values() {
        return null;
    }

    /**
     * 获得byte[]型的key
     *
     * @author chenem
     * @create 2017年2月8日 上午11:22:50
     * @param key
     * @return
     */
    private byte[] getByteKey(K key) {
        if (key instanceof String) {
            String preKey = this.keyPrefix + key;
            return preKey.getBytes();
        }

        return SerializeUtils.serialize(key);
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
