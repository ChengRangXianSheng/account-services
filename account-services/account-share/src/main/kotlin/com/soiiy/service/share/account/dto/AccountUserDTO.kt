package com.soiiy.service.share.account.dto

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType

/**
 * 账户用户DTO
 * @Author ChenRang
 * @Date  2019/11/16 18:35
 */
open class AccountUserDTO {

    var type: AccountUserType? = null

    var roleId:Int ? = null

    var username:String? = null

    var password:String? = null

    var avatarUrl:String? = null

    var name:String? = null

    var sex: AccountUserSex? = null

    var moblie:String? = null

    var storeId:Long? = null

    var limitStatus: AccountLimitStatus? = null

}