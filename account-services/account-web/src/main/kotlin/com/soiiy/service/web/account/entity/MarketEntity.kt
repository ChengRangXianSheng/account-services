package com.soiiy.service.web.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.AccountMarketModel
import com.soiiy.service.share.account.result.AccountMarketResult

/**
 *网点数据库结构映射
 *@Author ChenRang
 *@Date  2019/11/16 21:03
 */
@TableName("account_markets")
class MarketEntity:ShareResponse<AccountMarketResult>,AccountMarketModel(){

    @TableId(type = IdType.AUTO)
    override var id:Long=0
}