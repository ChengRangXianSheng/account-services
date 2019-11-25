package com.soiiy.platform.security.storage

import com.fasterxml.jackson.databind.ObjectMapper
import com.soiiy.platform.security.JwtAuthenticationUser
import com.soiiy.platform.utils.exception.HttpUnauthorizedException
import org.apache.commons.lang3.RandomStringUtils
import org.apache.commons.lang3.StringUtils
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.util.*

/**
 * Jwt权限储存类
 * @Author ChenRang
 * @Date  2019/11/23 11:44
 */
abstract class JwtAuthenticationStorage {
    open val tokenPrefix="default"

    private val randomLength=32

    private val randomChars="0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-"

    private val jsonMapper=ObjectMapper()

    /**
     * 生成token
     * @Author: ChenRang
     * @Date: 2019/11/23 11:47
     */
    private fun createToken():String{
        val prefix="jwtAuthToken@$tokenPrefix"
        val info=prefix+ Date().time
        return prefix+BCryptPasswordEncoder().encode(info)+RandomStringUtils.random(randomLength,randomChars)
    }

    /**
     * json序列化
     * @Author: ChenRang
     * @Date: 2019/11/23 11:51
     */
    private fun jsonSerialize(user:JwtAuthenticationUser):String{
        return jsonMapper.writeValueAsString(user)
    }
    /**
     * json反序列化
     * @Author: ChenRang
     * @Date: 2019/11/23 14:49
     */
    private fun jsonDeserialize(user:String):JwtAuthenticationUser{
        return jsonMapper.readValue(user,JwtAuthenticationUser::class.java)
    }

    /**
     * token缓存
     * @Author: ChenRang
     * @Date: 2019/11/23 17:07
     */
    private fun cache(primaryKey:String,token:String){
        val primaryToken="jwt@$primaryKey"
        val tokens=findFromStorage(primaryKey)
                .split("|;;|")
                .toMutableList()
        tokens.add(token)
        val res=StringUtils.join(token,"|;;|")
        saveFromStorage(primaryToken,res)
    }

    fun clear(primaryKey: Any){
        removeFromStorage("jwt@$primaryKey")
    }

    fun findUserByToken(token: String):JwtAuthenticationUser{
        if(token.isEmpty()) throw HttpUnauthorizedException()
        val userInfo=findFromStorage(token)
        return jsonDeserialize(userInfo)
    }

    fun refresh(user:JwtAuthenticationUser,token: String){
        val userInfo=jsonSerialize(user)
        saveFromStorage(token,userInfo)
    }

    fun login(user: JwtAuthenticationUser):String{
        val token=createToken()
        val userInfo=jsonSerialize(user)
        cache(user.primaryValue,token)
        saveFromStorage(token,userInfo)
        return token
    }

    fun logout(token: String){
        removeFromStorage(token)
    }
    abstract fun findFromStorage(token: String):String

    abstract fun saveFromStorage(token: String,userInfo:String)

    abstract fun removeFromStorage(token: String)
}