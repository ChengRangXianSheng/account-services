package com.soiiy.service.web.account.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

/**
 *
 * @Author ChenRang
 * @Date  2019/11/21 10:34
 */
@Controller
class TestController {

    @GetMapping("/aa")
    fun test():String="index"

    @GetMapping("/A")
    fun A():String="A"

    @GetMapping("/B")
    fun B():String="B"
}