package com.soiiy.platform.utils.result

import com.soiiy.platform.utils.exception.PlatformException

/**
 *返回失败对象
 *@Author ChenRang
 *@Date  2019/11/16 20:45
 */
class ResponseErrorResult(
        var errCode: Int = 1,
        var errMsg: String = "服务器响应失败!",
        var errDetail: Any? = null) {

    constructor(e: PlatformException) : this(e.code, e.message, e.detail)

    constructor(e: Throwable) : this(1,e.message ?: "服务器响应失败!", e )

}