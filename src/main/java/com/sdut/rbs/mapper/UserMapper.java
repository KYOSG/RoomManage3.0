package com.sdut.rbs.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sdut.rbs.entity.UsersEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
    public UsersEntity getUserById(String Id);
    public Page<UsersEntity> getAllUserByPage(Page<UsersEntity> page);
    public List<UsersEntity> getAllUser();
    public void addUser(List<UsersEntity> list);
    public UsersEntity getUserByName(String name);
    public void updateUser(Map<String,String> map);
    public void remove(String id);
    public void changePwd(String id,String pwd);
}