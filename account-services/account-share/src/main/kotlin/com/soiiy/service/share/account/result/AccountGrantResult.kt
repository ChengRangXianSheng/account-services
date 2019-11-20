package com.soiiy.service.share.account.result

import com.soiiy.service.share.account.constant.AccountGrantType

/**
 *授权结果集
 *@Author ChenRang
 *@Date  2019/11/18 16:52
 */
open class AccountGrantResult {

    var grant:String ? =null

    var type:AccountGrantType? =null

    var name:String? =null

    var label:String?=null

    var userId:String?=null

    var roleId:Int?=null

    var roleName:String?=null

    var roleLabel:String?=null

    var permissions:List<String> ?= null
}