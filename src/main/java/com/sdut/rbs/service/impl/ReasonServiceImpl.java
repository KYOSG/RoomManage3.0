package com.sdut.rbs.service.impl;

import com.sdut.rbs.service.ReasonService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;


import com.sdut.rbs.dao.ReasonDao;
import com.sdut.rbs.entity.ReasonEntity;


import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service("reasonService")
public class ReasonServiceImpl implements ReasonService {
    @Resource
    private ReasonDao reasonDao;

    @Override
    public ResultVo getAllReasonOption() {
        Map<String,Object> map = new HashMap<>();
        List<ReasonEntity> list = reasonDao.getAllReason();
        map.put("list",list);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo add(String name){
        try{
            reasonDao.add(name);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(int id){
        try{
                reasonDao.remove(id);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}