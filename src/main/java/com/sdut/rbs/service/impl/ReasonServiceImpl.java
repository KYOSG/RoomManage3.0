package com.sdut.rbs.service.impl;

import com.sdut.rbs.service.ReasonService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;


import com.sdut.rbs.mapper.ReasonMapper;
import com.sdut.rbs.entity.ReasonEntity;


import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("reasonService")
public class ReasonServiceImpl implements ReasonService {
    @Resource
    private ReasonMapper reasonMapper;

    @Override
    public ResultVo getAllReasonOption() {
        Map<String,Object> map = new HashMap<>();
        List<ReasonEntity> list = reasonMapper.getAllReason();
        map.put("list",list);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo add(String name){
        if (name.length() == 0){
            return ResultVo.error("名称不能为空");
        }
        try{
            reasonMapper.add(name);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(int id){
        try{
                reasonMapper.remove(id);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}