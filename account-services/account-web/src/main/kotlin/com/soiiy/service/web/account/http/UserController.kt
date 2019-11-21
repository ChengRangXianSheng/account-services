package com.soiiy.service.web.account.http

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserSex
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.query.AccountUserQuery
import com.soiiy.service.share.account.result.AccountUserResult
import com.soiiy.service.web.account.service.UserService
import com.soiiy.service.web.account.vo.UserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 * 用户控制层
 * @Author ChenRang
 * @Date  2019/11/18 14:53
 */
@RestController
@RequestMapping("/users")
class UserController {

    @Autowired
    lateinit var service:UserService

    /**
     * 跳转页面时加上用户类型
     * @Author: ChenRang
     * @Date: 2019/11/18 14:55
     */
    @GetMapping("/query")
    fun query(type:AccountUserType):AccountUserQuery=service.query(type)

    /**
     * 分页条件查询用户
     * @Author: ChenRang
     * @Date: 2019/11/18 14:55
     */
    @GetMapping("/index")
    fun index(@RequestParam(required = false) keywords:String?,
              @RequestParam(required = false) limitStatus: AccountLimitStatus?,
              @RequestParam(required = false) roleId:Int?,
              @RequestParam(required = false) type: AccountUserType,
              @RequestParam(required = false,defaultValue = "1") page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long
    ): ResponsePageResult<AccountUserResult> = service.index(keywords,limitStatus,type,roleId,page,size)

    /**
     * 查询授权信息
     * @Author: ChenRang
     * @Date: 2019/11/18 15:08
     */
    @GetMapping("/grants")
    fun grant(@RequestParam(required = false)grants:String,
              @RequestParam(required = false)storeId:Long?,
              @RequestParam(required = false)marketId:Long?
    ):List<AccountUserResult> = service.grant(grants,storeId,marketId)

    /**
     * 新增时封装表单数据
     * @Author: ChenRang
     * @Date: 2019/11/21 10:10
     */
    @GetMapping("/create")
    fun create(type: AccountUserType):AccountUserQuery=service.create(type)

    /**
     * 根据id查询用户信息
     * @Author: ChenRang
     * @Date: 2019/11/18 14:58
     */
    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):AccountUserResult=service.show(id)

    /**
     * 添加用户信息
     * @Author: ChenRang
     * @Date: 2019/11/18 15:00
     */
    @PostMapping
    fun store(@RequestBody vo:UserVO):AccountUserResult = service.store(vo)

    /**
     * 编辑前对表单数据填充
     * @Author: ChenRang
     * @Date: 2019/11/18 15:00
     */
    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):AccountUserQuery = service.edit(id)

    /**
     * 修改用户信息
     * @Author: ChenRang
     * @Date: 2019/11/18 15:01
     */
    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,@RequestBody vo:UserVO):AccountUserResult=service.update(id,vo)

    /**
     * 删除用户信息
     * @Author: ChenRang
     * @Date: 2019/11/18 15:04
     */
    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean=service.destroy(id)

}