package com.soiiy.service.share.account.constant

import com.baomidou.mybatisplus.annotation.EnumValue

/**
 * 账户授权类型
 * @Author ChenRang
 * @Date  2019/11/16 18:22
 */
enum class AccountGrantType(@EnumValue val code:Int) {

    NONE(0),

    MARKET(1),

    STORE(2);

    companion object {
        fun all():Map<String,String> = mapOf(
                "MARKET" to "网点",
                "STORE" to "店铺"
        )
    }
}