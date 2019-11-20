package com.soiiy.platform.utils.exception

/**
 *服务器响应失败
 *@Author ChenRang
 *@Date  2019/11/16 20:42
 */
class RuntimeException(code: Int = 1, message: String = "服务器响应失败！", detail: Any? = null): PlatformException(code, message, detail)