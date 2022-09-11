package com.sdut.rbs.dao;

import com.sdut.rbs.entity.RoomEntity;
import com.sdut.rbs.utils.ResultVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface RoomDao {
    public List<RoomEntity> getAllRoom();

    public void addRoom(Map<String,String> map);
    public void remove(int id);
    public List<RoomEntity> queryRoomByBorrowOptions(String date,int timeId,int isSpecial);
    public RoomEntity getRoomById(int id);

    public void updateRoom(Map<String,String> map);
}

