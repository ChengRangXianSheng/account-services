package com.soiiy.service.share.account.constant

import com.baomidou.mybatisplus.annotation.EnumValue

/**
 * 账户用户性别枚举类
 * @Author ChenRang
 * @Date  2019/11/16 18:19
 */
enum class AccountUserSex(@EnumValue val code:Int ) {

    NONE(0),

    MALE(1),

    FEMALE(2);

    companion object {
        fun all():Map<String,String> = mapOf(
                "NONE" to "保密",
                "MALE" to "男",
                "FEMALE" to "女"
        )
    }
}