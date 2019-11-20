package com.soiiy.service.share.account.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus
import javax.validation.constraints.NotNull

/**
 * 店铺DTO
 * @Author ChenRang
 * @Date  2019/11/16 18:35
 */
open class AccountStoreDTO {

    @NotNull(message="店铺名称不能为空")
    var name:String? = null

    @NotNull(message = "所属网点不能为空")
    var marketId:Long? = null

    @NotNull(message = "所属店铺分类不能为空")
    var categoryId:Long?=null

    var picUrl:String? = null

    var picAlbums:List<String> ? =null

    var marketCode:String? = null

    @NotNull(message = "扣点费用不能为空")
    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee:Number? = null

    var contactName:String? = null

    var contactMoblie:String? = null

    var telephone:String? = null

    var description:String? = null

    var limitStatus: AccountLimitStatus? = null
}