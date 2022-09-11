package com.sdut.rbs.controller;


import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.entity.UsersEntity;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.boot.autoconfigure.graphql.ConditionalOnGraphQlSchema;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String id = jsonParam.getString("userId");
        String pwd = jsonParam.getString("pwd");

        return uerService.checkLogin(id,pwd);
    }

    @GetMapping("/getAllUser")
    @ResponseBody
    public ResultVo getAllUser(){
        return uerService.getAllUser();
    }

    @PostMapping("/addUser")
    @ResponseBody
    public ResultVo addUser(@RequestBody JSONObject jsonObject){
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(jsonObject.getString("id"));
        usersEntity.setPassword(jsonObject.getString("password"));
        usersEntity.setName(jsonObject.getString("name"));
        usersEntity.setRole(jsonObject.getString("role"));
        List<UsersEntity> list = new ArrayList<>();
        list.add(usersEntity);
        return uerService.addUser(list);
    }

    @GetMapping("/getUserByName")
    @ResponseBody
    public ResultVo getUserByName(@RequestParam  String name){
        return uerService.getUserByName(name);
    }

    @PostMapping("/updateUser")
    @ResponseBody
    public ResultVo updateUser(@RequestBody JSONObject jsonObject){
        Map<String,String> map = new HashMap<>();
        map.put("id",jsonObject.getString("id"));
        map.put("name",jsonObject.getString("name"));
        map.put("role",jsonObject.getString("role"));
        map.put("password",jsonObject.getString("password"));
        return uerService.updateUser(map);
    }
    @GetMapping("/remove")
    @ResponseBody
    public ResultVo remove(String id){
        return uerService.remove(id);
    }
}
