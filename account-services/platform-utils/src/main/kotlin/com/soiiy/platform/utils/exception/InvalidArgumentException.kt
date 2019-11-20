package com.soiiy.platform.utils.exception

/**
 *变量无法生效异常
 *@Author ChenRang
 *@Date  2019/11/16 20:42
 */
class InvalidArgumentException(message: String): PlatformException(501, message)