package com.myland.framework.utils.redis;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

import javax.annotation.Resource;

/**
 * redis操作基类
 * 
 * @author chenem (chenermin@anjia365.com)
 * @version 2.0.0 2015年1月8日 下午1:43:44 初始创建
 * @param <K> the Redis key type against which the template works (usually a String)
 * @param <V> the Redis value type against which the template works
 */
public abstract class AbstractBaseRedisDao<K, V> {

    @Resource
    protected RedisTemplate<K, V> redisTemplate;
    
    /**
     * <pre>
     * 获取 RedisSerializer
     *
     * 版本修改历史记录：
     * 1) 2014年11月27日 下午9:25:28 chenem 初始创建
     * </pre>
     *
     * @return
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }
    
    /**
     * <pre>
     * 返回连接
     *
     * 版本修改历史记录：
     * 1) 2014年11月29日 下午2:02:40 chenem 初始创建
     * </pre>
     *
     * @return redis连接
     */
    public RedisConnection getRedisConnection() {
        return redisTemplate.getConnectionFactory().getConnection();
    }
    
    public void setRedisTemplate(RedisTemplate<K, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }
    
}
