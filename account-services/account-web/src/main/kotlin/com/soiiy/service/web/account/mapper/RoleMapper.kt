package com.soiiy.service.web.account.mapper

import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.soiiy.service.web.account.entity.RoleEntity
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Select

/**
 *角色mapper
 *@Author ChenRang
 *@Date  2019/11/17 20:57
 */
@Mapper
interface RoleMapper:BaseMapper<RoleEntity>{

    @Select("SELECT * FROM account_roles WHERE type=#{type}")
    fun findAllByType(type:Int):List<RoleEntity>

    @Select("SELECT * FROM account_roles WHERE grant=#{grant}")
    fun findAllByGrant(grant:Int):List<RoleEntity>

    @Select("SELECT * FROM account_roles WHERE id=#{id}")
    fun findById(id:Int?):RoleEntity?

}