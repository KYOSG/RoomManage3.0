package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.TimeOptionDao;
import com.sdut.rbs.entity.ReasonEntity;
import com.sdut.rbs.entity.TimeOptionEntity;
import com.sdut.rbs.service.TimeOptionService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;


@Service("timeOptionService")
public class TimeOptionServiceImpl implements TimeOptionService {
    @Resource
    private TimeOptionDao timeOptionDao;


    @Override
    public ResultVo getAllTimeOption() {
        Map<String,Object> map = new HashMap<>();
        List<TimeOptionEntity> list = timeOptionDao.getAllTime();
        map.put("list",list);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo add(String name){
        try{
            timeOptionDao.add(name);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(int id){
        try{
            timeOptionDao.remove(id);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}