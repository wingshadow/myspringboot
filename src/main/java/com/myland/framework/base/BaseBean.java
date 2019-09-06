package com.myland.framework.base;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * <pre>
 * 基础Bean，实现Serializable接口并重写toString方法
 * ① 方便对象的序列化
 * ② 方便将对象以字符串的形式打印到日志中
 * </pre>
 * 
 * @author FengChangshuo
 * @version 1.0.0 2017年5月3日 下午2:52:25 created
 */
public class BaseBean implements Serializable {
    
    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1161835415527064636L;
    
    @Override
    public String toString() {
        return JSONObject.toJSONString(this);
    }
    
}
