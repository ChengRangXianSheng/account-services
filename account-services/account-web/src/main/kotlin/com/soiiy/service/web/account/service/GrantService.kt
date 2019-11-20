package com.soiiy.service.web.account.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.query.AccountGrantQuery
import com.soiiy.service.share.account.result.AccountGrantResult
import com.soiiy.service.share.account.result.AccountTreeResult
import com.soiiy.service.web.account.entity.GrantEntity
import com.soiiy.service.web.account.mapper.MarketMapper
import com.soiiy.service.web.account.mapper.RoleMapper
import com.soiiy.service.web.account.mapper.StoreMapper
import com.soiiy.service.web.account.mapper.UserMapper
import com.soiiy.service.web.account.vo.GrantVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sun.rmi.runtime.Log

/**
 *授权业务类
 *@Author ChenRang
 *@Date  2019/11/18 17:21
 */
@Service
class GrantService {
    @Autowired
    lateinit var userMapper:UserMapper
    @Autowired
    lateinit var roleMapper:RoleMapper
    @Autowired
    lateinit var storeMapper:StoreMapper
    @Autowired
    lateinit var marketMapper:MarketMapper

    /**
     * 跳转页面时获取数据
     * @Author: ChenRang
     * @Date: 2019/11/18 17:30
     */
    fun query(type:AccountGrantType):AccountGrantQuery{
        var query=AccountGrantQuery()
        query.formRoles=roleMapper.findAllByGrant(type.code).map { it.result() }
        return query
    }
    /**
     * 查询到权限下的树
     * @Author: ChenRang
     * @Date: 2019/11/20 18:42
     */
    fun tree(markets:String?,stores:String?):List<AccountTreeResult>{
        if(stores != null){
            var storeResults=storeMapper.selectList()
        }
    }

    private fun <T> queryTree(idStr:String):QueryWrapper<T>{
        var ids=idStr.split(",").map { it.toLong() }
        return queryTree(ids)
    }

    private fun <T> queryTree(ids:List<Long>):QueryWrapper<T>{
        val query=QueryWrapper<T>()
        query.eq("limit_status",0).`in`("id",if(ids.isEmpty()) listOf() else ids)
        return query
    }
    /** 
     * 根据userId查看授权
     * @Author: ChenRang 
     * @Date: 2019/11/18 17:47
     */
    fun show(id:String):List<AccountGrantResult>{
        var grants = userMapper.findGrantByUser(id)
        return grants.map { result(it) }
    }
    /**
     * 添加授权
     * @Author: ChenRang
     * @Date: 2019/11/18 17:36
     */
    fun grant(vo:GrantVO):Boolean{
        if(vo.grant.isEmpty()||vo.userId.isEmpty()) return  false
        val type=vo.type.code
        userMapper.revoke(type,vo.grant,vo.userId)
        userMapper.grant(type,vo.grant,vo.userId,vo.label,vo.roleId!!)
        return true
    }

    /**
     * 取消授权
     * @Author: ChenRang
     * @Date: 2019/11/18 17:32
     */
    fun revoke(vo:GrantVO):Boolean{
        var type=vo.type.code
        userMapper.revoke(type,vo.grant,vo.userId)
        return true
    }

    private fun result(entity:GrantEntity):AccountGrantResult{
        var result=AccountGrantResult()
        result.grant=entity.grant
        result.userId=entity.userId
        result.roleId=entity.roleId
        result.label=entity.label
        result.type=entity.type
        result.name=resultGrantName(entity.type,entity.grant)
        val role=roleMapper.findById(entity.roleId)
        if(role !== null){
            result.roleName=role.name
            result.roleLabel=role.label
        }
        return  result
    }
    private fun resultGrantName(type: AccountGrantType,id:String):String{
        return when(type){
            AccountGrantType.STORE->resultStoreName(id)
            AccountGrantType.MARKET->resultMarketName(id)
            else -> "-"
        }
    }

    private fun resultMarketName(id: String): String {
        var market=marketMapper.selectById(id)
        if (market===null) return ""
        return market.name
    }

    private fun resultStoreName(id:String):String{
        var store = storeMapper.selectById(id)
        if (store===null) return ""
        return store.name
    }
}