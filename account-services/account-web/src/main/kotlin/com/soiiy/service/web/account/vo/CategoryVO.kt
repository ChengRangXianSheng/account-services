package com.soiiy.service.web.account.vo

import com.soiiy.platform.utils.share.ShareRequest
import com.soiiy.service.share.account.dto.StoreCategoryDTO
import com.soiiy.service.share.account.model.StoreCategoryModel
import com.soiiy.service.web.account.entity.CategoryEntity

/**
 *分类请求映射
 *@Author ChenRang
 *@Date  2019/11/20 10:03
 */
class CategoryVO:ShareRequest<CategoryEntity>,StoreCategoryDTO()