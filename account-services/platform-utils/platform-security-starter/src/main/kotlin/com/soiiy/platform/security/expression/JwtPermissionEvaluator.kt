package com.soiiy.platform.security.expression

import org.springframework.security.access.PermissionEvaluator
import org.springframework.security.core.Authentication
import java.io.Serializable

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 10:03
 */

class JwtPermissionEvaluator: PermissionEvaluator {

    override fun hasPermission(p0: Authentication, p1: Any?, p2: Any?): Boolean {
        return false
    }

    override fun hasPermission(p0: Authentication?, p1: Serializable?, p2: String?, p3: Any?): Boolean {
        return false
    }

}