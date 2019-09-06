package com.myland.platform.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.github.pagehelper.page.PageMethod;
import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.myland.platform.dao.AreaDao;
import com.myland.platform.entity.AreaDO;
import com.myland.platform.service.AreaService;



@Service("areaService")
public class AreaServiceImpl implements AreaService {
	@Autowired
	private AreaDao areaDao;
	
	@Override
	public AreaDO queryObject(Integer id){
		return areaDao.queryObject(id);
	}
	
	@Override
	public List<AreaDO> queryList(Map<String, Object> map){
		return areaDao.queryList(map);
	}
    @Override
    public PageInfo<AreaDO> queryListForPage(Map<String, Object> map,int pageNum, int pageSize){

        PageMethod.startPage(pageNum, pageSize);
        List<AreaDO> lst = areaDao.queryList(map);
        return new PageInfo<>(lst);
    }
	
	@Override
	public int queryTotal(Map<String, Object> map){
		return areaDao.queryTotal(map);
	}
	
	@Override
	public void insert(AreaDO areaBean){
		areaDao.insert(areaBean);
	}

    @Override
    public void insertSelective(AreaDO areaBean){
			areaDao.insertSelective(areaBean);
	}
	
	@Override
	public void update(AreaDO area){
		areaDao.update(area);
	}
	
	@Override
	public void delete(Integer id){
		areaDao.delete(id);
	}
	
	@Override
	public void deleteBatch(Integer[] ids){
		areaDao.deleteBatch(ids);
	}
	
}
