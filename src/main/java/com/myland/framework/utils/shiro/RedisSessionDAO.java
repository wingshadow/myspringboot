package com.myland.framework.utils.shiro;

import com.myland.framework.utils.redis.RedisManager;
import com.myland.framework.utils.SerializeUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Collection;

/**
 * @description: Shiro管理Session
 * @author: zhb
 * @create: 2018/3/9 0009
 */
public class RedisSessionDAO extends EnterpriseCacheSessionDAO {

    private static Logger logger = LoggerFactory.getLogger(RedisSessionDAO.class);
    private RedisManager redisManager;
    private String keyPrefix = "myland_shiro_redis_session:";

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);

        //redisManager.set(sessionId.toString().getBytes(), SerializeUtils.serialize(session),redisManager.getTimout());
        byte[] key = getByteKey(session.getId());
        byte[] value = SerializeUtils.serialize(session);
        session.setTimeout(redisManager.getTimout()*1000);
        this.redisManager.set(key, value, redisManager.getTimout());

        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        if (sessionId == null) {
            logger.error("session id is null");
            return null;
        } else {
            Session s = (Session) SerializeUtils.deserialize(this.redisManager.get(this.getByteKey(sessionId)));
            return s;
        }
    }

    @Override
    public void update(Session session) throws UnknownSessionException {
        super.update(session);
    }

    @Override
    public void delete(Session session) {
        if(session == null || session.getId() == null){
            logger.error("session or session id is null");
            return;
        }
        super.doDelete(session);
        redisManager.del(this.getByteKey(session.getId()));
    }

    @Override
    public Collection<Session> getActiveSessions() {
//        Set<Session> sessions = new HashSet<Session>();
//        Set<byte[]> keys = redisManager.keys(this.keyPrefix + "*");
//        if(keys != null && keys.size()>0){
//            for(byte[] key:keys){
//                Session s = (Session)SerializeUtils.deserialize(redisManager.get(key));
//                sessions.add(s);
//            }
//        }
//        return sessions;
        return null;
    }

    private byte[] getByteKey(Serializable sessionId) {
        String preKey = this.keyPrefix + sessionId;
        return preKey.getBytes();
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public String getKeyPrefix() {
        return keyPrefix;
    }

    public void setKeyPrefix(String keyPrefix) {
        this.keyPrefix = keyPrefix;
    }
}
