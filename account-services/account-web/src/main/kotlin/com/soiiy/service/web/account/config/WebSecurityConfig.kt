package com.soiiy.service.web.account.config

import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
//import org.springframework.security.config.annotation.web.builders.HttpSecurity
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
//import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * SpringSecurity配置类
 * @Author ChenRang
 * @Date  2019/11/21 17:53
 */
//@EnableWebSecurity
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//class WebSecurityConfig: WebSecurityConfigurerAdapter(), WebMvcConfigurer {
//
//
//    override fun configure(http: HttpSecurity?) {
//        http!!.csrf().disable()
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//                .authorizeRequests()
//                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
//                .antMatchers(HttpMethod.POST).authenticated()
//                .antMatchers(HttpMethod.DELETE).authenticated()
//                .antMatchers(HttpMethod.GET).authenticated()
//                .antMatchers(HttpMethod.PUT).authenticated()
//
//
//    }
//
//}