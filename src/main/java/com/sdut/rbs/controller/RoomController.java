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
    //-------------SearchRoomBorrowInfo-------------
    @RequestMapping(value = "/queryRBIByOptions",method = RequestMethod.POST)
    public ResultVo queryRBIByOptions(@RequestBody JSONObject jsonParam) {
        Map<String,String> map = new HashMap<>();
        map.put("date",jsonParam.getString("date"));
        map.put("reason",jsonParam.getString("reason"));
        map.put("timeId",jsonParam.getString("timeId"));
        map.put("roomId",jsonParam.getString("roomId"));
        int pageNum = 1;
        int pageSize = 50;
        return roomService.queryRBIByOptions(map,pageNum,pageSize);
    }

    //---------------RoomBorrowOperator---------------
    @PostMapping(value = "/borrow")//等价于RequestMapping(value = "/borrow/{id}", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo borrowRoom(@RequestBody JSONObject jsonObject){
        Map<String,String> map = new HashMap<>();
        map.put("date",jsonObject.getString("date"));
        map.put("roomId",jsonObject.getString("roomId"));
        map.put("roomName",jsonObject.getString("roomName"));
        map.put("timeId",jsonObject.getString("timeId"));
        map.put("timeName",jsonObject.getString("timeName"));
        map.put("username",jsonObject.getString("username"));
        map.put("userDepart",jsonObject.getString("userDepart"));

        map.put("reason",jsonObject.getString("reason"));
        map.put("applyTime",jsonObject.getString("applyTime"));
        return roomService.borrow(map);
    }

    @GetMapping( "/cancel")
    @ResponseBody
    public ResultVo cancel(@RequestParam int id){
        return roomService.cancel(id);
    }

}

