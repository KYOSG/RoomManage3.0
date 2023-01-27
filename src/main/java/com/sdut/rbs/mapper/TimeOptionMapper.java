package com.sdut.rbs.mapper;

import com.sdut.rbs.entity.TimeOptionEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 时间选项
 * 
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@Mapper
public interface TimeOptionMapper {
    public List<TimeOptionEntity> getAllTime();
    public void add(String name);
    public void remove(int id);
}
