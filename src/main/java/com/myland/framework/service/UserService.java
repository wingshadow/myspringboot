package com.myland.framework.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.SysUserEntity;
import com.myland.framework.entity.UserEntity;

/**
 * 用户
 * 
 */
public interface UserService {

	UserEntity queryObject(Long userId);
	
	List<UserEntity> queryList(Map<String, Object> map);

	PageInfo<UserEntity> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	int queryTotal(Map<String, Object> map);
	
	void save(String mobile, String password);
	
	void update(UserEntity user);
	
	void delete(Long userId);
	
	void deleteBatch(Long[] userIds);

	UserEntity queryByMobile(String mobile);

	/**
	 * 用户登录
	 * @param mobile    手机号
	 * @param password  密码
	 * @return          返回用户ID
	 */
	long login(String mobile, String password);
}
