package com.soiiy.platform.security.config

/**
 * Jwt授权接口
 * @Author ChenRang
 * @Date  2019/11/23 10:57
 */
interface JwtGrantable {

    fun grantId():Any

    fun grantType():Any

    fun grantRole():Any

    fun permissions():List<Any>
}