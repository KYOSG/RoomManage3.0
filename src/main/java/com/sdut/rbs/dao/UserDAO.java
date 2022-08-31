package com.sdut.rbs.dao;

import com.sdut.rbs.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDAO {
    public User checkLogin(String Id);
}