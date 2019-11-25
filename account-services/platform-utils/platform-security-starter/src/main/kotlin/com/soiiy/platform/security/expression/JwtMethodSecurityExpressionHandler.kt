package com.soiiy.platform.security.expression

import org.aopalliance.intercept.MethodInvocation
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations
import org.springframework.security.core.Authentication

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 10:03
 */

class JwtMethodSecurityExpressionHandler: DefaultMethodSecurityExpressionHandler() {

    override fun createSecurityExpressionRoot(authentication: Authentication, invocation: MethodInvocation): MethodSecurityExpressionOperations {
        val root = JwtMethodSecurityExpressionRoot(authentication)
        root.setThis(invocation.getThis())
        root.setPermissionEvaluator(this.permissionEvaluator)
        root.setTrustResolver(this.trustResolver)
        root.setRoleHierarchy(this.roleHierarchy)
        root.setDefaultRolePrefix(this.defaultRolePrefix)
        return root
    }

}