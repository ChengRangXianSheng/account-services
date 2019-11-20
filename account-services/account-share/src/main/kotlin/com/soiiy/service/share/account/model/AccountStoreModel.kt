package com.soiiy.service.share.account.model

import com.soiiy.service.share.account.constant.AccountLimitStatus
import java.time.LocalDateTime

/**
 *商户数据结构
 *@Author ChenRang
 *@Date  2019/11/16 18:48
 */
open class AccountStoreModel {

    open var id:Long = 0

    open var name:String = ""

    open var picUrl:String?=null

    open var picAlbums:List<String>?=null

    open var markerId:Long?=null

    open var categoryId:Long?=null

    open var markerCode:String?=null

    open var portionFee:Int=0

    open var contactName:String?=null

    open var contactMobile:String?=null

    open var telephone:String?=null

    open var description:String?=null

    open var limitStatus:AccountLimitStatus=AccountLimitStatus.NORMAL

    open var createAt:LocalDateTime= LocalDateTime.now()

    open var updateAt:LocalDateTime?=null
}