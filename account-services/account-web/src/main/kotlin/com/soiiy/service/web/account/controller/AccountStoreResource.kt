package com.soiiy.service.web.account.controller

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.query.AccountStoreQuery
import com.soiiy.service.share.account.result.AccountStoreResult
import com.soiiy.service.web.account.service.StoreService
import com.soiiy.service.web.account.vo.StoreVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

/**
 * 账户店铺web端
 * @Author ChenRang
 * @Date  2019/11/26 9:21
 */
@Controller
@RequestMapping("/storeResource")
class AccountStoreResource {

    @Autowired
    lateinit var service:StoreService
    private val prefix="/store"

    @GetMapping("/query")
    fun query(): String = prefix+"/store"

    @GetMapping("/index")
    @ResponseBody
    fun index(@RequestParam(required = false)keywords: String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false)marketId:String?,
              @RequestParam(required = false)categoryId:Long?,
              @RequestParam(required = false,defaultValue = "1") page:Long,
              @RequestParam(required = false,defaultValue = "10") size:Long
    ): ResponsePageResult<AccountStoreResult> = service.index(keywords,limitStatus,marketId,categoryId,page,size)

    /**
     * 根据id查询账户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 9:28
     */
    @RequestMapping("/show")
    @ResponseBody
    fun show(id:String):AccountStoreResult = service.show(id.toLong())

    /**
     * 修改店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/26 10:32
     */
    @RequestMapping("/update")
    @ResponseBody
    fun update(id:Long,dto:StoreVO):AccountStoreResult = service.update(id.toLong(),dto)

    /**
     * 根据id删除账户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 9:28
     */
    @RequestMapping("/delete")
    @ResponseBody
    fun destroy(id:String):Boolean = service.destroy(id.toLong())

    /**
     * 添加商户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 9:29
     */
    @RequestMapping("/store")
    @ResponseBody
    fun store(dto:StoreVO):AccountStoreResult = service.store(dto)
}