package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.constant.AccountUserType

/**
 *角色数据结构
 *@Author ChenRang
 *@Date  2019/11/17 20:51
 */
open class AccountRoleModel {

    open var id:Int=0

    open var type:AccountUserType=AccountUserType.NORMAL

    open var grant:AccountGrantType=AccountGrantType.NONE

    open var name:String=""

    open var label:String=""
}