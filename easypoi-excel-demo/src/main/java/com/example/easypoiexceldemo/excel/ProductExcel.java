package com.example.easypoiexceldemo.excel;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.util.Date;

/**
 * 商品
 * @author qzz
 */
@Data
public class ProductExcel {

    /**
     * 商品id
     */
    @Excel(name = "商品id")
    private Integer product_id;
    /**
     * 商品标题
     */
    @Excel(name="商品标题")
    private String title;
    /**
     * 商品副标题
     */
    @Excel(name="商品副标题")
    private String sub_title;
    /**
     * 商品售价
     */
    @Excel(name="商品售价")
    private Double sale_price;
    /**
     * 创建者
     */
    @Excel(name="创建者")
    private Integer create_by;
    /**
     * 创建时间
     */
    @Excel(name="创建时间", format = "yyyy-MM-dd")
    private Date create_time;
    /**
     * 修改时间
     */
    @Excel(name="修改时间", format = "yyyy-MM-dd")
    private Date update_time;
    /**
     * 修改者id
     */
    @Excel(name="修改者id")
    private Integer update_by;
}
