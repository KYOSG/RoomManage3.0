package com.sdut.rbs.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface FileMapper {
    public void addTimeTable(Map<String,String> map);

    public void addPic(Map<String,String> map);
}
