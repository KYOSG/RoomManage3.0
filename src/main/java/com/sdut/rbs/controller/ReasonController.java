package com.sdut.rbs.controller;

import java.util.Arrays;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.utils.ResultVo;

import org.springframework.web.bind.annotation.*;

import com.sdut.rbs.entity.ReasonEntity;
import com.sdut.rbs.service.ReasonService;


import javax.annotation.Resource;



/**
 * 借用原因
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@RestController
@CrossOrigin
@RequestMapping("/reason")
public class ReasonController {
    @Resource
    private ReasonService reasonService;

    @GetMapping("/getAllReasonOption")
    @ResponseBody
    public ResultVo getAllReasonOption(){
        return reasonService.getAllReasonOption();
    }

    @GetMapping("/addReason")
    @ResponseBody
    public ResultVo addReason(@RequestParam String name){
        return reasonService.add(name);
    }

    @GetMapping("/removeReason")
    @ResponseBody
    public ResultVo removeReason(@RequestParam int id){
        return reasonService.remove(id);
    }

}
