package com.example.easypoiexceldemo.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.easypoiexceldemo.dao.ProductDao;
import com.example.easypoiexceldemo.entity.Product;
import com.example.easypoiexceldemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author qzz
 */
@Service("ProductService")
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductDao productDao;

    /**
     * 添加商品信息
     * @param entity
     * @return
     */
    @Override
    public int add(Product entity) {
        return productDao.insert(entity);
    }

    /**
     * 编辑商品信息
     * @param entity
     * @return
     */
    @Override
    public int update(Product entity) {
        return productDao.updateById(entity);
    }

    /**
     * 获取商品列表信息
     * @param queryWrapper
     * @return
     */
    @Override
    public List<Map<String, Object>> selectList(Wrapper<Product> queryWrapper) {
        return productDao.selectMaps(queryWrapper);
    }

    /**
     * 分页获取商品列表信息
     * @param page
     * @param queryWrapper
     * @return
     */
    @Override
    public IPage<Product> selectPage(Page page, Wrapper<Product> queryWrapper) {
        return productDao.selectMapsPage(page,queryWrapper);
    }

    /**
     * 批量删除商品Map<String, Object>
     * @param ids
     * @return
     */
    @Override
    public int deleteBatchIds(Integer[] ids) {
        return productDao.deleteBatchIds(Arrays.asList(ids));
    }

}
