package com.soiiy.service.share.account.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus
import java.math.BigDecimal
import javax.validation.constraints.NotNull

/**
 * 平台网点DTO
 * @Author ChenRang
 * @Date  2019/11/16 18:34
 */
open class AccountMarketDTO {

    @NotNull(message="网点名称不能为空")
    var name:String? = null

    var address:String? = null

    var picUrl:String? = null

    var picAlbums:List<String>? = null

    var longitude: BigDecimal? = null

    var latitude:BigDecimal? = null

    var telephone:String? = null

    var description:String? = null

    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee:Number ? = null

    var companyName:String? = null

    var companyCode:String? = null

    var companyLicUrl:String? = null

    var bankName:String? = null

    var bankCode:String? = null

    var bankAddress:String? = null

    var limitStatus:AccountLimitStatus? = null
}