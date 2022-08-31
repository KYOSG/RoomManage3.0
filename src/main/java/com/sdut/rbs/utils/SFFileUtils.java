package com.sdut.rbs.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/*
* <dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi</artifactId>
    <version>5.2.2</version>
  </dependency>
  <dependency>
    <groupId>org.apache.poi</groupId>
    <artifactId>poi-ooxml</artifactId>
    <version>5.2.2</version>
  </dependency>
* */
public class SFFileUtils {
    private String FileName;
    private String endName;
    private MultipartFile multipartFile;
    private File file;

    public SFFileUtils(MultipartFile rawFile) {
        this.FileName = rawFile.getOriginalFilename();
        this.endName = creatFileEndName(rawFile);
        this.multipartFile = rawFile;

        //选择用缓冲区来实现这个转换即使用java 创建的临时文件 使用 MultipartFile.transferto()方法 。
        /**
         * MultipartFile 转 File
         */
        File file = null;
        try {
            String originalFilename = multipartFile.getOriginalFilename();
            String[] filename = originalFilename.split("\\.");
            file = File.createTempFile(filename[0], filename[1] + ".");
            multipartFile.transferTo(file);
            file.deleteOnExit();
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.file = file;
    }

    public File getFile() {
        return file;
    }

    public String getFileName() {
        return this.FileName;
    }

    public String getEndName(){
        return this.endName;
    }

    public String getPostfix() {
        return this.endName;
    }

    /*
     * 获取文件后缀
     * */
    private String creatFileEndName(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String[] temp = fileName.split("\\.");
        int strL = temp.length;
        return temp[strL - 1];
    }
}
