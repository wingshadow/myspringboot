package com.myland.framework.service;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.SysLogEntity;

import java.util.List;
import java.util.Map;

/**
 * 代码生成器
 * 
 */
public interface SysGeneratorService {
	
	List<Map<String, Object>> queryList(Map<String, Object> map);

	PageInfo<Map<String, Object>> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	int queryTotal(Map<String, Object> map);
	
	Map<String, String> queryTable(String tableName);
	
	List<Map<String, String>> queryColumns(String tableName);
	
	/**
	 * 生成代码
	 */
	byte[] generatorCode(String[] tableNames);
}
