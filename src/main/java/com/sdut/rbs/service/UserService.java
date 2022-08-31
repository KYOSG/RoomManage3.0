package com.sdut.rbs.service;

import com.sdut.rbs.utils.ResultVo;

public interface UserService {
    public ResultVo checkLogin(String userId, String pwd);
}
