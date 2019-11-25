package com.soiiy.service.web.account.controller

import com.soiiy.platform.utils.result.ResponsePageResult
import com.soiiy.service.share.account.constant.AccountLimitStatus
import com.soiiy.service.share.account.result.AccountMarketResult
import com.soiiy.service.web.account.service.MarketService
import org.apache.commons.logging.LogFactory
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

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

}