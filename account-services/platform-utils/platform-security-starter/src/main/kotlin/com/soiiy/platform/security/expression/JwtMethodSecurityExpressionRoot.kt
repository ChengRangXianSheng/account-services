package com.soiiy.platform.security.expression

import com.soiiy.platform.security.JwtAuthenticationUser
import org.springframework.security.access.expression.SecurityExpressionRoot
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 10:03
 */

class JwtMethodSecurityExpressionRoot(authentication: Authentication) : SecurityExpressionRoot(authentication), MethodSecurityExpressionOperations {

    private var filterObject: Any? = null
    private var returnObject: Any? = null
    private var target: Any? = null

    override fun setReturnObject(p0: Any?) {
        this.returnObject = p0
    }

    override fun getReturnObject(): Any? {
        return this.returnObject
    }

    override fun setFilterObject(p0: Any?) {
        this.filterObject = p0
    }

    override fun getFilterObject(): Any? {
        return this.filterObject
    }

    fun setThis(p0: Any?) {
        this.target = p0
    }

    override fun getThis(): Any? {
        return this.target
    }

    fun hasPermission(permission: Any): Boolean {
        val permissions = (authentication as JwtAuthenticationUser).permissions
        return permissions.contains(permission)
    }

}