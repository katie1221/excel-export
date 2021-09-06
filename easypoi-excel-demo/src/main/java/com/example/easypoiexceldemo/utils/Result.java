package com.example.easypoiexceldemo.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

/**
 * 响应数据
 * @author qzz
 */
@ApiModel("Result响应")
public class Result<T> implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 编码 0：成功 其他值代表失败
     */
    @ApiModelProperty(value="编码 0：成功 其他值代表失败")
    private int code = 0;

    /**
     * 消息内容
     */
    @ApiModelProperty(value="消息内容")
    private String msg = "success";

    /**
     * 响应数据
     */
    @ApiModelProperty(value="响应数据")
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Result<T> ok (T data){
        this.setData(data);
        return this;
    }

    public Result<T> error(){
        this.code = 500;
        this.msg="网络出错，请联系系统管理员";
        return this;
    }

    public Result<T> error(int code,String msg){
        this.code = code;
        this.msg=msg;
        return this;
    }

    public boolean success(){
        return code == 0 ? true : false;
    }
}
