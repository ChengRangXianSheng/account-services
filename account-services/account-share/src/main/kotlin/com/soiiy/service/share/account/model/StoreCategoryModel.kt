package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountLimitStatus

/**
 *店铺分类数据结构
 *@Author ChenRang
 *@Date  2019/11/20 9:52
 */
open class StoreCategoryModel {

    open var id:Long=0

    open var name:String?=null

    open var picUrl:String?=null

    // 网点分账
    open var portionFee: Int = 0

    open var limitStatus: AccountLimitStatus = AccountLimitStatus.NORMAL

}