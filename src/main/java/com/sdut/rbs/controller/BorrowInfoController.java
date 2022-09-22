package com.sdut.rbs.controller;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sdut.rbs.service.BorrowInfoService;
import com.sdut.rbs.utils.ResultVo;

import org.apache.poi.hssf.usermodel.*;
import org.springframework.web.bind.annotation.*;

import com.sdut.rbs.entity.BorrowInfoEntity;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;


/**
 * 借用记录
 *
 * @author kaiyuan
 * @email KaiyuanOSG@outlook.com
 * @date 2022-09-03 09:32:18
 */
@RestController
@RequestMapping("/borrowInfo")
@CrossOrigin
public class BorrowInfoController {
    @Resource
    private BorrowInfoService borrowInfoService;

    //获取借用记录
    @RequestMapping(value = "/queryRBIByOptions",method = RequestMethod.POST)
    public ResultVo queryRBIByOptions(@RequestBody JSONObject jsonObject) {
        Map<String,String> map = new HashMap<>();

        map.put("time",jsonObject.getString("time"));
        map.put("date",jsonObject.getString("date"));
        map.put("roomName",jsonObject.getString("roomName"));

        int pageNum = jsonObject.getIntValue("pageNum");
        int pageSize = jsonObject.getIntValue("pageSize");
        return borrowInfoService.queryRBIByOptions(map,pageNum,pageSize);
    }

    @PostMapping(value = "/notBorrowedYet")
    @ResponseBody
    public ResultVo notBorrowedYet(@RequestBody JSONObject jsonObject){
        JSONArray jsonArray = jsonObject.getJSONArray("time");

        String date = jsonObject.getString("date");

        List<String> timeList = new ArrayList<>();
        for (Object item : jsonArray){
            timeList.add(item.toString());
        }

        return borrowInfoService.notBorrowedYet(timeList,date);
    }

    @PostMapping(value = "/borrow")
    @ResponseBody
    public ResultVo borrowRoom(@RequestBody JSONObject jsonObject){
        JSONArray timeList = jsonObject.getJSONArray("time");

        Map<String,String> map = new HashMap<>();
        map.put("name",jsonObject.getString("name"));

        map.put("date",jsonObject.getString("date"));
        map.put("reason",jsonObject.getString("reason"));
        map.put("applyDate",jsonObject.getString("applyDate"));
        map.put("roomName",jsonObject.getString("roomName"));

        for(Object item : timeList){
            map.put("time",item.toString());
            int code = borrowInfoService.isBorrowed(map);
            switch (code){
                case 0: continue;
                case 1: return ResultVo.error("借用失败，请选择其他时间段");
            }
        }

        for(Object item : timeList){
            map.put("time",item.toString());
            borrowInfoService.borrow(map);
        }

        return ResultVo.ok("借用成功");
    }

    @GetMapping( "/cancel")
    @ResponseBody
    public ResultVo cancel(@RequestParam int id){
        return borrowInfoService.cancel(id);
    }

    @GetMapping(value = "/exportExcel")
    @ResponseBody
    public void exportExcel(HttpServletResponse response){
        List<BorrowInfoEntity> borrowInfoEntityList = borrowInfoService.getAll();

        try {
            HSSFWorkbook hssfWorkbook = exportExcel(borrowInfoEntityList);
            setResponseHeader(response, "教室借用记录");
            OutputStream outputStream = response.getOutputStream();
            hssfWorkbook.write(outputStream);
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName = new String(fileName.getBytes(), "ISO8859-1");
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private HSSFWorkbook exportExcel(List<BorrowInfoEntity> borrowInfoEntityList){

        // 创建一个HSSFWorkbook，对应一个Excel文件
        HSSFWorkbook workbook = new HSSFWorkbook();

        // 在workbook中添加一个sheet,对应Excel文件中的sheet
        String sheetName = "sheet1";
        HSSFSheet sheet = workbook.createSheet(sheetName);
        // 在sheet中添加表头第0行
        HSSFRow row = sheet.createRow(0);
        // 创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 声明列对象
        HSSFCell cell = null;
        // 创建标题

        //处理学院信息

        cell = row.createCell(0);
        cell.setCellValue("日期");
        cell = row.createCell(1);
        cell.setCellValue("时间");
        cell = row.createCell(2);
        cell.setCellValue("教室名称");
        cell = row.createCell(3);
        cell.setCellValue("用途");
        cell = row.createCell(4);
        cell.setCellValue("借用人");
        cell = row.createCell(5);
        cell.setCellValue("申请时间");
        // 创建内容
        int n = borrowInfoEntityList.size();
        for (int i=0;i<n;i++) {
            BorrowInfoEntity item = borrowInfoEntityList.get(i);
            row = sheet.createRow(i+1);
            //创建HSSFCell对象 设置单元格的值
            row.createCell(0).setCellValue(item.getDate());
            row.createCell(1).setCellValue(item.getTime());
            row.createCell(2).setCellValue(item.getRoomName());
            row.createCell(3).setCellValue(item.getReason());
            row.createCell(4).setCellValue(item.getName());
            row.createCell(5).setCellValue(item.getApplyDate());
        }
        return workbook;
    }
}
