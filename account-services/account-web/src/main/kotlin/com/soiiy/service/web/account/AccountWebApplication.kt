package com.soiiy.service.web.account

import org.mybatis.spring.annotation.MapperScan
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@MapperScan("com.soiiy.service.web.account.mapper")
class AccountWebApplication

fun main(args: Array<String>) {
    runApplication<AccountWebApplication>(*args)
}
