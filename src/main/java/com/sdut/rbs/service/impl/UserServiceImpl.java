package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.UserDAO;
import com.sdut.rbs.entity.User;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Override
    public ResultVo checkLogin(String userId, String pwd){
        //查询用户信息
        User user = userDAO.checkLogin(userId);
        //判断
        if (user == null){
            //用户名不存在
            return ResultVo.error("用户名不存在，请联系管理员注册");
        }else {
            //对密码加密

            //使用加密后的密码与user中的密码进行匹配
            if (user.getPassword().equals(pwd) ){
                //验证成功
                //String token = Base64Utils.encode(userId);
                Map<String, Object> map = new HashMap<>();
                map.put("user",user);
                return ResultVo.ok(map);
            }else {
                //验证失败
                return ResultVo.error("密码错误");
            }
        }
    }
}
