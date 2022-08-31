package com.sdut.rbs.dao;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FileDao {
    public void addTimeTable(Map<String,String> map);
}
