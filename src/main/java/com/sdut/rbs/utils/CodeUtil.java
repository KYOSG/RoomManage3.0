package com.sdut.rbs.utils;

/**
 * @Projectname: RBS-Server-SF
 * @Filename: CodeUtil
 * @Author: SpringForest
 * @Data:2023/1/17 15:27
 * @Description:
 */

public class CodeUtil {
    private static String patch [] = {"000000","00000","0000","000","00","0",""};

    public static String generator(String in){
        int hash = in.hashCode();
        //密钥
        int encryption = 20230116;
        long res = hash^encryption^System.currentTimeMillis();
        long code = res%1000000;
        code = code < 0 ? -code : code;
        String codeStr = code + "";
        //根据位数自动补0
        return patch[codeStr.length()] + codeStr;
    }
}
