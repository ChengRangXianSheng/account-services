package com.soiiy.service.share.account.result

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus

/**
 *商铺分类结果集
 *@Author ChenRang
 *@Date  2019/11/20 9:54
 */
open class StoreCategoryResult {

    var id:Long?=null

    var name:String?=null

    var picUrl:String?=null

    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee:Number?=null

    var limitStatus: AccountLimitStatus? = null
}