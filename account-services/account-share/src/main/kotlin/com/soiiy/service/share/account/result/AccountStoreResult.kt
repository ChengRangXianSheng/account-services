package com.soiiy.service.share.account.result

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus

/**
 *商铺请求相应参数
 *@Author ChenRang
 *@Date  2019/11/16 19:19
 */
class AccountStoreResult {

    var id:Long?=null

    var name:String?=null

    var marketId:String?=null

    var marketName:String?=null

    var marketCode:String?=null

    var categoryId:String?=null

    var categoryName:String?=null

    var categoryPortionFee:Int?=null

    var picUrl:String?=null

    var picAlbums:List<String>?=null

    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee:Number?=null

    var contactName:String?=null

    var contactMobile:String?=null

    var telephone:String?=null

    var description:String?=null

    var limitStatus:AccountLimitStatus?=null
}