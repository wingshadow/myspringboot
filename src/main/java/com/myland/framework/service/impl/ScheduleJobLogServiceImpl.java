package com.myland.framework.service.impl;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.page.PageMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myland.framework.dao.ScheduleJobLogDao;
import com.myland.framework.entity.ScheduleJobLogEntity;
import com.myland.framework.service.ScheduleJobLogService;

@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl implements ScheduleJobLogService {
	@Autowired
	private ScheduleJobLogDao scheduleJobLogDao;
	
	@Override
	public ScheduleJobLogEntity queryObject(Long jobId) {
		return scheduleJobLogDao.queryObject(jobId);
	}

	@Override
	public List<ScheduleJobLogEntity> queryList(Map<String, Object> map) {
		return scheduleJobLogDao.queryList(map);
	}

	@Override
	public PageInfo<ScheduleJobLogEntity> queryListForPage(Map<String, Object> map, int pageNum, int pageSize) {
		PageMethod.startPage(pageNum, pageSize);
		List<ScheduleJobLogEntity> lst = scheduleJobLogDao.queryList(map);
		return new PageInfo<>(lst);
	}

	@Override
	public int queryTotal(Map<String, Object> map) {
		return scheduleJobLogDao.queryTotal(map);
	}

	@Override
	public void save(ScheduleJobLogEntity log) {
		scheduleJobLogDao.insert(log);
	}

}
