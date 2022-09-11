package com.sdut.rbs.service;

import com.sdut.rbs.utils.ResultVo;

import java.util.Map;

/**
 * 时间选项
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
public interface TimeOptionService {
    public ResultVo getAllTimeOption();
    public ResultVo add(String name);
    public ResultVo remove(int id);
}

