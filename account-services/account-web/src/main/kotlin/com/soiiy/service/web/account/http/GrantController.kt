package com.soiiy.service.web.account.http

import com.soiiy.service.share.account.constant.AccountGrantType
import com.soiiy.service.share.account.query.AccountGrantQuery
import com.soiiy.service.share.account.result.AccountGrantResult
import com.soiiy.service.web.account.service.GrantService
import com.soiiy.service.web.account.vo.GrantVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *授权控制类
 *@Author ChenRang
 *@Date  2019/11/19 9:27
 */
@RestController
class GrantController {

    @Autowired
    lateinit var service: GrantService

    /**
     * 查询授权
     * @Author: ChenRang
     * @Date: 2019/11/19 9:28
     */
    @GetMapping
    fun query(type:AccountGrantType):AccountGrantQuery = service.query(type)

    /**
     * 添加授权
     * @Author: ChenRang
     * @Date: 2019/11/19 9:30
     */
    @PostMapping
    fun grant(@RequestBody vo:GrantVO):Boolean = service.grant(vo)

    /**
     * 取消授权
     * @Author: ChenRang
     * @Date: 2019/11/19 9:30
     */
    @PutMapping
    fun revoke(@RequestBody vo:GrantVO):Boolean=service.revoke(vo)

    /**
     * 查询授权
     * @Author: ChenRang
     * @Date: 2019/11/19 9:31
     */
    @GetMapping("/{userId}")
    fun show(userId:String):List<AccountGrantResult> = service.show(userId)
}