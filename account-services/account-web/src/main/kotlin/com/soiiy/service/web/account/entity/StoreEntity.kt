package com.soiiy.service.web.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.AccountStoreModel
import com.soiiy.service.share.account.result.AccountStoreResult

/**
 *店铺数据库结构映射
 *@Author ChenRang
 *@Date  2019/11/16 21:06
 */
@TableName("account_stores")
class StoreEntity:ShareResponse<AccountStoreResult>,AccountStoreModel(){

    @TableId(type = IdType.AUTO)
    override var id:Long = 0
}