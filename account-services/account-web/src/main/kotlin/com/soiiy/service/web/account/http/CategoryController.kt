package com.soiiy.service.web.account.http

import com.soiiy.service.share.account.query.StoreCategoryQuery
import com.soiiy.service.share.account.result.StoreCategoryResult
import com.soiiy.service.web.account.service.CategoryService
import com.soiiy.service.web.account.vo.CategoryVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

/**
 *店铺分类控制类
 *@Author ChenRang
 *@Date  2019/11/20 10:06
 */
@RestController
@RequestMapping("/store-categories")
class CategoryController {

    @Autowired
    lateinit var servcie:CategoryService

    /**
     * 封装查询路径
     * @Author: ChenRang
     * @Date: 2019/11/20 10:34
     */
    @GetMapping("/query")
    fun query():StoreCategoryQuery=servcie.query()

    /**
     * 默认展示所有的分类
     * @Author: ChenRang
     * @Date: 2019/11/20 10:35
     */
    @GetMapping("/index")
    fun index():List<StoreCategoryResult> = servcie.index()

    /**
     * 根据id查询分类
     * @Author: ChenRang
     * @Date: 2019/11/20 10:36
     */
    @GetMapping("/{id}")
    fun show(@PathVariable("id")id:Long):StoreCategoryResult = servcie.show(id)

    /**
     * 创建时填充表单信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:37
     */
    @GetMapping("/create")
    fun create():StoreCategoryQuery = servcie.create()

    /**
     * 添加表单信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:38
     */
    @PostMapping
    fun store(@RequestBody vo:CategoryVO):StoreCategoryResult = servcie.store(vo)

    /**
     * 编辑表单信息时填充表单
     * @Author: ChenRang
     * @Date: 2019/11/20 10:39
     */
    @GetMapping("/{id}/edit")
    fun edit(@PathVariable("id")id:Long):StoreCategoryQuery=servcie.edit(id)

    /**
     * 修改表单信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:40
     */
    @PutMapping("/{id}")
    fun update(@PathVariable("id")id:Long,@RequestBody vo:CategoryVO):StoreCategoryResult=servcie.update(id,vo)

    /**
     * 删除店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:42
     */
    @DeleteMapping("/{id}")
    fun destroy(@PathVariable("id")id:Long):Boolean = servcie.destroy(id)
}