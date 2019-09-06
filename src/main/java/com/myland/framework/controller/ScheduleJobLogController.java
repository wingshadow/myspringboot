package com.myland.framework.controller;

import com.github.pagehelper.PageInfo;
import com.myland.framework.utils.page.ResponseMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myland.framework.entity.ScheduleJobLogEntity;
import com.myland.framework.service.ScheduleJobLogService;
import com.myland.framework.utils.page.PageUtils;
import com.myland.framework.utils.page.Query;

import java.util.List;
import java.util.Map;

/**
 * 定时任务日志
 * 
 */
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
	@Autowired
	private ScheduleJobLogService scheduleJobLogService;
	
	/**
	 * 定时任务日志列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:log")
	public ResponseMessage list(@RequestParam Map<String, Object> params){
		//查询列表数据

		int pageNum = Integer.parseInt(params.get("page").toString());
		int pageSize = Integer.parseInt(params.get("limit").toString());

		PageInfo<ScheduleJobLogEntity> pageInfo = scheduleJobLogService.queryListForPage(params,pageNum,pageSize);
		PageUtils pageUtil = new PageUtils(pageInfo);
		
		return ResponseMessage.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务日志信息
	 */
	@RequestMapping("/info/{logId}")
	public ResponseMessage info(@PathVariable("logId") Long logId){
		ScheduleJobLogEntity log = scheduleJobLogService.queryObject(logId);
		
		return ResponseMessage.ok().put("log", log);
	}
}
