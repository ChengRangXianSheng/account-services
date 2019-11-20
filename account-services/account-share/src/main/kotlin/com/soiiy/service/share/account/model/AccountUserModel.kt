package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType
import java.time.LocalDateTime

/**
 *账户用户数据结构
 *@Author ChenRang
 *@Date  2019/11/16 18:39
 */
open class AccountUserModel {
    //用户主键ID
    open var id:String = ""

    open var usernmae:String = ""

    open var password:String? = null

    open var type:AccountUserType=AccountUserType.NORMAL

    open var roleId:Int ? = null

    open var name:String = ""

    open var avatarUrl:String? = null

    open var sex:AccountUserSex=AccountUserSex.NONE

    open var mobile:String? = null

    open var limitStatus:AccountLimitStatus=AccountLimitStatus.NORMAL

    open var createAt:LocalDateTime= LocalDateTime.now()

    open var updateAt:LocalDateTime? = null

}