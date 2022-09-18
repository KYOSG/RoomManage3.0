package com.sdut.rbs.controller;

import com.sdut.rbs.service.AdmitService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/AdmitController")
@CrossOrigin
public class AdmitController {
    @Resource
    private AdmitService admitService;

    @GetMapping("/getAllAdmit")
    @ResponseBody
    public ResultVo getAllAdmit(){
        return admitService.getAllAdmitList();
    }

    @GetMapping("/access")
    @ResponseBody
    public ResultVo getAllAdmit(@RequestParam("id") int id){
        return admitService.access(id);
    }

    @GetMapping("/deny")
    @ResponseBody
    public ResultVo deny(@RequestParam("id") int id){
        return admitService.deny(id);
    }
}
