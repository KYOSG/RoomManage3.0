package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.RoomBorrowedInfoDAO;
import com.sdut.rbs.dao.RoomDAO;
import com.sdut.rbs.entity.Room;
import com.sdut.rbs.entity.RoomBorrowedInfo;
import com.sdut.rbs.service.RoomService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Scope("singleton")
public class RoomServiceImpl implements RoomService {
    @Resource
    private RoomDAO roomDAO;
    @Resource
    private RoomBorrowedInfoDAO roomBorrowInfoDAO;

    /*
    * 查询教室信息
    * */
    @Override
    public  ResultVo getAllRoom(){
        List<Room> roomList = roomDAO.getAllRoom();
        if (roomList.size() == 0){
            return ResultVo.error("未查询到教室信息");
        }else {
            Map<String, Object> map = new HashMap<>();
            map.put("list",roomList);
            return ResultVo.ok(map);
        }
    }
    @Override
    public ResultVo queryRoomByBorrowOptions(String date,int timeId,int isSpecial){
        List<Room> room = roomDAO.queryRoomByBorrowOptions(date,timeId,isSpecial);

        if (room == null){
            return ResultVo.error("该条件下没有空闲教室");
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("roomList",room);
            return ResultVo.ok(map);
        }
    }
    /*
     * 查询教室借用信息
     * */
    @Override
    public ResultVo  queryRBIByOptions(Map<String,String> map,int pageNum,int pageSize) {
        List<RoomBorrowedInfo> RBI = roomBorrowInfoDAO.queryRBIByOptions(map);
        Map<String,Object> res = new HashMap<>();
        res.put("RBI",RBI);
        return ResultVo.ok(res);
    }

    @Override
    public ResultVo borrow(Map<String,String>map){
        synchronized (this) {//线程锁
//            int timeId = Integer.parseInt(map.get("timeID"));
//            String date = map.get("date");
//            String roomId = map.get("roomId");
//            RoomBorrowedInfo RBI = roomBorrowInfoDAO.queryRBIByTimeAndRoomId(timeId, date, roomId);


            roomBorrowInfoDAO.borrow(map);
            return ResultVo.ok("登记成功");
        }
    }

    @Override
    public ResultVo cancel(int id) {
        roomBorrowInfoDAO.cancel(id);
        return ResultVo.ok();
    }
}
