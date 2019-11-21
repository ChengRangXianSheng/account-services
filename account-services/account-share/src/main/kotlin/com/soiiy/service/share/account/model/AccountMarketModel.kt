package com.soiiy.service.share.account.model

import com.fasterxml.jackson.annotation.JsonFormat
import com.soiiy.service.share.account.constant.AccountLimitStatus
import org.springframework.format.annotation.DateTimeFormat
import java.util.*

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

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    open var createdAt: Date? =Date()

    /**
     * 最后更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    open var updatedAt: Date? = null
}