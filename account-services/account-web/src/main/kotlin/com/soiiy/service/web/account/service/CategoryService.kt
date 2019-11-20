package com.soiiy.service.web.account.service

import com.soiiy.service.share.account.query.StoreCategoryQuery
import com.soiiy.service.share.account.result.StoreCategoryResult
import com.soiiy.service.web.account.entity.CategoryEntity
import com.soiiy.service.web.account.mapper.CategoryMapper
import com.soiiy.service.web.account.vo.CategoryVO
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 *店铺分类service
 *@Author ChenRang
 *@Date  2019/11/20 10:09
 */
@Service
class CategoryService {

    @Autowired
    lateinit var mapper:CategoryMapper

    /**
     * 查询时跳转
     * @Author: ChenRang
     * @Date: 2019/11/20 10:11
     */
    fun query():StoreCategoryQuery=query()

    /**
     * 默认展示店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:13
     */
    fun index():List<StoreCategoryResult>{
        var selectList = mapper.selectList(null)
        return selectList.map { result(it) }
    }
    /**
     * 根据id查询某一个店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:22
     */
    fun show(id:Long):StoreCategoryResult{
        var entity = mapper.selectById(id)
        return result(entity)
    }
    /**
     * 添加店铺分类时封装表单信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:24
     */
    fun create():StoreCategoryQuery=query()

    /**
     * 添加店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:26
     */
    fun store(vo:CategoryVO):StoreCategoryResult{
        var entity = vo.entity()
        mapper.insert(entity)
        return result(entity)
    }

    /**
     * 编辑店铺分类时默认填充数据
     * @Author: ChenRang
     * @Date: 2019/11/20 10:28
     */
    fun edit(id: Long):StoreCategoryQuery{
        var query=query()
        query.formData=show(id)
        return query
    }
    /**
     * 修改店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:30
     */
    fun update(id:Long,vo:CategoryVO):StoreCategoryResult{
        var oldEntity=mapper.selectById(id)
        var entity = vo.entity(oldEntity)
        mapper.updateById(entity)
        return result(entity)
    }
    /**
     * 删除店铺分类信息
     * @Author: ChenRang
     * @Date: 2019/11/20 10:32
     */
    fun destroy(id:Long):Boolean{
        mapper.deleteById(id)
        return true
    }
    /**
     * 封装结果集
     * @Author: ChenRang
     * @Date: 2019/11/20 10:19
     */
    private fun result(entity:CategoryEntity):StoreCategoryResult{
        return entity.result()
    }
}