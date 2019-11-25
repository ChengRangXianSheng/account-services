package com.soiiy.platform.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.soiiy.platform.utils.exception.HttpUnauthorizedException
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.util.AntPathMatcher
import org.springframework.web.filter.OncePerRequestFilter
import sun.plugin.liveconnect.SecurityContextHelper
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Jwt权限过滤器，如果没有携带有效token,则登录否则根据token登录
 * @Author ChenRang
 * @Date  2019/11/23 11:10
 */

class JwtAuthenticationFilter(private val manager: AuthenticationManager,private val permitUrls:List<String>):OncePerRequestFilter() {

    private val objectMapper=ObjectMapper()

    private val antPathMatcher=AntPathMatcher()

    /**
     * 过滤器处理方法
     * @Author: ChenRang
     * @Date: 2019/11/23 11:13
     */
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        SecurityContextHolder.clearContext()
        val required=!isPermitUrl(request.requestURI)
        try {
            securityAuthentication(request,required)
        }catch (e:Throwable){
            if (required){
                return this.onAuthenticationFailure(e,response)
            }
        }
        filterChain.doFilter(request,response)
    }

    /**
     * 判断token是否有效
     * @Author: ChenRang
     * @Date: 2019/11/23 11:23
     */
    private fun securityAuthentication(request: HttpServletRequest,required:Boolean){
        var authorizationToken = request.getHeader("Authorization").orEmpty()
        if (!authorizationToken.startsWith("Bearer")&&required){
            throw HttpUnauthorizedException()
        }

        var token=if(authorizationToken.length>10) authorizationToken.substring(7) else ""
        var authentication=JwtAuthenticationToken(token,required)
        manager.authenticate(authentication)
    }
    /**
     * 无效token或请求时自定义错误信息
     * @Author: ChenRang
     * @Date: 2019/11/23 11:35
     */
    private fun onAuthenticationFailure(e:Throwable,response: HttpServletResponse){
        val resultBody=objectMapper.writeValueAsString(mapOf("errMsg" to e.message) )
        response.contentType="Application/json"
        response.characterEncoding="UTF-8"
        response.status=401
        response.writer.print(resultBody)
    }

    /**
     * url字符串匹配方法
     * @Author: ChenRang
     * @Date: 2019/11/23 11:21
     */
    private fun isPermitUrl(uri:String):Boolean{
        return permitUrls.any { antPathMatcher.match(it,uri) }
    }

}