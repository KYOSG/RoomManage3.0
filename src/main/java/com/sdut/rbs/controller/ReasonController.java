package com.sdut.rbs.controller;

import com.sdut.rbs.service.ReasonService;
import com.sdut.rbs.utils.ResultVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


/**
 * 借用原因
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@RestController
@CrossOrigin
public class ReasonController {
    @Resource
    private ReasonService reasonService;

    @GetMapping("/reason")
    @ResponseBody
    public ResultVo getAllReasonOption() {
        return reasonService.getAllReasonOption();
    }

    @PostMapping("/reason/{name}")
    @ResponseBody
    public ResultVo addReason(@PathVariable String name) {
        return reasonService.add(name);
    }

    @DeleteMapping("/reason")
    @ResponseBody
    public ResultVo removeReason(@RequestParam int id) {
        return reasonService.remove(id);
    }

}
