package com.soiiy.service.share.account.result

/**
 * 账户树结果集
 * @Author ChenRang
 * @Date  2019/11/20 18:40
 */
class AccountTreeResult {

    var id:Long?=null

    var name:String?=null

    var children:List<AccountTreeResult> ?= null
}