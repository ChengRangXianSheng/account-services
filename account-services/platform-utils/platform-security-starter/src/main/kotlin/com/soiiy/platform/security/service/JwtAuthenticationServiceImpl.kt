package com.soiiy.platform.security.service

import com.soiiy.platform.security.JwtAuthenticationUser
import com.soiiy.platform.security.config.JwtAuthorizable
import com.soiiy.platform.security.config.JwtGrantable
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import com.soiiy.platform.security.storage.JwtAuthenticationStorage

class JwtAuthenticationServiceImpl(private val storage: JwtAuthenticationStorage): JwtAuthenticationService {

    private val passwordEncoder = BCryptPasswordEncoder()

    override fun encode(p0: CharSequence): String {
        return passwordEncoder.encode(p0)
    }

    override fun matches(p0: CharSequence?, p1: String?): Boolean {
        return passwordEncoder.matches(p0, p1)
    }

    override fun clear(primaryKey: Any) {
        storage.clear(primaryKey)
    }

    override fun refresh(token: String, user: JwtAuthorizable, grants: List<JwtGrantable>) {
        val auth = JwtAuthenticationUser(user, grants)
        storage.refresh(auth, token)
    }

    override fun login(user: JwtAuthorizable, grants: List<JwtGrantable>): String {
        val auth = JwtAuthenticationUser(user, grants)
        return storage.login(auth)
    }

    override fun logout(token: String) {
        storage.logout(token)
    }


}