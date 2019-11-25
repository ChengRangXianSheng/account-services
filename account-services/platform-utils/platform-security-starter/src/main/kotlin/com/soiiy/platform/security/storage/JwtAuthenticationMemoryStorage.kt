package com.soiiy.platform.security.storage

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 9:46
 */

class JwtAuthenticationMemoryStorage: JwtAuthenticationStorage() {

    private val authenticationMap = mutableMapOf<String, String>()

    override fun findFromStorage(token: String): String {
        return authenticationMap[token].orEmpty()
    }

    override fun saveFromStorage(token: String, userInfo: String) {
        authenticationMap.set(token, userInfo)
    }

    override fun removeFromStorage(token: String) {
        authenticationMap.remove(token)
    }

}