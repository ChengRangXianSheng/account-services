package com.soiiy.service.share.account.dto

import com.soiiy.service.share.account.constant.AccountGrantType

/**
 * 账户授权类型
 * @Author ChenRang
 * @Date  2019/11/16 18:33
 */
open class AccountGrantDTO {

    lateinit var type: AccountGrantType

    lateinit var grant: String

    lateinit var userId: String

    var label:String? = null

    var roleId:Int? = null

}