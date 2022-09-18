package com.sdut.rbs.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.service.BorrowInfoService;
import com.sdut.rbs.utils.ResultVo;

import org.springframework.web.bind.annotation.*;

import com.sdut.rbs.entity.BorrowInfoEntity;
import com.sdut.rbs.service.BorrowInfoService;

import javax.annotation.Resource;


/**
 * 借用记录
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@RestController
@RequestMapping("/borrowInfo")
@CrossOrigin
public class BorrowInfoController {
    @Resource
    private BorrowInfoService borrowInfoService;

    //获取借用记录
    @RequestMapping(value = "/queryRBIByOptions",method = RequestMethod.POST)
    public ResultVo queryRBIByOptions(@RequestBody JSONObject jsonObject) {
        Map<String,String> map = new HashMap<>();

        map.put("time",jsonObject.getString("time"));
        map.put("date",jsonObject.getString("date"));
        map.put("roomName",jsonObject.getString("roomName"));

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageSize = jsonObject.getIntValue("pageSize");
        return borrowInfoService.queryRBIByOptions(map,pageNum,pageSize);
    }

    @PostMapping(value = "/notBorrowedYet")
    @ResponseBody
    public ResultVo notBorrowedYet(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("time");

        String date = jsonObject.getString("date");

        List<String> timeList = new ArrayList<>();
        for (Object item : jsonArray){
            timeList.add(item.toString());
        }

        return borrowInfoService.notBorrowedYet(timeList,date);
    }

    @PostMapping(value = "/borrow")
    @ResponseBody
    public ResultVo borrowRoom(@RequestBody JSONObject jsonObject){
        JSONArray timeList = jsonObject.getJSONArray("time");

        Map<String,String> map = new HashMap<>();
        map.put("name",jsonObject.getString("name"));

        map.put("date",jsonObject.getString("date"));
        map.put("reason",jsonObject.getString("reason"));
        map.put("applyDate",jsonObject.getString("applyDate"));
        map.put("roomName",jsonObject.getString("roomName"));

        for(Object item : timeList){
            map.put("time",item.toString());
            int code = borrowInfoService.isBorrowed(map);
            switch (code){
                case 0: continue;
                case 1: return ResultVo.error("借用失败，请选择其他时间段");
            }
        }

        for(Object item : timeList){
            map.put("time",item.toString());
            borrowInfoService.borrow(map);
        }

        return ResultVo.ok("借用成功");
    }

    @GetMapping( "/cancel")
    @ResponseBody
    public ResultVo cancel(@RequestParam int id){
        return borrowInfoService.cancel(id);
    }
}
