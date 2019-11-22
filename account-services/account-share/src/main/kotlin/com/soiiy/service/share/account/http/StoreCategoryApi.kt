package com.soiiy.service.share.account.http

import com.soiiy.service.share.account.dto.StoreCategoryDTO
import com.soiiy.service.share.account.query.StoreCategoryQuery
import com.soiiy.service.share.account.result.StoreCategoryResult
import org.springframework.web.bind.annotation.*

/**
 * 店铺分类api
 * @Author ChenRang
 * @Date  2019/11/21 17:07
 */

interface StoreCategoryApi {

    @GetMapping("/query")
    fun query():StoreCategoryQuery

    @GetMapping("/create")
    fun create():StoreCategoryQuery

    @GetMapping("/index")
    fun index():List<StoreCategoryResult>

    @PostMapping
    fun store(@RequestBody dto:StoreCategoryDTO):StoreCategoryResult

    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):StoreCategoryResult

    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):StoreCategoryQuery

    @PutMapping("/{id}")
    fun update(@PathVariable("id")id: Long,@RequestBody dto:StoreCategoryDTO):StoreCategoryResult

    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean

}