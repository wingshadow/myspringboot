package com.myland.framework.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.SysLogEntity;
import com.myland.framework.entity.SysRoleEntity;

/**
 * 系统日志
 * 
 */
public interface SysLogService {
	
	SysLogEntity queryObject(Long id);
	
	List<SysLogEntity> queryList(Map<String, Object> map);

	PageInfo<SysLogEntity> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	int queryTotal(Map<String, Object> map);
	
	void save(SysLogEntity sysLog);
	
	void update(SysLogEntity sysLog);
	
	void delete(Long id);
	
	void deleteBatch(Long[] ids);
}
