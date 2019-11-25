package com.soiiy.platform.security.config

import org.springframework.boot.context.properties.ConfigurationProperties

/**
 * JwtSecurity配置类-设置jwt的基本属性
 * @Author ChenRang
 * @Date  2019/11/23 11:01
 */
@ConfigurationProperties(prefix = "jwt")
class JwtSecurityProperties {

    var redis:Boolean=false

    var redisExpire:Long=3

    var redisPrefix:String="default"

    var time:Long=43200

    var header:String="Authorization"

    var authAdapter:Class<*> ?= null

    var grantAdapter:Class<*> ?= null

    lateinit var permitUrls:List<String>

}