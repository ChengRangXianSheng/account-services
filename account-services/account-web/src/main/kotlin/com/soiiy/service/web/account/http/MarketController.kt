package com.soiiy.service.web.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.query.AccountMarketQuery
import com.soiiy.service.share.account.result.AccountMarketResult
import com.soiiy.service.web.account.service.MarketService
import com.soiiy.service.web.account.vo.MarketVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *网点控制类
 *@Author ChenRang
 *@Date  2019/11/17 19:39
 */
@RestController
@RequestMapping("/markets")
class MarketController {

    @Autowired
    lateinit var service:MarketService

    @GetMapping("/query")
    fun query():AccountMarketQuery=service.query()

    /**
     * 分页显示网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:41
     */
    @GetMapping("/index")
    fun index(@RequestParam(required = false)keywords:String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false,defaultValue = "1")page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long
    ): ResponsePageResult<AccountMarketResult> = service.index(keywords,limitStatus,page,size)

    /**
     * 查询出网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:45
     */
    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountMarketResult=service.show(id)

    @GetMapping("/create")
    fun create():AccountMarketQuery=service.create()

    /**
     * 添加网点信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:51
     */
    @PostMapping
    fun store(@RequestBody vo:MarketVO):AccountMarketResult = service.store(vo)


    /**
     * 编辑时展示信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:47
     */
    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountMarketQuery=service.edit(id)

    /**
     * 修改信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:48
     */
    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,@RequestBody vo:MarketVO):AccountMarketResult=service.update(id,vo)
    /**
     * 删除信息
     * @Author: ChenRang
     * @Date: 2019/11/17 19:50
     */
    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean=service.destroy(id)
}