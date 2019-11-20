package com.soiiy.service.web.account.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.platform.utils.result.ResponseResult
import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.query.AccountStoreQuery
import com.soiiy.service.share.account.result.AccountStoreResult
import com.soiiy.service.web.account.entity.StoreEntity
import com.soiiy.service.web.account.entity.UserEntity
import com.soiiy.service.web.account.mapper.CategoryMapper
import com.soiiy.service.web.account.mapper.MarketMapper
import com.soiiy.service.web.account.mapper.StoreMapper
import com.soiiy.service.web.account.mapper.UserMapper
import com.soiiy.service.web.account.vo.GrantVO
import com.soiiy.service.web.account.vo.StoreVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *店铺业务层
 *@Author ChenRang
 *@Date  2019/11/17 18:04
 */
@Service
class StoreService {

    @Autowired
    lateinit var mapper:StoreMapper

    @Autowired
    lateinit var marketMapper:MarketMapper

    @Autowired
    lateinit var userMapper: UserMapper

    @Autowired
    lateinit var grantService:GrantService

    @Autowired
    lateinit var categoryMapper: CategoryMapper

    /**
     * 跳转到查询
     * @Author: ChenRang
     * @Date: 2019/11/17 18:06
     */
    fun query():AccountStoreQuery= AccountStoreQuery()

    /**
     * 默认分页展示
     * @Author: ChenRang
     * @Date: 2019/11/17 18:08
     */
    fun index(keywords:String?,limitStatus: AccountLimitStatus?,marketId:String?,categoryId:Long?,page:Long,size:Long):ResponsePageResult<AccountStoreResult>{
        var queryPage= Page<StoreEntity>(page,size)
        var queryWrapper=QueryWrapper<StoreEntity>()
        var queryMapper= mapOf("limit_status" to (limitStatus?.code?:0),"market_id" to marketId,"category_id" to categoryId)

        queryWrapper.allEq(queryMapper,false).
                and { it.eq("1",1).like(!keywords.isNullOrEmpty(),"name",keywords) }

        var result = mapper.selectPage(queryPage, queryWrapper)
        var datas=result.records.map { result(it) }

        return ResponseResult.page(datas,result.total)
    }
    /**
     * 根据id查询
     * @Author: ChenRang
     * @Date: 2019/11/17 18:32
     */
    fun show(id:Long):AccountStoreResult{
        var entity = mapper.selectById(id)
        return result(entity)
    }
    /**
     * 授权操作
     * @Author: ChenRang
     * @Date: 2019/11/17 19:17
     */
    fun grant(grants:String,page:Long,size:Long):ResponsePageResult<AccountStoreResult>{
        val queryPage=Page<StoreEntity>(page,size)
        var queryWrapper=QueryWrapper<StoreEntity>()

        val isGrantStore=grants.startsWith("store:")
        var itemGrantIds=grants.substringAfter(":").split(",").map { it.toLong() }

        queryWrapper.eq("limit_status",0).`in`( if (isGrantStore) "id" else "market_id",itemGrantIds)

        var result=mapper.selectPage(queryPage,queryWrapper)

        return ResponseResult.page(result.records.map { result(it) },result.total)
    }
    /**
     * 去查询时封装表单
     * @Author: ChenRang
     * @Date: 2019/11/17 18:33
     */
    fun create():AccountStoreQuery=query()
    /**
     * 新增店铺
     * @Author: ChenRang
     * @Date: 2019/11/17 18:34
     */
    fun store(vo:StoreVO):AccountStoreResult{
        var entity = vo.entity()
        mapper.insert(entity)
        //新增店铺时授权
        grantStore(entity)
        return result(entity)
    }
    /**
     * 编辑店铺时填充数据
     * @Author: ChenRang
     * @Date: 2019/11/17 18:52
     */
    fun edit(id:Long):AccountStoreQuery{
        val query=query()
        query.formData=show(id)
        return query
    }
    /**
     * 修改店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 18:53
     */
    fun update(id:Long,vo: StoreVO):AccountStoreResult{
        val oldEntity=mapper.selectById(id)
        val entity=vo.entity(oldEntity)
        mapper.updateById(entity)
        grantStore(entity)
        return result(entity)
    }
    /**
     * 删除店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 18:56
     */
    fun destroy(id:Long):Boolean{
        mapper.deleteById(id)
        return true
    }
    /**
     * 给用户添加授权
     * @Author: ChenRang
     * @Date: 2019/11/17 18:57
     */
    private fun grantStore(entity: StoreEntity){
        if(entity.contactMobile.isNullOrEmpty()) return

        var user=userMapper.selectByMobile(entity.contactMobile!!)
        var vo= GrantVO()

        if(user===null){
            val userEntity=UserEntity()
            userEntity.name=entity.contactName.orEmpty()
            userEntity.avatarUrl=entity.picUrl
            userEntity.mobile=entity.contactMobile
            userEntity.usernmae=entity.contactMobile!!
            userEntity.type=AccountUserType.NORMAL
            userMapper.insert(userEntity)
            vo.userId=userEntity.id
        }else{
            vo.userId=user.id
        }
        vo.type=AccountGrantType.STORE
        vo.roleId=301
        vo.label=entity.contactName.orEmpty()
        vo.grant=entity.id.toString()

        grantService.grant(vo)
    }
    /**
     * 结果类型转换
     * @Author: ChenRang
     * @Date: 2019/11/17 18:28
     */
    fun result(entity:StoreEntity):AccountStoreResult{
        var result=entity.result()

        var market=marketMapper.selectById(entity.markerId)
        var category=categoryMapper.selectById(entity.categoryId)

        result.marketName = if (market === null) null else market.name
        result.categoryName = if (category === null) null else category.name
        result.categoryPortionFee = if (category === null) null else category.portionFee

        return result
    }
}