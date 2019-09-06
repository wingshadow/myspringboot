package com.myland.framework.controller;

import com.github.pagehelper.PageInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.myland.framework.entity.SysConfigEntity;
import com.myland.framework.service.SysConfigService;
import com.myland.framework.utils.page.PageUtils;
import com.myland.framework.utils.page.Query;
import com.myland.framework.utils.page.ResponseMessage;
import com.myland.framework.utils.annotation.SysLog;
import com.myland.framework.utils.validator.ValidatorUtils;

import java.util.List;
import java.util.Map;

/**
 * 系统配置信息
 * 
 */
@RestController
@RequestMapping("/sys/config")
public class SysConfigController extends AbstractController {


	@Autowired
	private SysConfigService sysConfigService;
	
	/**
	 * 所有配置列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("sys:config:list")
	public ResponseMessage list(@RequestParam Map<String, Object> params){
		//查询列表数据

		int pageNum = Integer.parseInt(params.get("page").toString());
		int pageSize = Integer.parseInt(params.get("limit").toString());

		PageInfo<SysConfigEntity> pageInfo = sysConfigService.queryListForPage(params,pageNum,pageSize);
		PageUtils pageUtil = new PageUtils(pageInfo);
		
		return ResponseMessage.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 配置信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("sys:config:info")
	public ResponseMessage info(@PathVariable("id") Long id){
		SysConfigEntity config = sysConfigService.queryObject(id);
		
		return ResponseMessage.ok().put("config", config);
	}
	
	/**
	 * 保存配置
	 */
	@SysLog("保存配置")
	@RequestMapping("/save")
	@RequiresPermissions("sys:config:save")
	public ResponseMessage save(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);

		sysConfigService.save(config);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 修改配置
	 */
	@SysLog("修改配置")
	@RequestMapping("/update")
	@RequiresPermissions("sys:config:update")
	public ResponseMessage update(@RequestBody SysConfigEntity config){
		ValidatorUtils.validateEntity(config);
		
		sysConfigService.update(config);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 删除配置
	 */
	@SysLog("删除配置")
	@RequestMapping("/delete")
	@RequiresPermissions("sys:config:delete")
	public ResponseMessage delete(@RequestBody Long[] ids){
		sysConfigService.deleteBatch(ids);
		
		return ResponseMessage.ok();
	}

}
