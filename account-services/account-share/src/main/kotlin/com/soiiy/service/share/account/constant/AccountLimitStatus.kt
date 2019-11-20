package com.soiiy.service.share.account.constant

import com.baomidou.mybatisplus.annotation.EnumValue

/**
 * 账户限制类型
 * @Author ChenRang
 * @Date  2019/11/16 18:24
 */
enum class AccountLimitStatus(@EnumValue val code:Int) {

    NORMAL(0),

    CLOSED(1);

    companion object {
        fun all():Map<String,String> = mapOf(
                "NORMAL" to "正常",
                "CLOSED" to "停用"
        )
    }
}