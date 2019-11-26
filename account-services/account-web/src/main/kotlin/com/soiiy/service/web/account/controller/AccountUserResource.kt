package com.soiiy.service.web.account.controller

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.constant.AccountUserType
import com.soiiy.service.share.account.result.AccountUserResult
import com.soiiy.service.web.account.service.UserService
import com.soiiy.service.web.account.vo.UserVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * 账户用户web类
 * @Author ChenRang
 * @Date  2019/11/26 9:52
 */
@Controller
@RequestMapping("/userResource")
class AccountUserResource {

    @Autowired
    lateinit var service: UserService
    private val prefix="/user"

    @GetMapping("/query")
    fun query(type: Int,model:Model):String{
        model.addAttribute("userType",service.query(type))
        model.addAttribute("type",type)
        return prefix+"/user"
    }

    @GetMapping("/index")
    @ResponseBody
    fun index(@RequestParam(required = false) keywords:String?,
              @RequestParam(required = false) limitStatus: AccountLimitStatus?,
              @RequestParam(required = false) roleId:Int?,
              @RequestParam(required = false) type: AccountUserType,
              @RequestParam(required = false,defaultValue = "1") page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long
    ): ResponsePageResult<AccountUserResult> = service.index(keywords,limitStatus,type,roleId,page,size)

    /**
     * 通过id查询用户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 9:57
     */
    @RequestMapping("/show")
    @ResponseBody
    fun show(id:String):AccountUserResult = service.show(id.toLong())

    /**
     * 添加用户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 9:57
     */
    @RequestMapping("/store")
    @ResponseBody
    fun store(dto:UserVO):AccountUserResult = service.store(dto)

    /**
     * 修改用户信息
     * @Author: ChenRang
     * @Date: 2019/11/26 16:18
     */
    @RequestMapping("/update")
    @ResponseBody
    fun update(id:String,dto:UserVO):AccountUserResult = service.update(id.toLong(),dto)

    /**
     * 通过id删除用户
     * @Author: ChenRang
     * @Date: 2019/11/26 9:56
     */
    @RequestMapping("/delete")
    @ResponseBody
    fun destroy(id:String):Boolean = service.destroy(id.toLong())
}