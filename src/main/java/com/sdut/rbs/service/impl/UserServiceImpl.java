package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.UserDAO;
import com.sdut.rbs.entity.UsersEntity;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserDAO userDAO;
    @Override
    public ResultVo checkLogin(String id, String pwd){
        //查询用户信息
        UsersEntity user = userDAO.getUserById(id);
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

    @Override
    public ResultVo getAllUser() {
        List<UsersEntity> usersEntities = userDAO.getAllUser();
        Map<String,Object> map = new HashMap<>();
        map.put("userList",usersEntities);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo addUser(List<UsersEntity> list) {
        try{
            userDAO.addUser(list);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo getUserByName(String name) {
        UsersEntity usersEntity = userDAO.getUserByName(name);
        Map<String,Object> map = new HashMap<>();
        map.put("user",usersEntity);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo updateUser(Map<String, String> map) {
        try{
            userDAO.updateUser(map);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(String id) {
        try{
            userDAO.remove(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo changePassword(String id, String oldPwd, String newPwd) {
        UsersEntity user = userDAO.getUserById(id);
        if (user.getPassword().equals(oldPwd)){
            try {
                userDAO.changePwd(id,newPwd);
            }catch (Exception e){
                System.out.println(e);
                return ResultVo.error();
            }
        }else {
            return ResultVo.error("原密码错误");
        }
        return ResultVo.ok();
    }
}
