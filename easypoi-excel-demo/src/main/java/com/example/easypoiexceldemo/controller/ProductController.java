package com.example.easypoiexceldemo.controller;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.easypoiexceldemo.entity.Product;
import com.example.easypoiexceldemo.excel.ProductExcel;
import com.example.easypoiexceldemo.service.ProductService;
import com.example.easypoiexceldemo.utils.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.beans.Introspector;
import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Stream;


/**
 * @author qzz
 */
@Api(tags = "商品管理API")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 分页获取商品列表
     */
    @PostMapping("/page")
    @ApiOperation("分页获取商品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage",value = "当前页码",paramType = "query",dataType = "int"),
            @ApiImplicitParam(name = "pageSize",value = "每页显示个数",paramType = "query",dataType = "int")
    })
    public Result page(@RequestBody Product product, @RequestParam Integer currentPage, @RequestParam Integer pageSize){

        Page<Product> page = new Page<>(currentPage,pageSize);
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        //分页查看商品列表
        IPage<Product> pageInfo = productService.selectPage(page,queryWrapper);
        //返回结果集
        return new Result().ok(new PageData<>(pageInfo.getRecords(),pageInfo.getTotal()));
    }

    /**
     * 根据条件获取列表
     */
    @GetMapping("/list")
    @ApiOperation("根据条件获取列表")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "title",value = "商品标题",paramType = "query",dataType = "String")
    })
    public Result getProductList(@RequestParam String title, HttpServletRequest request){
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        queryWrapper.like(Tools.notEmpty(title),"title",title);
        //此方法 sql有结果，但是返回数据除了title列，其他列都是null
        List<Map<String, Object>> list = productService.selectList(queryWrapper);
        return new Result().ok(list);
    }

    /**
     * 添加商品信息
     */
    @PostMapping("add")
    @ApiOperation("添加商品信息")
    public Result addProduct(@RequestBody Product product){
        product.setCreate_by(1);
        product.setCreate_time(new Date());
        int n = productService.add(product);
        if(n>0){
            return new Result().ok(product.getProduct_id());
        }else{
            return new Result().error(400,"添加商品失败");
        }
    }

    /**
     * 编辑商品信息
     */
    @PostMapping("update")
    @ApiOperation("编辑商品信息")
    public Result updateProduct(@RequestBody Product product){
        product.setUpdate_by(1);
        product.setUpdate_time(new Date());
        int n = productService.update(product);
        if(n>0){
            return new Result();
        }else{
            return new Result().error(400,"编辑商品失败");
        }
    }

    /**
     * 根据ids删除商品信息
     */
    @PostMapping("del")
    @ApiOperation("根据ids删除商品信息")
    public Result delProduct(@RequestBody Integer[] ids){
        int n = productService.deleteBatchIds(ids);
        if(n>0){
            return new Result();
        }else{
            return new Result().error(400,"删除商品失败");
        }
    }

    /**
     * excel导出
     * @param response
     */
    @GetMapping("/excel")
    @ApiOperation("根据检索条件查询列表，导出excel")
    public void export( HttpServletResponse response) throws IOException {
        //根据条件检索列表
        QueryWrapper<Product> queryWrapper = new QueryWrapper();
        List<Map<String, Object>> list = productService.selectList(queryWrapper);

        List<ProductExcel> productList = MapToEntity.setList(list,ProductExcel.class);
        //导出excel
        ExcelUtils.exportExcel(response,null,productList, ProductExcel.class);
    }


}
