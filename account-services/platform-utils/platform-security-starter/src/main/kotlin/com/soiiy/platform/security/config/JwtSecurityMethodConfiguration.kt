package com.soiiy.platform.security.config

import com.soiiy.platform.security.expression.JwtMethodSecurityExpressionHandler
import com.soiiy.platform.security.expression.JwtPermissionEvaluator
import org.springframework.context.annotation.Configuration
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration

/**
 *
 * @Author ChenRang
 * @Date  2019/11/25 10:02
 */

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
class JwtSecurityMethodConfiguration: GlobalMethodSecurityConfiguration() {


    override fun createExpressionHandler(): MethodSecurityExpressionHandler {
        val handler = JwtMethodSecurityExpressionHandler()
        handler.setPermissionEvaluator(JwtPermissionEvaluator())
        return handler
    }


}