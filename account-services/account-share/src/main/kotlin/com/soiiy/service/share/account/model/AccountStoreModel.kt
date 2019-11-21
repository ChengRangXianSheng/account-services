package com.soiiy.service.share.account.model

import com.baomidou.mybatisplus.annotation.TableField
import com.fasterxml.jackson.annotation.JsonFormat
import com.soiiy.service.share.account.constant.AccountLimitStatus
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

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

    open var marketId:Long?=null

    open var categoryId:Long?=null

    open var marketCode:String?=null

    open var portionFee:Int=0

    open var contactName:String?=null

    open var contactMobile:String?=null

    open var telephone:String?=null

    open var description:String?=null

    open var limitStatus:AccountLimitStatus=AccountLimitStatus.NORMAL

    /**
     * 创建时间
     */
    @TableField(value = "created_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    open var createdAt: Date? = Date()

    /**
     * 最后更新时间
     */
    @TableField(value = "updated_at")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    open var updatedAt: Date? = null
}