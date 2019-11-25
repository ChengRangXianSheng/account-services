package com.soiiy.platform.security

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.databind.ObjectMapper
import com.soiiy.platform.security.config.JwtAuthorizable
import com.soiiy.platform.security.config.JwtGrantable
import com.soiiy.platform.utils.json.JsonUtil
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority

/**
 * jwt认证用户对象
 * @Author ChenRang
 * @Date  2019/11/23 14:05
 */
class JwtAuthenticationUser(
        val primaryValue:String="",
        val permissions:List<Any> = listOf(),
        val userDetails:String = "",
        val userGrants:String=""
) :Authentication{

    private val json=ObjectMapper()

    constructor(user:JwtAuthorizable, grants :List<JwtGrantable>):this(
            user.primaryValue().toString(),
            user.permissions(),
            JsonUtil.toJSONString(user).orEmpty(),
            JsonUtil.toJSONString(user).orEmpty()
    )

    @JsonIgnore
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> ?= null

    override fun setAuthenticated(p0: Boolean) = Unit

    @JsonIgnore
    override fun getName(): String = primaryValue

    @JsonIgnore
    override fun getCredentials(): Any ?=null

    @JsonIgnore
    override fun getPrincipal(): Any =primaryValue

    @JsonIgnore
    override fun isAuthenticated(): Boolean =false

    @JsonIgnore
    override fun getDetails(): Any ?=null

    fun <T> userDetail(parameterType:Class<T>):T{
        return JsonUtil.parseObject(userDetails,parameterType)
    }

    fun <T> userGrants(parameterType:Class<T>):List<T>{
        return json.readTree(userGrants).map {
            json.readValue(it.toString(),parameterType)
        }
    }
}