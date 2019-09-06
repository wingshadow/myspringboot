package com.myland.framework.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.myland.framework.entity.ScheduleJobEntity;
import com.myland.framework.entity.ScheduleJobLogEntity;

/**
 * 定时任务日志
 * 
 */
public interface ScheduleJobLogService {

	/**
	 * 根据ID，查询定时任务日志
	 */
	ScheduleJobLogEntity queryObject(Long jobId);
	
	/**
	 * 查询定时任务日志列表
	 */
	List<ScheduleJobLogEntity> queryList(Map<String, Object> map);

	PageInfo<ScheduleJobLogEntity> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	/**
	 * 查询总数
	 */
	int queryTotal(Map<String, Object> map);
	
	/**
	 * 保存定时任务日志
	 */
	void save(ScheduleJobLogEntity log);
	
}
