package com.soiiy.platform.utils.result

/**
 *整合返回结果集
 *@Author ChenRang
 *@Date  2019/11/16 20:48
 */
object ResponseResult {

    const val SUCCESS: String = "SUCCESS!"

    fun fail(e: Throwable): ResponseErrorResult {
        return ResponseErrorResult(e)
    }

    fun <E> page(data: List<E>,total: Long): ResponsePageResult<E> {
        return ResponsePageResult(data, total)
    }

}