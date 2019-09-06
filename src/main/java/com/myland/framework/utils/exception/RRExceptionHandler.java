package com.myland.framework.utils.exception;

import com.myland.framework.utils.page.ResponseMessage;
import org.apache.shiro.authz.AuthorizationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 * 
 */
@RestControllerAdvice
public class RRExceptionHandler {
	private Logger logger = LoggerFactory.getLogger(getClass());

	/**
	 * 自定义异常
	 */
	@ExceptionHandler(RRException.class)
	public ResponseMessage handleRRException(RRException e){
		ResponseMessage responseMessage = new ResponseMessage();
		responseMessage.put("code", e.getCode());
		responseMessage.put("msg", e.getMessage());

		return responseMessage;
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public ResponseMessage handleDuplicateKeyException(DuplicateKeyException e){
		logger.error(e.getMessage(), e);
		return ResponseMessage.error("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public ResponseMessage handleAuthorizationException(AuthorizationException e){
		logger.error(e.getMessage(), e);
		return ResponseMessage.error("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public ResponseMessage handleException(Exception e){
		logger.error(e.getMessage(), e);
		return ResponseMessage.error();
	}
}
