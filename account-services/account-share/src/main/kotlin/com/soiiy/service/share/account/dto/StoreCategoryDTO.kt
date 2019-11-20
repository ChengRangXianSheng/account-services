package com.soiiy.service.share.account.dto

import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.annotation.JsonSerialize
import com.soiiy.platform.utils.json.DecimalJsonType
import com.soiiy.service.share.account.constant.AccountLimitStatus
import javax.validation.constraints.NotNull

/**
 * 店铺分类
 * @Author ChenRang
 * @Date  2019/11/20 9:50
 */
open class StoreCategoryDTO {

    @NotNull(message = "店铺分类名称不能为空")
    var name:String? = null

    var picUrl:String? = null

    @NotNull(message = "建议扣点费用不能为空")
    @JsonSerialize(using = DecimalJsonType.CoinSerializer::class)
    @JsonDeserialize(using = DecimalJsonType.CoinDeserializer::class)
    var portionFee: Number? = null

    var limitStatus: AccountLimitStatus? = null
}