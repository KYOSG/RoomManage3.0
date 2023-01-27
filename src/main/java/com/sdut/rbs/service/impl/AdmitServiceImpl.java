package com.sdut.rbs.service.impl;

import com.sdut.rbs.mapper.AdmitMapper;
import com.sdut.rbs.entity.AdmitEntity;
import com.sdut.rbs.service.AdmitService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AdmitServiceImpl implements AdmitService {
    @Resource
    private AdmitMapper admitMapper;

    @Override
    public ResultVo getAllAdmitList() {
        List<AdmitEntity> admitEntityList = admitMapper.getAllAdmitList();
        Map<String,Object> map = new HashMap<>();
        map.put("list",admitEntityList);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo access(int id) {
        try{
            admitMapper.access(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo deny(int id) {
        try{
            admitMapper.deny(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}
