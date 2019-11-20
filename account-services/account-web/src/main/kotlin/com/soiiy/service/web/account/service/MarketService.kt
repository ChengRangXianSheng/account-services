package com.soiiy.service.web.account.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.platform.utils.result.ResponseResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.query.AccountMarketQuery
import com.soiiy.service.share.account.result.AccountMarketResult
import com.soiiy.service.web.account.entity.MarketEntity
import com.soiiy.service.web.account.mapper.MarketMapper
import com.soiiy.service.web.account.vo.MarketVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *网点业务层
 *@Author ChenRang
 *@Date  2019/11/17 15:53
 */
@Service
class MarketService {

    @Autowired
    lateinit var mapper:MarketMapper

    fun query():AccountMarketQuery= AccountMarketQuery()
    /**
     * 分页展示相关的网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 16:01
     */
    fun index(keywords:String?,limitStatus: AccountLimitStatus?,page:Long,size:Long):ResponsePageResult<AccountMarketResult>{
        val queryPage=Page<MarketEntity>(page,size)
        val queryWrapper=QueryWrapper<MarketEntity>()

        queryWrapper.eq(limitStatus!==null,"limit_status",limitStatus?.code)
                .and{it.eq("1",1).like(!keywords.isNullOrEmpty(),"company_name",keywords)
                        .or().like(!keywords.isNullOrEmpty(),"name",keywords)}

        var result=mapper.selectPage(queryPage,queryWrapper)
        var datas=result.records.map { result(it) }

        return ResponseResult.page(datas,result.total)
    }
    /**
     * 添加网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 16:47
     */
    fun create():AccountMarketQuery=query()
    /**
     * 根据id查询网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 16:57
     */
    fun show(id:Long):AccountMarketResult{
        var entity= mapper.selectById(id)
        return result(entity)
    }
    /**
     * 添加网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 16:58
     */
    fun store(vo:MarketVO):AccountMarketResult{
        val entity=vo.entity()
        mapper.insert(entity)
        return result(entity)
    }
    /**
     * 编辑网点信息前先默认展示原来的信息
     * @Author: ChenRang
     * @Date: 2019/11/17 17:07
     */
    fun edit(id:Long):AccountMarketQuery{
        var query=query()
        query.formData=show(id)
        return query
    }
    /**
     * 开始修改
     * @Author: ChenRang
     * @Date: 2019/11/17 17:13
     */
    fun update(id:Long,vo:MarketVO):AccountMarketResult{
        val oldEntity=mapper.selectById(id)
        val entity=vo.entity(oldEntity)
        mapper.updateById(entity)
        return result(entity)
    }
    /**
     * 删除用户
     * @Author: ChenRang
     * @Date: 2019/11/17 17:18
     */
    fun destroy(id:Long):Boolean{
        mapper.deleteById(id)
        return true
    }
    /**
     * 结果集转换
     * @Author: ChenRang
     * @Date: 2019/11/17 16:38
     */
    private fun result(entity: MarketEntity):AccountMarketResult{
        return entity.result()
    }
}