package com.soiiy.platform.security

import com.soiiy.platform.security.storage.JwtAuthenticationStorage
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 9:48
 */
class JwtAuthenticationProvider(private val storage: JwtAuthenticationStorage): AuthenticationProvider {

    override fun authenticate(p0: Authentication): Authentication? {
        val authentication = p0 as JwtAuthenticationToken
        val token = authentication.token
        return try {
            val user = storage.findUserByToken(token)
            SecurityContextHolder.getContext().authentication = user
            user
        } catch (e: Exception) {
            if (authentication.required)
                throw e
            else
                SecurityContextHolder.getContext().authentication = null
            null
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return JwtAuthenticationToken::class.java.isAssignableFrom(authentication)
    }

}