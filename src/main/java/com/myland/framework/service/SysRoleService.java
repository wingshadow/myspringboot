package com.myland.framework.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.SysRoleEntity;
import com.myland.framework.entity.UserEntity;


/**
 * 角色
 * 
 */
public interface SysRoleService {
	
	SysRoleEntity queryObject(Long roleId);
	
	List<SysRoleEntity> queryList(Map<String, Object> map);

	PageInfo<SysRoleEntity> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysRoleEntity role);
	
	void update(SysRoleEntity role);
	
	void deleteBatch(Long[] roleIds);
	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
