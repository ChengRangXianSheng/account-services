package com.soiiy.service.web.account.controller

import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

/**
 *
 * @Author ChenRang
 * @Date  2019/11/21 10:34
 */
@RestController
@RequestMapping("/test")
class TestController {

    @RequestMapping("/aa")
    fun test():String="AAAA"
}