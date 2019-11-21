package com.soiiy.service.web.account.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.stereotype.Component

/**
 * JWT配置类
 * @Author ChenRang
 * @Date  2019/11/21 18:40
 */

@Component
@ConfigurationProperties(prefix = "jwt")
class JwtConfig {

    var REDIS_TOKEN_KEY_PREFIX:String="TOKEN_"

    var time:Long?=null

    var secret:String?=null

    var prefix:String?=null

    var header:String?=null
}