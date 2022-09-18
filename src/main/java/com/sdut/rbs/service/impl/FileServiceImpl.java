package com.sdut.rbs.service.impl;

import com.sdut.rbs.dao.FileDao;
import com.sdut.rbs.entity.TimeTable;
import com.sdut.rbs.service.FileService;
import com.sdut.rbs.utils.ExcelUtils;
import com.sdut.rbs.utils.ResultVo;
import com.sdut.rbs.utils.SFFileUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class FileServiceImpl implements FileService {
    @Resource
    private FileDao fileDao;

    @Override
    public ResultVo importTimeTable(MultipartFile file, String startDate,String roomName) throws IOException, ParseException {
        if (file.isEmpty()) {
            return ResultVo.error("无文件");
        }
        SFFileUtils fileUtils = new SFFileUtils(file);
        //获取文件名及后缀
        String endName = fileUtils.getEndName();

        if (!endName.equals("xls")&&!endName.equals("xlsx")){
            return ResultVo.error("文件类型错误");
        }

        // 创建File流
        File saveFile = fileUtils.getFile();

        ExcelUtils excelUtils = new ExcelUtils(saveFile,endName);
        if (excelUtils.isEmpty){
            return ResultVo.error("Excel为空");
        }
        //解析文件,将单元格内容解析为StringList

        //处理list
        Map<String,String> map = new HashMap<>();
        //获取当前日期
        Date today = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd");

        int dayFix = -1;//周内日期修正（偏移量）

        //处理上课日期,一列中日期均相同
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        date = formatter.parse(startDate);//使用开始日期
        Calendar calendar = Calendar.getInstance();

        for(int i=2;i<=8;i++){//列
            dayFix++;//偏移量+1
            //行
            for(int j=2;j<=6;j++){

                calendar.setTime(date);//设置时间为学期开始日期
                calendar.add(Calendar.DATE, + dayFix);//周内时间偏移

                String temp = excelUtils.getCertainCell(j,i);
                if (temp != null){
                    String[] ttmp = temp.split("/");
                    if (ttmp.length<=10){

                        TimeTable timeTable = new TimeTable(temp);
                        map.put("name",timeTable.getName());

                        map.put("reason",timeTable.getReason());
                        map.put("roomName",roomName);
                        map.put("applyDate",dateFormat.format(today));
                        map.put("isAdmit","1");
                        calendar.add(Calendar.DATE,+7*(timeTable.getStartWeek()-1));//设置开始周

                        try {
                            for(int k=timeTable.getStartWeek();k<=timeTable.getEndWeek();k++){
                                map.put("date", formatter.format(calendar.getTime()));//日期单独处理
                                map.put("time",timeTable.getTime()[0]);

                                fileDao.addTimeTable(map);

                                map.put("time",timeTable.getTime()[1]);

                                fileDao.addTimeTable(map);

                                calendar.add(Calendar.DATE,+7);
                            }
                        }catch (Exception e){
                            System.out.println(e);
                            return ResultVo.error(String.valueOf(e));
                        }
                    }
                }
            }

        }
        FileUtils.delete(saveFile);

        return ResultVo.ok();
    }

    /*@Override
    public ResultVo addPic(MultipartFile file, String name) {
        if (file.isEmpty()) {
            return ResultVo.error("上传失败");
        }
        SFFileUtils fileUtils = new SFFileUtils(file);
        //文件名称
        String fileName = file.getOriginalFilename();
        //允许上传文件名的后缀
        List<String> FILE_WHILE_EXT_LIST = Arrays.asList("JPG","PNG","JPEG");
        Assert.notNull(fileName,"File name can not be empty");
        String fileExtName = fileName.substring(fileName.lastIndexOf(".") + 1);
        if (FILE_WHILE_EXT_LIST.contains(fileExtName.toUpperCase())){
            //上传文件的位置
            //SAVE_PATH  E:/liangd/Java/stmg-front/src/main/webapp
            //IMG_PATH  /upload/
            String filePath = CommonPath.SAVE_PATH.getValue() + CommonPath.IMG_PATH.getValue();
            //全路径 E:/liangd/Java/stmg-front/src/main/webapp/upload/1.jpg
            File dest = new File(filePath + fileName);
            //数据库保存地址 /upload/1.jpg
            String imgPath = CommonPath.IMG_PATH.getValue() + fileName;
            try {
                file.transferTo(dest);
                return ResultVo.ok(imgPath);
            } catch (IOException e) {
                return ResultVo.error("上传失败");
            }
        }
        return ResultVo.error("图片格式不正确");
    }*/
}
