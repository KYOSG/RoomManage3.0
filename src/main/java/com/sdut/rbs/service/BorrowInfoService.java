package com.sdut.rbs.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sdut.rbs.entity.BorrowInfoEntity;
import com.sdut.rbs.utils.ResultVo;

import java.util.List;
import java.util.Map;

/**
 * 借用记录
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
public interface BorrowInfoService {
    public ResultVo queryRBIByOptions(Map<String,String> map, int pageNum, int pageSize);//该按条件查询教室
    public ResultVo notBorrowedYet(List<String> timeList, String date);
    public int isBorrowed (Map<String,String> map);
    public ResultVo borrow (Map<String,String> map);
    public ResultVo cancel (int id);//借用记录id
    public List<BorrowInfoEntity> getAll();
    public ResultVo getDataByDate(String date,String room);

}

