package com.myland.platform.service;

import com.github.pagehelper.PageInfo;
import com.myland.platform.entity.AreaDO;
import java.util.List;
import java.util.Map;

/**
 * 行政区划表
 * 
 * @author myland
 * @email feiying
 * @date 2019-01-21 11:24:34
 */
public interface AreaService {
	
	AreaDO queryObject(Integer id);
	
	List<AreaDO> queryList(Map<String, Object> map);

    PageInfo<AreaDO> queryListForPage(Map<String, Object> map, int pageNum, int pageSize);
	
	int queryTotal(Map<String, Object> map);
	
	void insert(AreaDO areaBean);

    void insertSelective(AreaDO areaBean);
	
	void update(AreaDO areaBean);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}
