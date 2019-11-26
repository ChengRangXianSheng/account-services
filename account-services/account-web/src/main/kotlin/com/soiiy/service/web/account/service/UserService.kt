package com.soiiy.service.web.account.service

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.extension.plugins.pagination.Page
import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.platform.utils.result.ResponseResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.query.AccountUserQuery
import com.soiiy.service.share.account.result.AccountUserResult
import com.soiiy.service.web.account.entity.GrantEntity
import com.soiiy.service.web.account.entity.StoreEntity
import com.soiiy.service.web.account.entity.UserEntity
import com.soiiy.service.web.account.mapper.RoleMapper
import com.soiiy.service.web.account.mapper.StoreMapper
import com.soiiy.service.web.account.mapper.UserMapper
import com.soiiy.service.web.account.vo.UserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import sun.security.provider.PolicyParser

/**
 *用户mapper
 *@Author ChenRang
 *@Date  2019/11/17 20:21
 */
@Service
class UserService{

    @Autowired
    lateinit var mapper:UserMapper
    @Autowired
    lateinit var roleMapper:RoleMapper
    @Autowired
    lateinit var storeMapper: StoreMapper

    fun query(type: Int):AccountUserQuery{
        val query=AccountUserQuery()
        query.formRoles=roleMapper.findAllByType(type).map { it.result() }
        return query
    }
    /**
     * 分页按条件查询用户
     * @Author: ChenRang
     * @Date: 2019/11/17 20:24
     */
    fun index(keywords:String?,limitStatus: AccountLimitStatus?,type:AccountUserType,roleId:Int?,page:Long,size:Long):ResponsePageResult<AccountUserResult>{
        val queryPage=Page<UserEntity>(page,size)
        val queryWrapper = QueryWrapper<UserEntity>()

        queryWrapper.allEq(mapOf("type" to type.code, "role_id" to roleId, "limit_status" to limitStatus), false)
                .and { it.eq("1", 1).like(!keywords.isNullOrEmpty(), "name", keywords)
                        .or().like(!keywords.isNullOrEmpty(), "mobile", keywords) }

        var result=mapper.selectPage(queryPage,queryWrapper)

        return ResponseResult.page(result.records.map { result(it) },result.total)
    }
    /**
     * 给用户授权
     * @Author: ChenRang
     * @Date: 2019/11/17 21:24
     */
    fun grant(grants:String,storeId:Long?,marketId:Long?):List<AccountUserResult>{
        val itemGrantIds=grants.substringAfter(":")

        var grantUsers = if (storeId===null){
            val storeQuery=QueryWrapper<StoreEntity>()
            storeQuery.`in`("id",itemGrantIds).eq   (marketId!=null,"market_id",marketId)
            val storeIds=storeMapper.selectList(storeQuery).map { it.id }
            if(storeIds.isEmpty()) return emptyList()
            val grantQuery=QueryWrapper<GrantEntity>()
            grantQuery.eq("type:",3).`in`("`grant`",storeIds)
            mapper.findGrantByQuery(grantQuery)
        }else{
            mapper.findGrantByStore(storeId)
        }

        if (grantUsers.isEmpty()) return emptyList()

        val grantUserIds=grantUsers.map{it.userId}
        val userQuery=QueryWrapper<UserEntity>()
        userQuery.`in`("id",grantUserIds)
        val userResults=mapper.selectList(userQuery)

        return userResults.map { userEntity ->
            val grantUser=grantUsers.firstOrNull{it.userId==userEntity.id}
            userEntity.roleId=grantUser?.roleId
            userEntity.name=grantUser?.label?:userEntity.name
            userEntity.grant=grantUser?.grant
            return@map result(userEntity)
        }
    }
    fun create(type: Int):AccountUserQuery=query(type)
    /**
     * 通过手机号查询
     * @Author: ChenRang
     * @Date: 2019/11/18 14:50
     */
    fun findByMobile(mobile:String):AccountUserResult?{
        var entity = mapper.selectByMobile(mobile)
        return if(entity===null) null else result(entity)
    }
    /**
     * 添加用户
     * @Author: ChenRang
     * @Date: 2019/11/17 21:07
     */
    fun store(vo:UserVO):AccountUserResult{
        var entity = vo.entity()
        mapper.insert(entity)
        return result(entity)
    }
    /**
     * 根据id查询
     * @Author: ChenRang
     * @Date: 2019/11/17 21:17
     */
    fun show(id:Long):AccountUserResult{
        var entity = mapper.selectById(id)
        return result(entity)
    }
    /**
     * 修改用户资料时填充表单
     * @Author: ChenRang
     * @Date: 2019/11/17 21:19
     */
    fun edit(id:Long):AccountUserQuery{
        var user=show(id)
        var query=query(1)
        query.formData=user
        return query
    }
    /**
     * 修改用户资料
     * @Author: ChenRang
     * @Date: 2019/11/17 21:21
     */
    fun update(id:Long,vo:UserVO):AccountUserResult{
        var oldEntity = mapper.selectById(id)
        var entity = vo.entity(oldEntity)
        mapper.updateById(entity)
        return result(entity)
    }
    /**
     * 删除用户资料
     * @Author: ChenRang
     * @Date: 2019/11/17 21:23
     */
    fun destroy(id:Long):Boolean{
        mapper.deleteById(id)
        return true
    }
    fun result(entity: UserEntity):AccountUserResult{
        var result=entity.result()
        var role=roleMapper.findById(entity.roleId)
        if(role!==null){
            result.roleName=role.name
            result.roleLabel=role.label
        }
        return result
    }
}