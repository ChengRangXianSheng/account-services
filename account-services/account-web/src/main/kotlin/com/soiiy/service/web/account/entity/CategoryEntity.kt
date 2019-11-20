package com.soiiy.service.web.account.entity

import com.baomidou.mybatisplus.annotation.IdType
import com.baomidou.mybatisplus.annotation.TableId
import com.baomidou.mybatisplus.annotation.TableName
import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.StoreCategoryModel
import com.soiiy.service.share.account.result.StoreCategoryResult

/**
 *店铺分类映射结构
 *@Author ChenRang
 *@Date  2019/11/20 10:01
 */
@TableName("store_categories")
class CategoryEntity:ShareResponse<StoreCategoryResult>,StoreCategoryModel(){

    @TableId(type = IdType.AUTO)
    override var id:Long=0

}