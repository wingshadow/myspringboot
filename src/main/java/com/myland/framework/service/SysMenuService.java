package com.myland.framework.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.SysMenuEntity;


/**
 * 菜单管理
 * 
 */
public interface SysMenuService {
	
	/**
	 * 根据父菜单，查询子菜单
	 * @param parentId 父菜单ID
	 * @param menuIdList  用户菜单ID
	 */
	List<SysMenuEntity> queryListParentId(Long parentId, List<Long> menuIdList);
	
	/**
	 * 获取不包含按钮的菜单列表
	 */
	List<SysMenuEntity> queryNotButtonList();
	
	/**
	 * 获取用户菜单列表
	 */
	List<SysMenuEntity> getUserMenuList(Long userId);

	/**
	 * 获取用户权限列表
	 */
	Set<String> getUserPermissions(long userId);
	
	
	/**
	 * 查询菜单
	 */
	SysMenuEntity queryObject(Long menuId);
	
	/**
	 * 查询菜单列表
	 */
	List<SysMenuEntity> queryList(Map<String, Object> map);

	PageInfo<SysMenuEntity> queryListForPage(Map<String, Object> map,int pageNum, int pageSize);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存菜单
	 */
	void save(SysMenuEntity menu);
	
	/**
	 * 修改
	 */
	void update(SysMenuEntity menu);
	
	/**
	 * 删除
	 */
	void deleteBatch(Long[] menuIds);
	
	/**
	 * 查询用户的权限列表
	 */
	List<SysMenuEntity> queryUserList(Long userId);
}
