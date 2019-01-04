package com.lyl.helloworld.controller;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import org.apache.poi.hssf.util.HSSFColor;
@RestController
public class TestExcel {
    @GetMapping("test")
    public void testExcel1() {
        List<Object> l = new LinkedList<>();
        l.add("zs");
        l.add("ls");
        l.add("we");
        l.add("mz");
        String[] headers = new String[]{"tou1", "tou2", "tou3", "tou4"};
        try {
            OutputStream o = new FileOutputStream(new File("E:\\test.xls"));
            exportDataExcel("nihao", headers, l, o);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    protected static void exportDataExcel(String title, String[] headers, List<Object> mapList, OutputStream out) {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽度字符
        sheet.setDefaultColumnWidth(20);
        //生成一个样式，用来设置标题样式
        HSSFCellStyle style = workbook.createCellStyle();
        //设置这些样式
        style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        //生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        //把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式,用于设置内容样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
        style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
        // 把字体应用到当前的样式
        style2.setFont(font2);
        //产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
             HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }
        for (int i = 0; i < mapList.size(); i++) {
            row = sheet.createRow(i + 1);
            int j = 0;
            HSSFCell cell = row.createCell(j++);
            cell.setCellValue("循环获得值1");
            cell.setCellStyle(style2);
            row.createCell(j++).setCellValue("循环获得值2");
            row.createCell(j++).setCellValue("循环获得值3");
            row.createCell(j++).setCellValue("循环获得值4");
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @GetMapping("test2")
    public  void testExcel2(){
        try {
            FileInputStream file = new FileInputStream("E:\\test.xls");
            List<Map<String, Object>> yanantest = duquexcel(file);
            for (Map<String, Object> item : yanantest) {
                System.out.println(item.get("id") + "," + item.get("name") + "," + item.get("gendar")+","+item.get("time"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static List<Map<String, Object>> duquexcel(InputStream fis) {
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        HSSFWorkbook wb;
        try {
            wb = new HSSFWorkbook(new POIFSFileSystem(fis));
            Sheet sheet = wb.getSheetAt(0);
            // 日期格式化
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/dd");
            // 数字格式化
            DecimalFormat df = new DecimalFormat("##");
            // 循环xls中的每个表格
            Row firstRow = sheet.getRow(0);

            for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                Map<String, Object> rowMap = new HashMap<String, Object>();

                for (int k = 0; k < row.getLastCellNum(); k++) {
                    Cell cell = row.getCell(k);
                    if (null == cell) {
                        continue;
                    }
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            if(HSSFDateUtil.isCellDateFormatted(cell)){
                                rowMap.put(firstRow.getCell(k).getStringCellValue(),sdf.format(HSSFDateUtil.getJavaDate(cell.getNumericCellValue())) );
                                break;
                            }
                            double value_d = cell.getNumericCellValue();
                            long value_l = (long) cell.getNumericCellValue();// cell.getCellStyle()获取样式
                            if (value_d == value_l)
                                rowMap.put(firstRow.getCell(k).getStringCellValue(), String.valueOf(value_l));
                            else
                                rowMap.put(firstRow.getCell(k).getStringCellValue(), String.valueOf(value_d));
                            break;
                        case Cell.CELL_TYPE_STRING:
                            rowMap.put(firstRow.getCell(k).getStringCellValue(), cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_BLANK:
                            break;
                        default:
                            rowMap.put(firstRow.getCell(k).getStringCellValue(), cell.toString());
                            break;
                    }
                }
                // 是否空行
                if (rowMap.size() > 0) {
                    resultList.add(rowMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultList;
    }
}
