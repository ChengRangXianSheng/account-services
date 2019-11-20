package com.soiiy.platform.utils.exception

/**
 *认证异常登录
 *@Author ChenRang
 *@Date  2019/11/16 20:41
 */
class HttpUnauthorizedException(message: String = "需要登陆！"): PlatformException(401, message)