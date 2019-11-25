package com.soiiy.platform.security

import org.springframework.security.authentication.AbstractAuthenticationToken

/**
 * Jwt令牌
 * @Author ChenRang
 * @Date  2019/11/23 11:31
 */
class JwtAuthenticationToken(val token: String,val required:Boolean=false) :AbstractAuthenticationToken(null){

    override fun getCredentials(): Any =token

    override fun getPrincipal(): Any =required

}