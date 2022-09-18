package com.sdut.rbs.service;

import com.sdut.rbs.entity.AdmitEntity;
import com.sdut.rbs.utils.ResultVo;

import java.util.List;

public interface AdmitService {
    public ResultVo getAllAdmitList();
    public ResultVo access(int id);
    public ResultVo deny(int id);
}
