package com.sdut.rbs.utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class ExcelUtils {
    public Workbook workbook;
    public Sheet sheet;
    public boolean isEmpty;//判断文件是不是有内容
    public int totalRows;//总行数/最后一行的位置

    public ExcelUtils(File file,String fileType) throws IOException {
        this.isEmpty = false;
        this.createWorkbook(file,fileType);
    }

    private void createWorkbook(File excelFile,String fileType) throws IOException {

        FileInputStream fileInputStream = null;

        try {
            fileInputStream = new FileInputStream(excelFile);
            if (fileType.equalsIgnoreCase("xls")) {
                this.workbook = new HSSFWorkbook(fileInputStream);               //生成.xls的excel
            } else if (fileType.equalsIgnoreCase("xlsx")) {
                this.workbook = new XSSFWorkbook(fileInputStream);               //生成.xlsx的excel
            }
            if (this.workbook == null){
                this.isEmpty = true;
                return ;
            }

            this.checkWorkbook(this.workbook);

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (workbook !=null){
                workbook.close();           //关闭流
            }
            if (fileInputStream != null){
                fileInputStream.close();    //关闭流
            }
        }
    }

    //读取数据
    public void checkWorkbook(Workbook workbook) {

        int sheetNum = 0;
        this.sheet = workbook.getSheetAt(sheetNum); // 获取sheet

        // 校验sheet是否合法
        if (sheet == null) {
            this.isEmpty = true;
            return;
        }

        Row firstRow = sheet.getRow(sheet.getFirstRowNum()); // 获取第一行，一般是表头

        if (null == firstRow) {
            this.isEmpty = true;
            return;
        }

        this.totalRows = sheet.getPhysicalNumberOfRows();//获取有记录的行数，即：最后有数据的行是第n行，前面有m行是空行没数据，则返回n-m；
    }

    //读取Cell内容并转换成String
    public static String getCellToString(Cell cell) {
        if (cell == null) {
            return null;
        }
        String content = null;
        try {
            switch (cell.getCellType()) {
                case NUMERIC:   //数字或者时间
                    Double doubleValue = cell.getNumericCellValue();
                    // todo:格式化科学计数法，取一位整数(待定)
                    DecimalFormat decimalFormat = new DecimalFormat("0");
                    content = decimalFormat.format(doubleValue);
                    break;
                case STRING:    //字符串
                    content = cell.getStringCellValue();
                    break;
                case BOOLEAN:   //布尔
                    Boolean booleanValue = cell.getBooleanCellValue();
                    content = booleanValue.toString();
                    break;
                case BLANK:     // 空值
                    break;
                case FORMULA:   // 公式
                    content = cell.getCellFormula();
                    break;
                case ERROR:     // 故障
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return content;
    }

    //读取指定位置的单元格
    public String getCertainCell(int rowNum, int columnNum){
        Row row = this.sheet.getRow(rowNum);//获取行
        Cell cell = row.getCell(columnNum);//获取列

        return getCellToString(cell);
    }
}
