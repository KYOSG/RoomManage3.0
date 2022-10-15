package com.sdut.rbs.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.rbs.entity.BorrowInfoEntity;
import com.sdut.rbs.entity.RoomEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface BorrowedInfoDao {
    public Page<BorrowInfoEntity>  queryRBIByOptions(Page<BorrowInfoEntity> page,Map<String, String> map);
    public List<RoomEntity> notBorrowedYet(@Param("timeList") List<String> timeList,@Param("date") String date);
    public BorrowInfoEntity isBorrowed(@Param("roomName") String roomName,@Param("time") String time,@Param("date") String date);
    public BorrowInfoEntity borrow(Map<String,String>map);
    public void cancel(int id);
    public List<BorrowInfoEntity> getAll();
    public List<BorrowInfoEntity> getDataByDate(String date,String room);
}
