package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountLimitStatus
import java.time.LocalDateTime

/**
 *网点数据结构
 *@Author ChenRang
 *@Date  2019/11/16 18:54
 */
open class AccountMarketModel {

    open var id:Long =0

    open var name:String=""

    open var ownerId:Long?=null

    open var picUrl:String?=null

    open var picAlbums:List<String>?=null

    open var address:String=""

    open var longitude:Double=0.0

    open var latitude:Double=0.0

    open var telephone:String?=null

    open var description:String?=null

    open var portionFee:Int=0

    open var companyName:String=""

    open var companyCode:String=""

    open var companyLicUrl:String?=null

    open var bankName:String?=null

    open var bankCode:String?=null

    open var bankAddress:String?=null

    open var limitStatus:AccountLimitStatus=AccountLimitStatus.NORMAL

    open var createAt:LocalDateTime= LocalDateTime.now()

    open var updateAt:LocalDateTime?=null
}