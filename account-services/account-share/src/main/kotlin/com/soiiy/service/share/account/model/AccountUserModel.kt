package com.soiiy.service.share.account.model

import com.baomidou.mybatisplus.annotation.TableField
import com.fasterxml.jackson.annotation.JsonFormat
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType
import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import java.util.*

/**
 *账户用户数据结构
 *@Author ChenRang
 *@Date  2019/11/16 18:39
 */
open class AccountUserModel {
    //用户主键ID
    open var id:String = ""

    open var username:String = ""

    open var password:String? = null

    open var type:AccountUserType=AccountUserType.NORMAL

    open var roleId:Int ? = null

    open var name:String = ""

    open var avatarUrl:String? = null

    open var sex:AccountUserSex=AccountUserSex.NONE

    open var mobile:String? = null

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