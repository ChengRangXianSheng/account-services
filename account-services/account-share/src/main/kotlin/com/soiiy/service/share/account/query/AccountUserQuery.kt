package com.soiiy.service.share.account.query

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.result.AccountRoleResult
import com.soiiy.service.share.account.result.AccountUserResult

/**
 *
 *@Author ChenRang
 *@Date  2019/11/16 19:10
 */

class AccountUserQuery {

    var formData: AccountUserResult?=null

    var formTypes:Map<String,String> = AccountUserType.all()

    var formRoles:List<AccountRoleResult>?=null

    var formMarkets:String?="list:url"

    var formSex:Map<String,String> =AccountUserSex.all()

    var formLimitStatus:Map<String,String> =AccountLimitStatus.all()
}