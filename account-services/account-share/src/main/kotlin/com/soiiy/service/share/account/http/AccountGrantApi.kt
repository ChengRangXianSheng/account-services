package com.soiiy.service.share.account.http

import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.dto.AccountGrantDTO
import com.soiiy.service.share.account.query.AccountGrantQuery
import com.soiiy.service.share.account.result.AccountGrantResult
import org.springframework.web.bind.annotation.*

/**
 * 账户授权api
 * @Author ChenRang
 * @Date  2019/11/21 17:12
 */

interface AccountGrantApi {

    @GetMapping("/query")
    fun query():AccountGrantQuery

    @PostMapping
    fun grant(dto:AccountGrantDTO):Boolean

    @PutMapping
    fun revoke(dto:AccountGrantDTO):Boolean

    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):List<AccountGrantResult>

    @GetMapping("/tree")
    fun tree(@RequestParam(required = false)markets:String?=null,
             @RequestParam(required = false)stores:String?=null):List<AccountGrantResult>
}