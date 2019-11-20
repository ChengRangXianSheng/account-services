package com.soiiy.service.web.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.query.AccountStoreQuery
import com.soiiy.service.share.account.result.AccountStoreResult
import com.soiiy.service.web.account.service.StoreService
import com.soiiy.service.web.account.vo.StoreVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *店铺控制类
 *@Author ChenRang
 *@Date  2019/11/17 19:53
 */
@RestController
@RequestMapping("/stores")
class StoreController {

    @Autowired
    lateinit var service:StoreService

    @GetMapping("/query")
    fun query(): AccountStoreQuery =service.query()

    /**
     * 分页查询展示店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:54
     */
    @GetMapping("/index")
    fun index(@RequestParam(required = false)keywords: String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false)marketId:String?,
              @RequestParam(required = false,defaultValue = "1")page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long
    ): ResponsePageResult<AccountStoreResult> = service.index(keywords,limitStatus,marketId,page,size)

    /**
     * 查询授权信息
     * @Author: ChenRang
     * @Date: 2019/11/17 20:02
     */
    @GetMapping("/grant")
    fun grant(@RequestParam(required = false)grants: String,
              @RequestParam(required = false,defaultValue = "1")page: Long,
              @RequestParam(required = false,defaultValue = "10")size: Long):ResponsePageResult<AccountStoreResult>
            =  service.grant(grants,page,size)
    /**
     * 新建时跳转页面
     * @Author: ChenRang
     * @Date: 2019/11/18 18:27
     */
    @GetMapping("/create")
    fun create():AccountStoreQuery=service.create()
    /**
     * 添加店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:57
     */
    @PostMapping
    fun store(@RequestBody vo:StoreVO):AccountStoreResult=service.store(vo)

    /**
     * 根据id查询店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:58
     */
    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountStoreResult=service.show(id)

    /**
     * 编辑前填充表单信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:59
     */
    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountStoreQuery=service.edit(id)

    /**
     * 修改信息
     * @Author: ChenRang
     * @Date: 2019/11/17 20:00
     */
    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long, @RequestBody vo:StoreVO):AccountStoreResult=service.update(id,vo)

    /**
     * 删除店铺信息
     * @Author: ChenRang
     * @Date: 2019/11/17 20:01
     */
    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id") id:Long):Boolean=service.destroy(id)
}