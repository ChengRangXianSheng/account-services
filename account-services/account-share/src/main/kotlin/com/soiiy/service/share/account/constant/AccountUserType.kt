package com.soiiy.service.share.account.constant

import com.baomidou.mybatisplus.annotation.EnumValue

/**
 * 账户用户类型
 * @Author ChenRang
 * @Date  2019/11/16 18:21
 */
enum class AccountUserType(@EnumValue val code:Int) {

    NORMAL(0),

    ADMIN(1),

    MARKET(2);

    companion object {
        fun all():Map<String,String> = mapOf(
                "NORMAL"  to "普通用户",
                "ADMIN" to "管理员",
                "MARKET" to "网点用户"
        )
    }
}