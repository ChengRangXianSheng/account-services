package com.soiiy.service.share.account.query

import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.result.AccountRoleResult

/**
 *账户授权请求表单
 *@Author ChenRang
 *@Date  2019/11/18 16:45
 */
class AccountGrantQuery {

    var formGrants: String? =null

    var formRoles:List<AccountRoleResult> ? =null

    var formTypes:Map<String,String> = AccountGrantType.all()
}