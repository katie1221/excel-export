package com.example.easypoiexceldemo.entity;

import com.baomidou.mybatisplus.annotation.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;

/**
 * @author qzz
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_product")
@ApiModel(value = "Product对象")
public class Product implements Serializable {
    private  static final long serialVersionUID = 1L;
    /**
     * 商品id
     */

    @TableId(value="product_id", type = IdType.AUTO)
    @ApiModelProperty(value = "商品id")
    private Integer product_id;
    /**
     * 商品标题
     */
    @TableField("title")
    @ApiModelProperty("商品标题")
    private String title;
    /**
     * 商品副标题
     */
    @TableField("sub_title")
    @ApiModelProperty("商品副标题")
    private String sub_title;
    /**
     * 商品售价
     */
    @TableField("sale_price")
    @ApiModelProperty("商品售价")
    private Double sale_price;
    /**
     * 创建者
     */
    @TableField(value = "create_by",fill = FieldFill.INSERT)
    @ApiModelProperty(value="创建者")
    private Integer create_by;
    /**
     * 创建时间
     * timezone：是时间设置为东八区，避免时间在转换中有误差
     * @DateTimeFormat：从前台页面将时间类型的数据传入数据库中
     * @JsonFormat：从数据库中获取日期类型的数据传到前台展示
     */
    @TableField(value = "create_time",fill = FieldFill.INSERT)
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty("创建时间")
    private Date create_time;
    /**
     * 修改时间
     */
    @TableField(value = "update_time",fill = FieldFill.UPDATE)
    @ApiModelProperty("修改时间")
    private Date update_time;
    /**
     * 修改者id
     */
    @TableField(value = "update_by",fill = FieldFill.UPDATE)
    @ApiModelProperty(value="修改者id")
    private Integer update_by;

}
