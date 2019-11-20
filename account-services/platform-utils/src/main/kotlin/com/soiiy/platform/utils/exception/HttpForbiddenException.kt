package com.soiiy.platform.utils.exception

/**
 *授权异常-权限
 *@Author ChenRang
 *@Date  2019/11/16 20:36
 */
class HttpForbiddenException(message: String): PlatformException(403, message)