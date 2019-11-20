package com.soiiy.service.web.account.vo

import com.soiiy.platform.utils.share.ShareRequest
import com.soiiy.service.share.account.dto.AccountStoreDTO
import com.soiiy.service.web.account.entity.StoreEntity

/**
 *店铺请求对象
 *@Author ChenRang
 *@Date  2019/11/17 15:53
 */
class StoreVO:ShareRequest<StoreEntity>,AccountStoreDTO()