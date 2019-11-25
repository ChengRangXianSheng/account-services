package com.soiiy.platform.security.config

import com.soiiy.platform.security.JwtAuthenticationFilter
import com.soiiy.platform.security.JwtAuthenticationProvider
import com.soiiy.platform.security.service.JwtAuthenticationService
import com.soiiy.platform.security.service.JwtAuthenticationServiceImpl
import com.soiiy.platform.security.storage.JwtAuthenticationMemoryStorage
import com.soiiy.platform.security.storage.JwtAuthenticationRedisStorage
import com.soiiy.platform.security.storage.JwtAuthenticationStorage
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.config.BeanDefinition
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean
import org.springframework.boot.autoconfigure.http.HttpMessageConverters
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Role
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * SpringSecurity核心配置类
 * @Author ChenRang
 * @Date  2019/11/23 11:00
 */
@Configuration
@EnableConfigurationProperties(JwtSecurityProperties::class)
class JwtSecurityConfigration: WebSecurityConfigurerAdapter(), WebMvcConfigurer {

    @Autowired
    lateinit var properties: JwtSecurityProperties

    @Autowired
    @Suppress("SpringJavaInjectionPointsAutowiringInspection")
    lateinit var messageConverters: HttpMessageConverters

    @Autowired
    private lateinit var jwtAuthenticationStorage: JwtAuthenticationStorage

    @Bean
    @Role(BeanDefinition.ROLE_INFRASTRUCTURE)
    fun jwtAuthenticationStorage(): JwtAuthenticationStorage {
        if (properties.redis) {
            return JwtAuthenticationRedisStorage(properties.redisPrefix, properties.redisExpire)
        }
        return JwtAuthenticationMemoryStorage()
    }

    @Bean
    @ConditionalOnMissingBean
    fun jwtAuthenticationService(jwtAuthenticationStorage: JwtAuthenticationStorage): JwtAuthenticationService {
        return JwtAuthenticationServiceImpl(jwtAuthenticationStorage)
    }

    override fun configure(http: HttpSecurity) {
        http.formLogin().disable().httpBasic().disable()
                .cors().and().csrf().disable()
                .sessionManagement().disable()

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter::class.java)
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(JwtAuthenticationProvider(jwtAuthenticationStorage))
    }

    private fun jwtAuthenticationFilter(): JwtAuthenticationFilter {
        return JwtAuthenticationFilter(this.authenticationManager(), properties.permitUrls)
    }

    override fun addArgumentResolvers(resolvers: MutableList<HandlerMethodArgumentResolver>) {
        resolvers.add(JwtMethodUserArgumentResolver(messageConverters.converters))
    }

}