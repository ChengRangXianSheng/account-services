package com.soiiy.service.web.account.controller

import com.soiiy.service.web.account.service.CategoryService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.servlet.ModelAndView

/**
 *
 * @Author ChenRang
 * @Date  2019/11/22 15:50
 */
@Controller
@RequestMapping("/categoryResource")
class CategoryResource {

    private val log=LoggerFactory.getLogger(CategoryResource::class.java)

    private val prefix="/category"

    @Autowired
    lateinit var service: CategoryService

    /**
     * 点击时填充数据
     * @Author: ChenRang
     * @Date: 2019/11/22 16:12
     */
    @GetMapping("/index")
    fun index(model:Model):String {
        model.addAttribute("category",service.index())
        return prefix+"/category"
    }

}