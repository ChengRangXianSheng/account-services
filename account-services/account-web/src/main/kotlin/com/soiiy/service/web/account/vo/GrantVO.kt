package com.soiiy.service.web.account.vo

import com.soiiy.platform.utils.share.ShareRequest
import com.soiiy.service.share.account.dto.AccountGrantDTO
import com.soiiy.service.web.account.entity.GrantEntity

/**
 *授权请求对象
 *@Author ChenRang
 *@Date  2019/11/18 17:11
 */
class GrantVO:ShareRequest<GrantEntity>,AccountGrantDTO()