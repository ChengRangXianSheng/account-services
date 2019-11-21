package com.soiiy.service.share.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.dto.AccountStoreDTO
import com.soiiy.service.share.account.query.AccountStoreQuery
import com.soiiy.service.share.account.result.AccountStoreResult
import org.springframework.web.bind.annotation.*

/**
 * 店铺api
 * @Author ChenRang
 * @Date  2019/11/21 16:47
 */

interface AccountStoreApi {

    @GetMapping("/query")
    fun query():AccountStoreQuery

    @GetMapping("/create")
    fun create():AccountStoreQuery

    @GetMapping("/index")
    fun index(@RequestParam(required = false)keywords:String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false)marketId:Long?,
              @RequestParam(required = false,defaultValue = "1") page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long):ResponsePageResult<AccountStoreResult>

    @GetMapping("/grants")
    fun grant(@RequestParam(required = false)grants:String?,
              @RequestParam(required = false,defaultValue = "1") page: Long,
              @RequestParam(required = false,defaultValue = "10")size:Long):ResponsePageResult<AccountStoreResult>

    @PostMapping
    fun store(@RequestBody dto:AccountStoreDTO):AccountStoreResult

    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountStoreResult

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountStoreQuery

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,@RequestBody dto:AccountStoreDTO):AccountStoreResult

    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean
}