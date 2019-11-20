package com.soiiy.platform.utils.share

import com.soiiy.platform.utils.json.JsonUtil
import com.soiiy.platform.utils.json.JsonUtilParams
import java.lang.reflect.Type
import kotlin.reflect.full.isSubtypeOf
import kotlin.reflect.full.starProjectedType
import kotlin.reflect.jvm.javaType

/**
 *
 *@Author ChenRang
 *@Date  2019/11/16 20:20
 */
@Suppress("UNCHECKED_CAST")
interface ShareResponse<E: Any> {

    private fun clazzType(): Type {
        return this::class.supertypes.first {
            it.isSubtypeOf(ShareResponse::class.starProjectedType)
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

    fun beforeResultConvert(params: JsonUtilParams) {}

    fun afterResultConvert(e: E) {}

    fun result(): E {
        val params = JsonUtilParams()
        beforeResultConvert(params)
        val result = JsonUtil.copy(this, newInstance(), params)
        afterResultConvert(result)
        return result
    }

    fun result(params: JsonUtilParams? = null): E {
        return JsonUtil.copy(this, newInstance(), params)
    }

    fun result(params: (JsonUtilParams) -> JsonUtilParams): E {
        return result(params(JsonUtilParams()))
    }
}