package com.soiiy.service.share.account.query

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.result.AccountStoreResult

/**
 *商铺请求表单选项参数
 *@Author ChenRang
 *@Date  2019/11/16 19:05
 */
class AccountStoreQuery {

    var formData: AccountStoreResult?=null

    var formMarkets:String?="list:url"

    var formCategories:String?="map:-:url"

    var formLists:Map<String,String> = AccountLimitStatus.all()
}