

package com.cloud.common.data.mybatis;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.cloud.common.data.datascope.DataScopeInterceptor;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * @author ygnet
 * @date 2017/10/29
 */
@Configuration
@ConditionalOnClass(MybatisPlusConfig.class)
@MapperScan(basePackages = {"com.cloud.**.mapper"})
public class MybatisPlusConfig {


    /**
     * 分页插件
     *
     * @return PaginationInterceptor
     */
    @Bean
    @ConditionalOnMissingBean
    public PaginationInterceptor paginationInterceptor() {

        return new PaginationInterceptor();
    }

    /**
     * 数据权限插件
     *
     * @return DataScopeInterceptor
     */
    @Bean
    @ConditionalOnMissingBean
    public DataScopeInterceptor dataScopeInterceptor() {
        return new DataScopeInterceptor();
    }

    /**
     * 逻辑删除插件
     *
     * @return LogicSqlInjector
     */
    @Bean
    @ConditionalOnMissingBean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
