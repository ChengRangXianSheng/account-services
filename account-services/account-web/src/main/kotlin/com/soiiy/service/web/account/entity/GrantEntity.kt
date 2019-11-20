package com.soiiy.service.web.account.entity

import com.soiiy.platform.utils.share.ShareResponse
import com.soiiy.service.share.account.model.AccountGrantModel
import com.soiiy.service.share.account.result.AccountGrantResult

/**
 *授权表数据库结构映射
 *@Author ChenRang
 *@Date  2019/11/18 17:06
 */
class GrantEntity:ShareResponse<AccountGrantResult>,AccountGrantModel()