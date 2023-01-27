package com.sdut.rbs.controller;

import com.sdut.rbs.service.AdmitService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@CrossOrigin
public class AdmitController {
    @Resource
    private AdmitService admitService;

    @GetMapping("/admit")
    @ResponseBody
    public ResultVo getAllAdmit() {
        return admitService.getAllAdmitList();
    }

    @PutMapping("/admit/access/{id}")
    @ResponseBody
    public ResultVo getAllAdmit(@PathVariable("id") int id) {
        return admitService.access(id);
    }

    @PutMapping("/admit/deny/{id}")
    @ResponseBody
    public ResultVo deny(@PathVariable("id") int id) {
        return admitService.deny(id);
    }
}
