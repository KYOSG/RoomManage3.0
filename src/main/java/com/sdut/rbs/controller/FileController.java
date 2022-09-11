package com.sdut.rbs.controller;

import com.sdut.rbs.service.FileService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.text.ParseException;

@RequestMapping("/file")
@CrossOrigin
@RestController
public class FileController {
    @Resource
    private FileService fileService;

    @PostMapping("/importTimeTable")
    @ResponseBody
    public ResultVo importTimeTable(MultipartFile file,@RequestPart("startDate") String startDate, @RequestPart("roomId") String roomId) throws IOException, ParseException {
        return fileService.importTimeTable(file,startDate,roomId);
    }

    /*@PostMapping("/addPic")
    @ResponseBody
    public ResultVo addPic(MultipartFile file,@RequestPart("roomName") String name) throws IOException, ParseException {
        return fileService.addPic(file,name);
    }*/
}
