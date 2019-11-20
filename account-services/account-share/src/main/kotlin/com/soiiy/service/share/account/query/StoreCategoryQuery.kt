package com.soiiy.service.share.account.query

import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.result.StoreCategoryResult

/**
 *店铺分类封装表单
 *@Author ChenRang
 *@Date  2019/11/20 9:57
 */
class StoreCategoryQuery {

    var formData:StoreCategoryResult? = null

    var formLimitStatus:Map<String,String> = AccountLimitStatus.all()
}