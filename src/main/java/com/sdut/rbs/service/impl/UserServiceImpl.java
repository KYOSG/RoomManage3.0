package com.sdut.rbs.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.rbs.mapper.UserMapper;
import com.sdut.rbs.entity.UsersEntity;
import com.sdut.rbs.service.UserService;
import com.sdut.rbs.utils.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;
    @Override
    public ResultVo checkLogin(String id, String pwd){
        //查询用户信息
        UsersEntity user = userMapper.getUserById(id);
        //判断
        if (user == null){
            //用户名不存在
            return ResultVo.error("用户名不存在，请联系管理员注册");
        }else {
            //对密码加密
            pwd = DigestUtils.md5DigestAsHex(pwd.getBytes(StandardCharsets.UTF_8));

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
    public ResultVo getAllUser(int pageNum,int pageSize) {
        Page<UsersEntity> page = new Page<>(pageNum,pageSize);

        userMapper.getAllUserByPage(page);

        Map<String,Object> map = new HashMap<>();
        map.put("userList",page.getRecords());
        map.put("total",page.getTotal());
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo addUser(List<UsersEntity> list) {
        try{
            userMapper.addUser(list);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo getUserByName(String username) {
        UsersEntity usersEntity = userMapper.getUserByName(username);
        Map<String,Object> map = new HashMap<>();
        map.put("user",usersEntity);
        return ResultVo.ok(map);
    }

    @Override
    public ResultVo updateUser(Map<String, String> map) {
        try{
            map.put("password",DigestUtils.md5DigestAsHex(map.get("password").getBytes(StandardCharsets.UTF_8)));
            userMapper.updateUser(map);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo remove(String id) {
        try{
            userMapper.remove(id);
        }catch (Exception e){
            System.out.println(e);
            return ResultVo.error(e.toString());
        }
        return ResultVo.ok();
    }

    @Override
    public ResultVo changePassword(String id, String oldPwd, String newPwd) {
        UsersEntity user = userMapper.getUserById(id);
        if (user.getPassword().equals(oldPwd)){
            try {
                newPwd = DigestUtils.md5DigestAsHex(newPwd.getBytes(StandardCharsets.UTF_8));
                System.out.println(newPwd);
                userMapper.changePwd(id,newPwd);
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
