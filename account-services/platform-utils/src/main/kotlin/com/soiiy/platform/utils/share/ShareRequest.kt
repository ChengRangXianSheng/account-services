package com.soiiy.platform.utils.share

import com.soiiy.platform.utils.json.JsonUtil
import com.soiiy.platform.utils.json.JsonUtilParams
import java.lang.reflect.Type
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.memberProperties
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField
import kotlin.reflect.jvm.javaType

/**
 *请求接口
 *@Author ChenRang
 *@Date  2019/11/16 20:49
 */
@Suppress("UNCHECKED_CAST")
interface ShareRequest<E: Any> {

    private fun clazzType(): Type {
        return this::class.supertypes.first {
            it.isSubtypeOf(ShareRequest::class.starProjectedType)
        }.arguments.first().type!!.javaType
    }

    private fun clazzTypeName(): String {
        return clazzType().typeName
    }

    private fun clazz(): Class<E> {
        return Class.forName(clazzTypeName()) as Class<E>
    }

    private fun newInstance(): E {
        return clazz().newInstance() as E
    }

    fun beforeEntityConvert(params: JsonUtilParams) {}

    fun beforeCreateEntityConvert(params: JsonUtilParams) {}

    fun beforeUpdateEntityConvert(params: JsonUtilParams) {}

    fun afterEntityConvert(e: E) {}

    fun afterCreateEntityConvert(e: E) {}

    fun afterUpdateEntityConvert(e: E) {}

    fun entity(): E {
        val params = JsonUtilParams()
        beforeEntityConvert(params)
        beforeCreateEntityConvert(params)
        val result = JsonUtil.copy(this, newInstance(), params)
        afterEntityConvert(result)
        afterCreateEntityConvert(result)
        return result
    }

    fun entity(original: E): E {
        val params = JsonUtilParams()
        beforeEntityConvert(params)
        beforeUpdateEntityConvert(params)
        val result = JsonUtil.copy(this, original, params)
        afterEntityConvert(result)
        afterUpdateEntityConvert(result)
        return result
    }

    fun map(filterNull: Boolean = true): Map<String, Any?> {
        val fromValueMap = mutableMapOf<String, Any?>()
        this::class.memberProperties.forEach { it ->
            it.isAccessible = true
            val field = it.javaField?.get(this)
            if (field !== null) {
                fromValueMap[it.name] = field
            } else if (!filterNull) {
                fromValueMap[it.name] = null
            }
        }
        return fromValueMap
    }

}