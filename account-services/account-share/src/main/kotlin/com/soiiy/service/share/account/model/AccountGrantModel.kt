package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountGrantType

/**
 *账户授权数据结构
 *@Author ChenRang
 *@Date  2019/11/18 16:23
 */
open class AccountGrantModel {

    open var type:AccountGrantType = AccountGrantType.NONE

    open var grant:String = ""

    open var label:String? = null

    open var userId:String = ""

    open var roleId:Int = 0
}