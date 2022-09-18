package com.sdut.rbs.dao;

import com.sdut.rbs.entity.AdmitEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AdmitDao {
    public List<AdmitEntity> getAllAdmitList();
    public void access(int id);
    public void deny(int id);
}
