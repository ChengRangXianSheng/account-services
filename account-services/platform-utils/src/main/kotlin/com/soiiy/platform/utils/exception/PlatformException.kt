package com.soiiy.platform.utils.exception

import java.lang.RuntimeException

/**
 *异常抽象类
 *@Author ChenRang
 *@Date  2019/11/16 20:41
 */
abstract class PlatformException(val code: Int, override val message: String, val detail: Any? = null): RuntimeException()