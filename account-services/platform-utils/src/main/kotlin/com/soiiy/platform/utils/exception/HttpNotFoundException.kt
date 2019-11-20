package com.soiiy.platform.utils.exception

/**
 *请求找不到异常
 *@Author ChenRang
 *@Date  2019/11/16 20:38
 */
class HttpNotFoundException (message: String): PlatformException(404, message)