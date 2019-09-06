package com.myland.platform.controller;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.myland.framework.utils.page.PageUtils;
import com.myland.framework.utils.page.ResponseMessage;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myland.platform.entity.AreaDO;
import com.myland.platform.service.AreaService;



/**
 * 行政区划表
 * 
 * @author myland
 * @email feiying
 * @date 2019-01-21 11:24:34
 */
@RestController
@RequestMapping("area")
public class AreaController {
	@Autowired
	private AreaService areaService;
	
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("area:list")
	public ResponseMessage list(@RequestParam Map<String, Object> params){
        int pageNum = Integer.parseInt(params.get("page").toString());
        int pageSize = Integer.parseInt(params.get("limit").toString());

        PageInfo<AreaDO> pageInfo = areaService.queryListForPage(params,pageNum,pageSize);
        PageUtils pageUtil = new PageUtils(pageInfo);
		
		return ResponseMessage.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("area:info")
	public ResponseMessage info(@PathVariable("id") Integer id){
		AreaDO areaBean = areaService.queryObject(id);
		
		return ResponseMessage.ok().put("areaDO", areaBean);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("area:save")
	public ResponseMessage save(@RequestBody AreaDO areaBean){
		areaService.insertSelective(areaBean);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("area:update")
	public ResponseMessage update(@RequestBody AreaDO areaBean){
		areaService.update(areaBean);
		
		return ResponseMessage.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("area:delete")
	public ResponseMessage delete(@RequestBody Integer[] ids){
		areaService.deleteBatch(ids);
		
		return ResponseMessage.ok();
	}
	
}
