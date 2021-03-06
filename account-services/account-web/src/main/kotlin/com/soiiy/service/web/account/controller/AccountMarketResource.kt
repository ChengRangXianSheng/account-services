package com.soiiy.service.web.account.controller

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.result.AccountMarketResult
import com.soiiy.service.web.account.service.MarketService
import com.soiiy.service.web.account.vo.MarketVO
import org.apache.commons.logging.LogFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*

/**
 * 账户网点后台接口
 * @Author ChenRang
 * @Date  2019/11/22 18:55
 */
@Controller
@RequestMapping("/marketResource")
class AccountMarketResource {

    private val log= LoggerFactory.getLogger(AccountMarketResource::class.java)
    private val prefix="/market"

    @Autowired
    lateinit var service:MarketService

    /**
     * 跳转到查询页面
     * @Author: ChenRang
     * @Date: 2019/11/23 9:41
     */
    @GetMapping("/query")
    fun query():String=prefix+"/market"

    /**
     * 点击左侧菜单栏显示网点信息
     * @Author: ChenRang
     * @Date: 2019/11/22 18:58
     */
    @GetMapping("/index")
    @ResponseBody
    fun index(@RequestParam(required = false)keywords:String?,
              @RequestParam(required = false)limitStatus: AccountLimitStatus?,
              @RequestParam(required = false,defaultValue = "1")page:Long,
              @RequestParam(required = false,defaultValue = "10")size:Long
    ):ResponsePageResult<AccountMarketResult> = service.index(keywords,limitStatus,page,size)

    /**
     * 删除网点信息
     * @Author: ChenRang
     * @Date: 2019/11/25 17:16
     */
    @RequestMapping("/delete")
    @ResponseBody
    fun delete(id:String):Boolean=service.destroy(id.toLong())

    /**
     * 修改网点信息
     * @Author: ChenRang
     * @Date: 2019/11/26 10:39
     */
    @RequestMapping("/update")
    @ResponseBody
    fun update(id:String,dto:MarketVO):AccountMarketResult = service.update(id.toLong(),dto)
    /**
     * 根据id查询信息
     * @Author: ChenRang
     * @Date: 2019/11/25 17:30
     */
    @RequestMapping("/show")
    @ResponseBody
    fun show(id:String):AccountMarketResult=service.show(id.toLong())

    /**
     * 添加网点信息
     * @Author: ChenRang
     * @Date: 2019/11/25 19:02
     */
    @RequestMapping("/store")
    @ResponseBody
    fun store(dto:MarketVO):AccountMarketResult=service.store(dto)

}