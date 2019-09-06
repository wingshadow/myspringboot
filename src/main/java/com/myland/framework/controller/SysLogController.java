package com.myland.framework.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.myland.framework.entity.SysLogEntity;
import com.myland.framework.service.SysLogService;
import com.myland.framework.utils.page.PageUtils;
import com.myland.framework.utils.page.Query;
import com.myland.framework.utils.page.ResponseMessage;

import java.util.List;
import java.util.Map;


/**
 * 系统日志
 * 
 */
@Controller
@RequestMapping("/sys/log")
public class SysLogController {
	@Autowired
	private SysLogService sysLogService;
	
	/**
	 * 列表
	 */
	@ResponseBody
	@RequestMapping("/list")
	@RequiresPermissions("sys:log:list")
	public ResponseMessage list(@RequestParam Map<String, Object> params){
		int pageNum = Integer.parseInt(params.get("page").toString());
		int pageSize = Integer.parseInt(params.get("limit").toString());

		PageInfo<SysLogEntity> pageInfo = sysLogService.queryListForPage(params,pageNum,pageSize);
		PageUtils pageUtil = new PageUtils(pageInfo);
		
		return ResponseMessage.ok().put("page", pageUtil);
	}
	
}
