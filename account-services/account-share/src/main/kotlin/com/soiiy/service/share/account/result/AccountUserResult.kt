package com.soiiy.service.share.account.result

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType

/**
 *用户请求相应参数
 *@Author ChenRang
 *@Date  2019/11/16 19:24
 */
class AccountUserResult {

    var id:String?=null

    var name:String?=null

    var sex:AccountUserSex?=null

    var roleName:String?=null

    var mobile:String?=null

    var limitStatus:AccountLimitStatus?=null

    var username:String?=null

    var password:String?=null

    var roleId:Int?=null

    var roleLabel: String? = null

    var type:AccountUserType?=null

    var avatarUrl:String?=null

    var grant:String?=null

    var permission:List<String>?=null
}