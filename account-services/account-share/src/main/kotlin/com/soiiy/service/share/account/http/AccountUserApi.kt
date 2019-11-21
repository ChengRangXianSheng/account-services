package com.soiiy.service.share.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.dto.AccountUserDTO
import com.soiiy.service.share.account.query.AccountUserQuery
import com.soiiy.service.share.account.result.AccountUserResult
import org.springframework.web.bind.annotation.*

/**
 * 账户api
 * @Author ChenRang
 * @Date  2019/11/21 17:18
 */

interface AccountUserApi {

    @GetMapping("/query")
    fun query(type:AccountUserType):AccountUserQuery

    @GetMapping("/create")
    fun create(type: AccountUserType):AccountUserQuery

    @GetMapping("/index")
    fun index(@RequestParam(required = false)keywords:String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false)roleId:Int?,
              @RequestParam(required = false)type: AccountUserType,
              @RequestParam(required = false,defaultValue = "1")page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long):ResponsePageResult<AccountUserResult>

    @GetMapping("/grant")
    fun grant(grants:String,
              markets:Long?,
              stores:Long?):List<AccountUserResult>

    @PostMapping
    fun store(dto:AccountUserDTO):AccountUserResult

    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountUserResult

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountUserQuery

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,dto:AccountUserDTO):AccountUserResult

    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean

}