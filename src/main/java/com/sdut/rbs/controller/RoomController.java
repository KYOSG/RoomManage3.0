package com.sdut.rbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.service.RoomService;
import com.sdut.rbs.utils.ResultVo;
import com.sdut.rbs.utils.SFFileUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
/*
* 等价于
* @Controller
* @ResponseBody //返回值为json格式
* */

@RequestMapping("/room")
@CrossOrigin
public class RoomController {
    @Resource
    private RoomService roomService;
    /*
    * POST 添加
    * GET 查询
    * PUT 修改
    * DELETE 删除
    * */
    //----------------SearchRoomInfo----------------
    @RequestMapping(value = "/getAllRoom",method = RequestMethod.GET)
    public ResultVo getAllRoom(){
        return roomService.getAllRoom();
    }

    @RequestMapping(value = "/searchRoomForBorrow", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo searchRoomForBorrow(@RequestBody JSONObject jsonParam){

        String date = jsonParam.getString("date");
        int timeId = jsonParam.getInteger("timeId");

        int isSpecial;

        String isSpecialS = jsonParam.getString("isSpecial");

        if (isSpecialS.equals("true")){
            isSpecial = 1;
        }else {
            isSpecial = 0;
        }

        return roomService.queryRoomByBorrowOptions(date,timeId,isSpecial);
    }

    @PostMapping("/addRoom")
    @ResponseBody
    public ResultVo addRoom(@RequestBody JSONObject jsonObject){
        Map<String,String> map = new HashMap<>();
        map.put("name",jsonObject.getString("name"));
        map.put("capacity",jsonObject.getString("capacity"));
        map.put("description",jsonObject.getString("description"));
        map.put("status",jsonObject.getString("status"));
        map.put("imgUrl",jsonObject.getString("imgUrl"));
        return roomService.addRoom(map);
    }

    @GetMapping("/remove")
    @ResponseBody
    public ResultVo remove(@RequestParam int id){
        return roomService.remove(id);
    }

    @GetMapping("/getRoomById")
    @ResponseBody
    public ResultVo getRoomById(@RequestParam int id){
        return roomService.getRoomById(id);
    }

    @PostMapping("/updateRoom")
    @ResponseBody
    public ResultVo updateRoom(@RequestBody JSONObject jsonObject){
        Map<String,String> map = new HashMap<>();
        map.put("id",jsonObject.getString("id"));
        map.put("name",jsonObject.getString("name"));
        map.put("capacity",jsonObject.getString("capacity"));
        map.put("description",jsonObject.getString("description"));
        map.put("status",jsonObject.getString("status"));
        map.put("imgUrl",jsonObject.getString("imgUrl"));
        return roomService.updateRoom(map);
    }
}

