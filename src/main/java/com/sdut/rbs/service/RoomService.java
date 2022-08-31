package com.sdut.rbs.service;

import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import java.util.Map;

public interface RoomService {
    //----------------SearchRoomInfo----------------
    public ResultVo getAllRoom();

    public ResultVo queryRoomByBorrowOptions(String date,int timeId,int isSpecial);

    //-------------SearchRoomBorrowInfo-------------
    public ResultVo  queryRBIByOptions(Map<String,String> map,int pageNum,int pageSize);//该按条件查询教室
    //-------------RoomBorrowOperate-------------
    public ResultVo borrow (Map<String,String> map);


    public ResultVo cancel (int id);//借用记录id
}
