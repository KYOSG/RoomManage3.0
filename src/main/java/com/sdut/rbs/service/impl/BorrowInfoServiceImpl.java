package com.sdut.rbs.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.rbs.mapper.TimeOptionMapper;
import com.sdut.rbs.entity.RoomEntity;
import com.sdut.rbs.entity.TimeOptionEntity;
import com.sdut.rbs.service.BorrowInfoService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdut.rbs.mapper.BorrowedInfoMapper;
import com.sdut.rbs.entity.BorrowInfoEntity;

import javax.annotation.Resource;


@Service("borrowInfoService")
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Resource
    private BorrowedInfoMapper borrowedInfoMapper;
    @Resource
    private TimeOptionMapper timeOptionMapper;
    @Override
    public ResultVo queryRBIByOptions(Map<String,String> map,int pageNum,int pageSize) {
        Page<BorrowInfoEntity> page = new Page<>(pageNum,pageSize);
        borrowedInfoMapper.queryRBIByOptions(page,map);

        Map<String,Object> res = new HashMap<>();
        res.put("RBI",page.getRecords());
        res.put("total",page.getTotal());
        res.put("current",page.getCurrent());

        return ResultVo.ok(res);
    }

    @Override
    public ResultVo notBorrowedYet(List<String> timeList,String date){
        List<RoomEntity> RBI = borrowedInfoMapper.notBorrowedYet(timeList,date);
        Map<String,Object> res = new HashMap<>();
        res.put("list",RBI);
        return ResultVo.ok(res);
    }

    @Override
    public int isBorrowed(Map<String,String>map){
        String date = map.get("date");
        String time = map.get("time");
        String roomName = map.get("roomName");
        if (borrowedInfoMapper.isBorrowed(roomName, time, date) != null) {
            return 1;
        }else {
            return 0;
        }
    }
    @Override
    public ResultVo borrow(Map<String,String>map){
        try{
            synchronized (this) {//线程锁
                String date = map.get("date");
                String time = map.get("time");
                String roomName = map.get("roomName");
                map.put("isAdmit","0");
                borrowedInfoMapper.borrow(map);
            }
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo cancel(int id) {
        try {
            borrowedInfoMapper.cancel(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public List<BorrowInfoEntity> getAll(){
        return borrowedInfoMapper.getAll();
    }

    @Override
    public ResultVo getDataByDate(String date,String room) {

        List<BorrowInfoEntity> list = borrowedInfoMapper.getDataByDate(date,room);
        Map<String,List<String>> tempMap = new HashMap<>();
        //获取所有时间（按顺序）
        List<TimeOptionEntity> timeOptionList = timeOptionMapper.getAllTime();

        for (TimeOptionEntity time : timeOptionList){
            List<String> rooms = new ArrayList<>();
            tempMap.put(time.getName(),rooms);
        }

        for (BorrowInfoEntity item : list){
            tempMap.get(item.getTime()).add(item.getRoomName());
        }

        Map<String,Object> map = new HashMap<>();
        List<List<String>> res = new ArrayList<>();
        //拼装结果
        for (TimeOptionEntity key: timeOptionList){
            res.add(tempMap.get(key.getName()));
        }

        map.put("list",res);
        return ResultVo.ok(map);
    }


}