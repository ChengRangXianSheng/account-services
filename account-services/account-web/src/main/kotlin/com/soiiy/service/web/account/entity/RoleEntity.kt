package com.soiiy.service.web.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.AccountRoleModel
import com.soiiy.service.share.account.result.AccountRoleResult

/**
 *店铺数据库结构映射
 *@Author ChenRang
 *@Date  2019/11/17 20:42
 */
@TableName("account_roles")
class RoleEntity:ShareResponse<AccountRoleResult>,AccountRoleModel(){

    @TableId(type = IdType.AUTO)
    override var id:Int=0
}