package com.example.easypoiexceldemo.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 新建一个MybatisPlus配置类 返回一个分页拦截器
 * @author qzz
 */
@Configuration
@MapperScan(basePackages = {"com.example.easypoiexceldemo.dao"})
public class MybatisPlusConfig {

    /**
     * Mybatis Plus 3.4.0版本及其之后的版本采用新的分页：
     * 新的分页插件,一缓和二缓遵循mybatis的规则,需要设置 MybatisConfiguration#useDeprecatedExecutor = false 避免缓存出现问题(该属性会在旧插件移除后一同移除)
     * @return
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        //向Mybatis过滤器链中添加分页拦截器
        mybatisPlusInterceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        //还可以添加其他的拦截器
        return mybatisPlusInterceptor;
    }
}
