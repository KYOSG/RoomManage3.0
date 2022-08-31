package com.sdut.rbs.dao;

import com.sdut.rbs.entity.RoomBorrowedInfo;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomBorrowedInfoDAO {
    public List<RoomBorrowedInfo>  queryRBIByOptions(Map<String,String>map);

    public RoomBorrowedInfo queryRBIByTimeAndRoomId(int timeId,String date, String roomId);

    public RoomBorrowedInfo borrow(Map<String,String>map);

    public void cancel(int id);
}
