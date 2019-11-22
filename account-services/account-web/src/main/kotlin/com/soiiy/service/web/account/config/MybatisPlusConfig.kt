package com.soiiy.service.web.account.config

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

/**
 *MybatisPlus配置类
 *@Author ChenRang
 *@Date  2019/11/16 20:12
 */
@Configuration
class MybatisPlusConfig {

    /**
     * 配置分页插件
     * @Author: ChenRang
     * @Date: 2019/11/16 20:15
     */
    @Bean
    fun paginationInterceptor():PaginationInterceptor= PaginationInterceptor()



}