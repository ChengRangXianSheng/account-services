package com.soiiy.platform.security.service


import com.soiiy.platform.security.config.JwtAuthorizable
import com.soiiy.platform.security.config.JwtGrantable
import org.springframework.security.crypto.password.PasswordEncoder

interface JwtAuthenticationService: PasswordEncoder {

    fun clear(primaryKey: Any)

    fun refresh(token: String, user: JwtAuthorizable, grants: List<JwtGrantable> = listOf())

    fun login(user: JwtAuthorizable, grants: List<JwtGrantable> = listOf()): String

    fun logout(token: String)

}