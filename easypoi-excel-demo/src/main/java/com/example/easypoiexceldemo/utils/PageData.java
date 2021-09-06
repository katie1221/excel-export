package com.example.easypoiexceldemo.utils;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 分页工具类
 * @author qzz
 */
@Data
public class PageData<T> implements Serializable {

    private  static final long serialVersionUID = 1L;

    /**
     * 总记录数
     */
    private int total;
    /**
     * 列表数据
     */
    private List<T> list;

    public PageData(List<T> list, long total){
        this.list = list ;
        this.total = (int) total;
    }

}
