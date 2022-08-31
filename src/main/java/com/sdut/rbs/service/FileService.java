package com.sdut.rbs.service;

import com.sdut.rbs.utils.ResultVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.ParseException;

public interface FileService {
    public ResultVo importTimeTable(MultipartFile file, String startDate,String roomId) throws IOException, ParseException;
}
