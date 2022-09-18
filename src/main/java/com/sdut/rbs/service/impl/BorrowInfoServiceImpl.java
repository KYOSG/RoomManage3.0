package com.sdut.rbs.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.rbs.entity.RoomEntity;
import com.sdut.rbs.service.BorrowInfoService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sdut.rbs.dao.BorrowedInfoDao;
import com.sdut.rbs.entity.BorrowInfoEntity;

import javax.annotation.Resource;


@Service("borrowInfoService")
public class BorrowInfoServiceImpl implements BorrowInfoService {
    @Resource
    private BorrowedInfoDao borrowedInfoDAO;

    @Override
    public ResultVo queryRBIByOptions(Map<String,String> map,int pageNum,int pageSize) {
        Page<BorrowInfoEntity> page = new Page<>(pageNum,pageSize);
        borrowedInfoDAO.queryRBIByOptions(page,map);

        Map<String,Object> res = new HashMap<>();
        res.put("RBI",page.getRecords());
        res.put("total",page.getTotal());
        res.put("current",page.getCurrent());

        return ResultVo.ok(res);
    }

    @Override
    public ResultVo notBorrowedYet(List<String> timeList,String date){
        List<RoomEntity> RBI = borrowedInfoDAO.notBorrowedYet(timeList,date);
        Map<String,Object> res = new HashMap<>();
        res.put("list",RBI);
        return ResultVo.ok(res);
    }

    @Override
    public int isBorrowed(Map<String,String>map){
        String date = map.get("date");
        String time = map.get("time");
        String roomName = map.get("roomName");
        if (borrowedInfoDAO.isBorrowed(roomName, time, date) != null) {
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
                borrowedInfoDAO.borrow(map);
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
            borrowedInfoDAO.cancel(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}