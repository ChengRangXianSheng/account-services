package com.soiiy.service.web.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableField
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.AccountUserModel
import com.soiiy.service.share.account.result.AccountUserResult

/**
 *用户数据库结构映射
 *@Author ChenRang
 *@Date  2019/11/16 20:53
 */
@TableName("account_users")
class UserEntity:ShareResponse<AccountUserResult>,AccountUserModel(){

    @TableId(type = IdType.ID_WORKER_STR)
    override var id:String=""

    @TableField(exist = false)
    var grant:String?=null
}