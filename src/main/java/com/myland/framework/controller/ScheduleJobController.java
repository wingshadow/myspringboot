package com.myland.framework.controller;

import com.github.pagehelper.PageInfo;
import com.myland.framework.utils.page.ResponseMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myland.framework.entity.ScheduleJobEntity;
import com.myland.framework.service.ScheduleJobService;
import com.myland.framework.utils.page.PageUtils;
import com.myland.framework.utils.page.Query;
import com.myland.framework.utils.annotation.SysLog;
import com.myland.framework.utils.validator.ValidatorUtils;

import java.util.List;
import java.util.Map;

/**
 * 定时任务
 * 
 */
@RestController
@RequestMapping("/sys/schedule")
public class ScheduleJobController {
	@Autowired
	private ScheduleJobService scheduleJobService;
	
	/**
	 * 定时任务列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:schedule:list")
	public ResponseMessage list(@RequestParam Map<String, Object> params){
		//查询列表数据

		int pageNum = Integer.parseInt(params.get("page").toString());
		int pageSize = Integer.parseInt(params.get("limit").toString());

		PageInfo<ScheduleJobEntity> pageInfo = scheduleJobService.queryListForPage(params,pageNum,pageSize);
		PageUtils pageUtil = new PageUtils(pageInfo);
		
		return ResponseMessage.ok().put("page", pageUtil);
	}
	
	/**
	 * 定时任务信息
	 */
	@RequestMapping("/info/{jobId}")
	@RequiresPermissions("sys:schedule:info")
	public ResponseMessage info(@PathVariable("jobId") Long jobId){
		ScheduleJobEntity schedule = scheduleJobService.queryObject(jobId);
		
		return ResponseMessage.ok().put("schedule", schedule);
	}
	
	/**
	 * 保存定时任务
	 */
	@SysLog("保存定时任务")
	@RequestMapping("/save")
	@RequiresPermissions("sys:schedule:save")
	public ResponseMessage save(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
		
		scheduleJobService.save(scheduleJob);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 修改定时任务
	 */
	@SysLog("修改定时任务")
	@RequestMapping("/update")
	@RequiresPermissions("sys:schedule:update")
	public ResponseMessage update(@RequestBody ScheduleJobEntity scheduleJob){
		ValidatorUtils.validateEntity(scheduleJob);
				
		scheduleJobService.update(scheduleJob);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 删除定时任务
	 */
	@SysLog("删除定时任务")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:schedule:delete")
	public ResponseMessage delete(@RequestBody Long[] jobIds){
		scheduleJobService.deleteBatch(jobIds);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 立即执行任务
	 */
	@SysLog("立即执行任务")
	@RequestMapping("/run")
	@RequiresPermissions("sys:schedule:run")
	public ResponseMessage run(@RequestBody Long[] jobIds){
		scheduleJobService.run(jobIds);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 暂停定时任务
	 */
	@SysLog("暂停定时任务")
	@RequestMapping("/pause")
	@RequiresPermissions("sys:schedule:pause")
	public ResponseMessage pause(@RequestBody Long[] jobIds){
		scheduleJobService.pause(jobIds);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 恢复定时任务
	 */
	@SysLog("恢复定时任务")
	@RequestMapping("/resume")
	@RequiresPermissions("sys:schedule:resume")
	public ResponseMessage resume(@RequestBody Long[] jobIds){
		scheduleJobService.resume(jobIds);
		
		return ResponseMessage.ok();
	}

}
