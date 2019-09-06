package com.myland.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.myland.framework.entity.SysUserEntity;
import com.myland.framework.utils.shiro.ShiroUtils;

/**
 * Controller公共组件
 * 
 */
public abstract class AbstractController {
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	protected SysUserEntity getUser() {
		return ShiroUtils.getUserEntity();
	}

	protected Long getUserId() {
		return getUser().getUserId();
	}
}
