package com.sdut.rbs.controller;

import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.service.TimeOptionService;
import com.sdut.rbs.utils.ResultVo;

import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;



/**
 * 时间选项
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@RestController
@CrossOrigin
@RequestMapping("/timeOption")

public class TimeOptionController {
    @Resource
    private TimeOptionService timeOptionService;

    @GetMapping("/getAllTimeOption")
    @ResponseBody
    public ResultVo getTimeOptionByOptions(){

        return timeOptionService.getAllTimeOption();
    }

    @GetMapping("/addTimeOption")
    @ResponseBody
    public ResultVo addTimeOption(@RequestParam String name){
        if (name.length() == 0){
            return ResultVo.error("名称不能为空");
        }
        return timeOptionService.add(name);
    }

    @GetMapping("/removeTimeOption")
    @ResponseBody
    public ResultVo removeTimeOption(@RequestParam int id){

        return timeOptionService.remove(id);
    }

}
