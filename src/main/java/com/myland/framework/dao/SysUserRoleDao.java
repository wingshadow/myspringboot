package com.myland.framework.dao;

import java.util.List;

import com.myland.framework.entity.SysUserRoleEntity;

/**
 * 用户与角色对应关系
 * 
 */
public interface SysUserRoleDao extends BaseDao<SysUserRoleEntity> {
	
	/**
	 * 根据用户ID，获取角色ID列表
	 */
	List<Long> queryRoleIdList(Long userId);
}
