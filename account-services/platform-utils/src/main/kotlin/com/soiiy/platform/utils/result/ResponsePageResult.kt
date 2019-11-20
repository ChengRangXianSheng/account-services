package com.soiiy.platform.utils.result

/**
 *返回分页对象
 *@Author ChenRang
 *@Date  2019/11/16 20:45
 */
class ResponsePageResult<E>(var data: List<E>, var total: Long)