package com.soiiy.service.web.account.vo

import com.soiiy.platform.utils.share.ShareRequest
import com.soiiy.service.share.account.dto.AccountUserDTO
import com.soiiy.service.web.account.entity.UserEntity

/**
 *用户请求参数对象
 *@Author ChenRang
 *@Date  2019/11/17 15:49
 */
class UserVO:ShareRequest<UserEntity>,AccountUserDTO()