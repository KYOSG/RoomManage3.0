package com.sdut.rbs.controller;


import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
@ResponseBody
@RequestMapping("/user")
@CrossOrigin
public class UserController  {

    @Resource
    private UserService uerService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public ResultVo login(@RequestBody JSONObject jsonParam){
        System.out.println(jsonParam);
        String userId = jsonParam.getString("userId");
        String pwd = jsonParam.getString("pwd");

        return uerService.checkLogin(userId,pwd);
    }
}
