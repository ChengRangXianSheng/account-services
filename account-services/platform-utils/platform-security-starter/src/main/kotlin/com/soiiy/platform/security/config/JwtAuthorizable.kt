package com.soiiy.platform.security.config

/**
 * Jwt用户权限方法接口
 * @Author ChenRang
 * @Date  2019/11/23 10:50
 */
interface JwtAuthorizable {

    fun primaryValue():Any

    fun permissions():List<String>

}