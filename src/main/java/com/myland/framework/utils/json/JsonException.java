package com.myland.framework.utils.json;

/**
 * JSON序列化或反序列化处理时产生的异常
 */
public class JsonException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -7353394480389439337L;

    public JsonException(String message, Throwable e) {
        super(message, e);
    }

}
