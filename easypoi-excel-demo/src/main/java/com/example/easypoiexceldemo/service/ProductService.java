package com.example.easypoiexceldemo.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.easypoiexceldemo.entity.Product;

import java.util.List;
import java.util.Map;

/**
 * @author qzz
 */
public interface ProductService{

    /**
     * 添加商品信息
     * @param product
     * @return
     */
    int add(Product product);

    /**
     * 编辑商品信息
     * @param product
     * @return
     */
    int update(Product product);

    /**
     * 获取商品列表信息
     * @param queryWrapper
     * @return
     */
    List<Map<String, Object>> selectList(Wrapper<Product> queryWrapper);

    /**
     * 分页获取商品列表信息
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<Product> selectPage(Page page, Wrapper<Product> queryWrapper);

    /**
     * 批量删除商品
     * @param ids
     * @return
     */
    int deleteBatchIds(Integer[] ids);
}
