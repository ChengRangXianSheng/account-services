package com.soiiy.service.share.account.query

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.result.AccountMarketResult

/**
 *网点请求表单选项参数
 *@Author ChenRang
 *@Date  2019/11/16 19:03
 */
class AccountMarketQuery {

    var formData: AccountMarketResult?=null

    var formLimits:Map<String,String> = AccountLimitStatus.all()
}