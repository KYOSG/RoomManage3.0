package com.sdut.rbs.dao;

import com.sdut.rbs.entity.Room;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RoomDAO {
    public List<Room> getAllRoom();

    public List<Room> queryRoomByBorrowOptions(String date,int timeId,int isSpecial);
}

