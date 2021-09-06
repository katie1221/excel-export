package com.example.easypoiexceldemo.utils;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.enmus.ExcelType;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.BeanUtils;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * excel工具类
 * @author qzz
 */
public class ExcelUtils {

    /**
     * Excel导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String fileName, Collection<?> list, Class<?> pojoClass) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            //当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            fileName = df.format(new Date());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(fileName, fileName, ExcelType.HSSF), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }

    /**
     * Excel导出，先sourceList转换成List<targetClass>，再导出
     *
     * @param response      response
     * @param fileName      文件名
     * @param sourceList    原数据List
     * @param targetClass   目标对象Class
     */
    public static void exportExcelToTarget(HttpServletResponse response, String fileName, Collection<?> sourceList, Class<?> targetClass) throws Exception {
        List targetList = new ArrayList<>(sourceList.size());
        for (Object source : sourceList) {
            Object target = targetClass.newInstance();
            BeanUtils.copyProperties(source, target);
            targetList.add(target);
        }
        exportExcel(response, fileName, targetList, targetClass);
    }


    /**
     * Excel导出----设置title---sheetName---要求Collection<?> list是Class<?> pojoClass类型的
     *
     * @param response      response
     * @param fileName      文件名
     * @param list          数据List
     * @param pojoClass     对象Class
     */
    public static void exportExcel(HttpServletResponse response, String title, String sheetName, String fileName, Collection<?> list, Class<?> pojoClass) throws IOException {
        if (StringUtils.isBlank(fileName)) {
            //当前日期
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            fileName = df.format(new Date());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams(title, sheetName, ExcelType.HSSF), pojoClass, list);
        response.setCharacterEncoding("UTF-8");
        response.setHeader("content-Type", "application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8") + ".xls");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        out.flush();
    }
}
