package com.myland.framework.utils.json;

import java.io.IOException;
import java.text.SimpleDateFormat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * JSON处理工具类
 */
public abstract class JsonUtil {

    /**
     * Jackson进行JSON处理的类
     */
    private static ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(sdf);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    /**
     * 将目标对象序列化成String形式
     *
     * @param value 要序列化的对象
     * @return 序列化后的字符串
     * @throws JsonException JSON序列化异常
     */
    public static String toJSONString(Object value) throws JsonException {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (IOException e) {
            throw new JsonException("JOSN writeValueAsString exception", e);
        }
    }

    /**
     * 将目标对象序列化成byte数组形式
     *
     * @param value 要序列化的对象
     * @return 序列化后的字节数组
     * @throws JsonException JSON序列化异常
     */
    public static byte[] toJSONBytes(Object value) throws JsonException {
        try {
            return objectMapper.writeValueAsBytes(value);
        } catch (IOException e) {
            throw new JsonException("JOSN writeValueAsBytes exception", e);
        }
    }

    /**
     * JSON字符串反序列化
     *
     * @param <T>       反序列化后的对象类型
     * @param content   JSON字符串
     * @param valueType 反序列化的目标类型
     * @return 反序列化的值
     * @throws JsonException JSON反序列化异常
     */
    public static <T> T readValue(String content, Class<T> valueType) throws JsonException {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new JsonException("JOSN readValue exception", e);
        }
    }

    /**
     * JSON字符串反序列化
     *
     * @param <T>          反序列化后的对象类型
     * @param content      JSON字符串
     * @param valueTypeRef 反序列化的目标类型
     * @return 反序列化的值
     * @throws JsonException JSON反序列化异常
     */
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef) throws JsonException {
        try {
            return objectMapper.readValue(content, valueTypeRef);
        } catch (IOException e) {
            throw new JsonException("JOSN readValue exception", e);
        }
    }

    /**
     * JSON字符串反序列化
     *
     * @param <T>       反序列化后的对象类型
     * @param content   JSON字符串
     * @param valueType 反序列化的目标类型
     * @return 反序列化的值
     * @throws JsonException JSON反序列化异常
     */
    public static <T> T readValue(String content, JavaType valueType) throws JsonException {
        try {
            return objectMapper.readValue(content, valueType);
        } catch (IOException e) {
            throw new JsonException("JOSN readValue exception", e);
        }
    }

    /**
     * 获取JSON格式的字符串的结点解析对象，用于手动解析结构相对复杂或者无规律的JSON对象
     *
     * @param content JSON字符串
     * @return Json节点对象
     */
    public static JsonNode readTree(String content) {
        try {
            return objectMapper.readTree(content);
        } catch (IOException e) {
            throw new JsonException("JOSN readTree exception", e);
        }
    }

}
