package com.myland.framework.utils.page;

import java.util.HashMap;
import java.util.Map;

/**
 * 返回数据
 * 
 */
public class ResponseMessage extends HashMap<String, Object> {
	private static final long serialVersionUID = 1L;
	
	public ResponseMessage() {
		put("code", 0);
	}
	
	public static ResponseMessage error() {
		return error(500, "未知异常，请联系管理员");
	}
	
	public static ResponseMessage error(String msg) {
		return error(500, msg);
	}
	
	public static ResponseMessage error(int code, String msg) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.put("code", code);
		responseMessage.put("msg", msg);
		return responseMessage;
	}

	public static ResponseMessage ok(String msg) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.put("msg", msg);
		return responseMessage;
	}
	
	public static ResponseMessage ok(Map<String, Object> map) {
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.putAll(map);
		return responseMessage;
	}
	
	public static ResponseMessage ok() {
		return new ResponseMessage();
	}

	@Override
	public ResponseMessage put(String key, Object value) {
		super.put(key, value);
		return this;
	}
}
