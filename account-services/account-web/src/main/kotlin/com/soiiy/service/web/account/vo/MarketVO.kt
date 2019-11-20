package com.soiiy.service.web.account.vo

import com.soiiy.platform.utils.share.ShareRequest
import com.soiiy.service.share.account.dto.AccountMarketDTO
import com.soiiy.service.web.account.entity.MarketEntity

/**
 *网点请求对象
 *@Author ChenRang
 *@Date  2019/11/17 15:51
 */
class MarketVO:ShareRequest<MarketEntity>,AccountMarketDTO()