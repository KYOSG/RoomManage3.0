package com.sdut.rbs.service;

import com.sdut.rbs.entity.UsersEntity;
import com.sdut.rbs.utils.ResultVo;

import java.util.List;
import java.util.Map;

public interface UserService {
    public ResultVo checkLogin(String userId, String pwd);
    public ResultVo getAllUser();
    public ResultVo addUser(List<UsersEntity> list);
    public ResultVo getUserByName(String name);
    public ResultVo updateUser(Map<String,String> map);
    public ResultVo remove(String id);
    public ResultVo changePassword(String id,String oldPwd,String newPwd);
}
