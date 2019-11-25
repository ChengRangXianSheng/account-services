package com.soiiy.platform.security.storage

import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.SmartInitializingSingleton
import org.springframework.beans.factory.config.BeanPostProcessor
import org.springframework.core.Ordered
import org.springframework.data.redis.core.StringRedisTemplate
import java.util.concurrent.TimeUnit

/**
 * Redis配置
 * @Author ChenRang
 * @Date  2019/11/25 9:42
 */
class JwtAuthenticationRedisStorage(override val tokenPrefix: String, private val cacheDays: Long): JwtAuthenticationStorage(), BeanPostProcessor, Ordered, BeanFactoryAware, SmartInitializingSingleton {

    private var beanFactory: BeanFactory? = null

    private var redistTemplate: StringRedisTemplate? = null

    override fun getOrder(): Int {
        return Ordered.LOWEST_PRECEDENCE
    }

    override fun setBeanFactory(beanFactory: BeanFactory) {
        this.beanFactory = beanFactory
    }

    override fun afterSingletonsInstantiated() {
        if (this.beanFactory === null) return
        this.redistTemplate = this.beanFactory!!.getBean(StringRedisTemplate::class.java)
    }

    override fun findFromStorage(token: String): String {
        return redistTemplate?.opsForValue()?.get(token).orEmpty()
    }

    override fun saveFromStorage(token: String, userInfo: String) {
        redistTemplate?.opsForValue()?.set(token, userInfo)
        redistTemplate?.expire(token, cacheDays, TimeUnit.DAYS)
    }

    override fun removeFromStorage(token: String) {
        if (redistTemplate?.hasKey(token) == true) {
            redistTemplate?.delete(token)
        }
    }

}