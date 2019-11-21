package com.soiiy.service.web.account.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.soiiy.service.web.account.entity.StoreEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 *商铺mapper
 *@Author ChenRang
 *@Date  2019/11/17 15:42
 */
@Mapper
interface StoreMapper:BaseMapper<StoreEntity>{

    @Select("SELECT * FROM account_stores WHERE market_id=#{marketId}")
    fun selectByMarketId(marketId:Long):List<StoreEntity>
}