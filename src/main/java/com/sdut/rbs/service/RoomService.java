package com.sdut.rbs.service;

import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface RoomService {
    //----------------SearchRoomInfo----------------
    public ResultVo getAllRoom();

    public ResultVo queryRoomByBorrowOptions(String date,int timeId,int isSpecial);

    public ResultVo addRoom(Map<String,String> map);
    public ResultVo remove(int id);
    public ResultVo getRoomById(int id);

    public ResultVo updateRoom(Map<String,String> map);
}
