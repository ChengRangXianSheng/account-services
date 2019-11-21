package com.soiiy.service.web.account.mapper

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper
import com.baomidou.mybatisplus.core.mapper.BaseMapper
import com.soiiy.service.share.account.result.AccountGrantResult
import com.soiiy.service.web.account.entity.GrantEntity
import com.soiiy.service.web.account.entity.UserEntity
import org.apache.ibatis.annotations.*
import sun.security.provider.PolicyParser

/**
 *用户mapper
 *@Author ChenRang
 *@Date  2019/11/17 15:43
 */
@Mapper
interface UserMapper:BaseMapper<UserEntity>{

    /**
     * 新增根据根据电话号码查询
     * @Author: ChenRang
     * @Date: 2019/11/17 18:40
     */
    @Select("SELECT * FROM account_users WHERE mobile=#{mobile}")
    fun selectByMobile(mobile:String):UserEntity?

    @Select("SELECT * FROM account_grants \${ew.customSqlSegment}")
    fun findGrantByQuery(@Param("ew")wrapper: QueryWrapper<GrantEntity>):List<GrantEntity>

    @Select("SELECT * FROM account_grants WHERE type=3 AND 'grant'=#{store};")
    fun findGrantByStore(store:Any):List<GrantEntity>
    /**
     * 根据userId查看权限
     * @Author: ChenRang
     * @Date: 2019/11/18 17:48
     */
    @Select("SELECT * FROM account_grants WHERE user_id=#{userId}")
    fun findGrantByUser(id:String):List<GrantEntity>
    /**
     * 添加授权操作
     * @Author: ChenRang
     * @Date: 2019/11/18 17:39
     */
    @Insert("INSERT INTO account_grants(`grant`,type,label,user_id,role_id) VALUES(#{grant},#{type},#{label},#{userId},#{roleId})")
    fun grant(type:Int,grant:String,userId:String,label:String?,roleId:Int)

    /**
     * 取消授权操作
     * @Author: ChenRang
     * @Date: 2019/11/18 17:33
     */
    @Delete("DELETE FROM account_grants WHERE `grant`=#{grant} AND user_id=#{userId} AND type=#{type}")
    fun revoke(type:Int,grant:String,userId:String)
}