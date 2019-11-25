package com.soiiy.platform.security.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.soiiy.platform.security.JwtAuthenticationUser
import org.springframework.core.MethodParameter
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.ModelAndViewContainer
import org.springframework.web.servlet.mvc.method.annotation.AbstractMessageConverterMethodArgumentResolver

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 10:04
 */

class JwtMethodUserArgumentResolver: AbstractMessageConverterMethodArgumentResolver {

    constructor(converters: List<HttpMessageConverter<*>>): super(converters)

    constructor(converters: List<HttpMessageConverter<*>>, advice: List<Any>?): super(converters, advice)

    private val json = ObjectMapper()

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(JwtAuthenticationUser::class.java)
    }

    override fun resolveArgument(p0: MethodParameter, p1: ModelAndViewContainer?, p2: NativeWebRequest, p3: WebDataBinderFactory?): Any? {
        val parameter = p0.nestedIfOptional()
        val parameterType = parameter.parameterType
        try {
            val user = SecurityContextHolder.getContext().authentication as JwtAuthenticationUser
            val userString = json.writeValueAsString(user)
            return json.readValue(userString, parameterType)
        } catch (e: Exception) { return null }
    }

}