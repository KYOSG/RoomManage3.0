package com.sdut.rbs.dao;

import com.sdut.rbs.entity.OldTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Projectname: RBS-Server-SF
 * @Filename: SpecialDao
 * @Author: SpringForest
 * @Data:2022/10/19 19:31
 * @Description:
 */
@Mapper
public interface SpecialDao {
    public List<OldTable> getOld();

}
