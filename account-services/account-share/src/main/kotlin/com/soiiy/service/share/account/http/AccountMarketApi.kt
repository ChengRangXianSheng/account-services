package com.soiiy.service.share.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.dto.AccountMarketDTO
import com.soiiy.service.share.account.query.AccountMarketQuery
import com.soiiy.service.share.account.result.AccountMarketResult
import org.springframework.web.bind.annotation.*

/**
 * 网点api
 * @Author ChenRang
 * @Date  2019/11/21 17:00
 */

interface AccountMarketApi {

    @GetMapping("/query")
    fun query():AccountMarketQuery

    @GetMapping("/create")
    fun create():AccountMarketQuery

    @GetMapping("/index")
    fun index(@RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false)keywords:String?,
              @RequestParam(required = false,defaultValue = "1") page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long):ResponsePageResult<AccountMarketResult>

    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountMarketResult

    @PostMapping
    fun store(@RequestBody dto:AccountMarketDTO):AccountMarketResult

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountMarketQuery

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,@RequestBody dto:AccountMarketDTO):AccountMarketResult

    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean
}