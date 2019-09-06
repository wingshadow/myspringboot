package com.myland.framework.utils;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 序列化工具类
 * 
 * @author chenem chenermin@anjia365.com
 * @version 1.0.0 2017年2月8日
 */
public class SerializeUtils {
    
    private static final Logger log = LoggerFactory.getLogger(SerializeUtils.class);
    
    /**
     * 将对象序列化
     * 
     * @author chenem
     * @create 2017年2月8日 上午11:31:53
     * @param value
     * @return
     */
    public static byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv = null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            close(os);
            close(bos);
        }
        return rv;
    }
    
    /**
     * 反序列化
     * 
     * @author chenem
     * @create 2017年2月8日 上午11:34:48
     * @param in
     * @param clazz
     * @return
     */
    public static <E> E deserialize(byte[] in, Class<E> clazz) {
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                Object rv = is.readObject();
                E ro = clazz.cast(rv);
                is.close();
                bis.close();
                return ro;
            }
            return null;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            close(is);
            close(bis);
        }
    }
    
    /**
     * 反序列化
     * 
     * @author chenem
     * @create 2017年2月8日 上午11:34:48
     * @param in
     * @param clazz
     * @return
     */
    public static Object deserialize(byte[] in) {
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if (in != null) {
                bis = new ByteArrayInputStream(in);
                is = new ObjectInputStream(bis);
                Object rv = is.readObject();
                is.close();
                bis.close();
                return rv;
            }
            return null;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return null;
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            return null;
        } finally {
            close(is);
            close(bis);
        }
    }
    
    /**
     * 关闭流
     * 
     * @author chenem
     * @create 2017年2月8日 上午11:33:13
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Exception e) {
                log.info("Unable to close:" + closeable);
                log.error(e.getMessage(), e);
            }
        }
    }
    
}
