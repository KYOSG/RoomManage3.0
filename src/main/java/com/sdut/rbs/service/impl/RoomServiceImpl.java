package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.BorrowedInfoDao;
import com.sdut.rbs.dao.RoomDao;
import com.sdut.rbs.entity.RoomEntity;
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
    private RoomDao roomDAO;

    /*
    * 查询教室信息
    * */
    @Override
    public  ResultVo getAllRoom(){
        List<RoomEntity> roomList = roomDAO.getAllRoom();
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
        List<RoomEntity> room = roomDAO.queryRoomByBorrowOptions(date,timeId,isSpecial);

        if (room == null){
            return ResultVo.error("该条件下没有空闲教室");
        }else {
            Map<String,Object> map = new HashMap<>();
            map.put("roomList",room);
            return ResultVo.ok(map);
        }
    }

    @Override
    public ResultVo addRoom(Map<String, String> map) {
        try{
            roomDAO.addRoom(map);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(int id) {
        try{
            roomDAO.remove(id);
        }catch (Exception e){
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo getRoomById(int id) {
        Map<String,Object> map = new HashMap<>();
        RoomEntity room = roomDAO.getRoomById(id);
        map.put("room",room);

        return ResultVo.ok(map);
    }

    public ResultVo updateRoom(Map<String,String> map){
        try{
            roomDAO.updateRoom(map);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }
}
