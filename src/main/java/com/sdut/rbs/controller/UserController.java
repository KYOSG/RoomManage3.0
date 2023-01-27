package com.sdut.rbs.controller;


import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.entity.UsersEntity;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@ResponseBody
@CrossOrigin

public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserService uerService;

    @PostMapping(value = "/user/login")
    @ResponseBody
    public ResultVo login(@RequestBody JSONObject jsonParam) {
        String id = jsonParam.getString("userId");
        String pwd = jsonParam.getString("pwd");

        return uerService.checkLogin(id, pwd);
    }

    @GetMapping("/user/{pageNum}/{pageSize}")
    @ResponseBody
    public ResultVo getAllUser(@PathVariable int pageNum, @PathVariable int pageSize) {
        return uerService.getAllUser(pageNum, pageSize);
    }

    @PostMapping("/user")
    @ResponseBody
    public ResultVo addUser(@RequestBody JSONObject jsonObject) {
        UsersEntity usersEntity = new UsersEntity();
        usersEntity.setId(jsonObject.getString("id"));
        usersEntity.setPassword(jsonObject.getString("password"));
        usersEntity.setUsername(jsonObject.getString("username"));
        usersEntity.setUserDepart(jsonObject.getString("userDepart"));
        usersEntity.setRole(jsonObject.getString("role"));
        List<UsersEntity> list = new ArrayList<>();
        list.add(usersEntity);
        return uerService.addUser(list);
    }

    @GetMapping("/user/{username}")
    @ResponseBody
    public ResultVo getUserByName(@PathVariable String username) {
        return uerService.getUserByName(username);
    }


    @PutMapping("/user")
    @ResponseBody
    public ResultVo updateUser(@RequestBody JSONObject jsonObject) {
        Map<String, String> map = new HashMap<>();
        map.put("id", jsonObject.getString("id"));
        map.put("username", jsonObject.getString("username"));
        map.put("role", jsonObject.getString("role"));
        map.put("password", jsonObject.getString("password"));
        return uerService.updateUser(map);
    }

    @DeleteMapping("/user/{id}")
    @ResponseBody
    public ResultVo remove(@PathVariable String id) {
        return uerService.remove(id);
    }

    @PutMapping("/changePwd")
    @ResponseBody
    public ResultVo changePwd(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        String id = jsonObject.getString("userId");
        String newPwd = jsonObject.getString("newPwd");
        String oldPwd = jsonObject.getString("oldPwd");
        return uerService.changePassword(id, oldPwd, newPwd);
    }
}
