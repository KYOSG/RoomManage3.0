package com.sdut.rbs.dao;

import com.sdut.rbs.entity.ReasonEntity;
import com.sdut.rbs.entity.TimeOptionEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface TimeOptionDao {
    public List<TimeOptionEntity> getAllTime();
    public void add(String name);
    public void remove(int id);
}
