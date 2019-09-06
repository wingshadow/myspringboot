package com.myland.framework.utils.redis;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;

import java.util.concurrent.TimeUnit;

/**
 * @author chenem
 * @email chenermin@anjia365.com
 * @create 2017年2月8日
 */
public class RedisManager extends AbstractBaseRedisDao<Object, Object> {

    private long timout;

    /**
     * 向redis中存入信息
     *
     * @param key
     * @param value
     * @author chenem
     * @create 2017年2月8日 下午3:30:50
     */
    public void set(byte[] key, byte[] value,long expire) {

        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.set(key, value);
                connection.expire(key,expire);
                return null;
            }
        });
    }

    /**
     * 删除redis中信息
     *
     * @param key
     * @author chenem
     * @create 2017年2月8日 下午3:43:58
     */
    public void del(byte[] key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key);
                return null;
            }
        });
    }

    /**
     * 取redis中的值
     *
     * @param key
     * @return
     * @author chenem
     * @create 2017年2月9日 上午10:15:49
     */
    public byte[] get(byte[] key) {
        return redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(key);
            }
        });
    }

    public void put(String key, String hashKey, String value, int hour, TimeUnit unit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, hour, unit);
    }

    public void put(String key, int hashKey, String value, int hour, TimeUnit unit) {
        redisTemplate.opsForHash().put(key, hashKey, value);
        redisTemplate.expire(key, hour, unit);
    }

    public void put(String key, int hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void put(String key, String hashKey, String value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public void set(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public String get(String key) {
        return (String) redisTemplate.opsForValue().get(key);
    }

    public String getHash(String key, String hashKey) {
        return (String) redisTemplate.opsForHash().get(key, hashKey);
    }

    public String getHash(String key, int hashKey) {
        return (String) redisTemplate.opsForHash().get(key, String.valueOf(hashKey));
    }

    public void removeHash(String key, String hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public void removeHash(String key, int hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public long getTimout() {
        return timout;
    }

    public void setTimout(long timout) {
        this.timout = timout;
    }
}
