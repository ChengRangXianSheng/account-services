package com.soiiy.service.share.account.result

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus
import java.math.BigDecimal

/**
 *网点请求响应参数
 *@Author ChenRang
 *@Date  2019/11/16 19:13
 */
class AccountMarketResult {

    var id:Long? =null

    var name:String?=null

    var picUrl:String?=null

    var picAlbums:List<String>?=null

    var address:String?=null

    var telephone:String?=null

    var description:String?=null

    var longitude:BigDecimal?=null

    var latitude:BigDecimal?=null

    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee:Number?=null

    var companyName:String?=null

    var companyCode:String?=null

    var companyLicUrl:String?=null

    var bankName:String?=null

    var bankCode:String?=null

    var bankAddress:String?=null

    var limtiStatus:AccountLimitStatus?=null
}