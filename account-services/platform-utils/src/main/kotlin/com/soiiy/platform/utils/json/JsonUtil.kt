package com.soiiy.platform.utils.json

import com.fasterxml.jackson.databind.ObjectMapper
import java.math.BigDecimal
import kotlin.reflect.KProperty1
import kotlin.reflect.full.memberProperties
import kotlin.reflect.jvm.isAccessible
import kotlin.reflect.jvm.javaField

/**
 *json工具类
 *@Author ChenRang
 *@Date  2019/11/16 20:44
 */
object JsonUtil {

    private val factor = JsonUtilFactor()

    private val objectMapper = ObjectMapper()

    fun toJSONString(any: Any?): String? {
        return if (any === null) null else objectMapper.writeValueAsString(any)
    }

    fun <T> parseObject(json: String, clazz: Class<T>): T {
        return objectMapper.readValue(json, clazz)
    }

    fun <T : Any> copy(from: Any, to: T, params: JsonUtilParams? = null): T {
        return factor.copy(from, to, params)
    }

    fun <T : Any> copy(from: Any, to: T, paramsLambda: (JsonUtilParams) -> JsonUtilParams): T {
        return copy(from, to, paramsLambda(JsonUtilParams()))
    }

    fun <T : Any> convert(from: Any, to: Class<T>, params: JsonUtilParams? = null): T {
        return copy(from, to.newInstance(), params)
    }

    fun <T : Any> convert(from: Any, to: Class<T>, paramsLambda: (JsonUtilParams) -> JsonUtilParams): T {
        return convert(from, to, paramsLambda(JsonUtilParams()))
    }

    fun map(from: Any): Map<String, Any?> = factor.convertFromObject(from)

}

class JsonUtilParams {

    var filters: MutableList<String> = mutableListOf()

    var excepts: MutableList<String> = mutableListOf()

    var alias: MutableMap<String, String> = mutableMapOf()

    var attrs: MutableMap<String, Any> = mutableMapOf()

    private fun filter(filter: String): JsonUtilParams {
        if (!this.filters.contains(filter)) {
            this.filters.add(filter)
        }
        return this
    }

    fun filter(vararg filters: String): JsonUtilParams {
        filters.forEach { filter(it) }
        return this
    }

    private fun except(except: String): JsonUtilParams {
        if (!this.excepts.contains(except)) {
            this.excepts.add(except)
        }
        return this
    }

    fun except(vararg excepts: String): JsonUtilParams {
        excepts.forEach { except(it) }
        return this
    }

    fun alias(originalKey: String, targetKey: String): JsonUtilParams {
        this.alias[originalKey] = targetKey
        return this
    }

    fun alias(alias: Map<String, String>): JsonUtilParams {
        alias.forEach { o, t -> this.alias(o, t) }
        return this
    }

    fun set(key: String, value: Any): JsonUtilParams {
        this.attrs[key] = value
        return this
    }

    fun set(attrs: Map<String, Any>): JsonUtilParams {
        attrs.forEach { k, v -> set(k, v) }
        return this
    }

}

private class JsonUtilFactor {

    private fun convertFromMap(map: Map<String, Any?>, params: JsonUtilParams): Map<String, Any> {
        var mutableMap = mutableMapOf<String, Any>()
        map.forEach { k, v ->
            if (v == null) return@forEach
            mutableMap.set(k.toString(), v)
        }

        if (params.alias.isNotEmpty()) {
            params.alias.forEach {
                if (map.containsKey(it.key)) {
                    mutableMap[it.value] = map[it.key]!!
                }
            }
        }
        if (params.attrs.isNotEmpty()) {
            params.attrs.forEach { mutableMap[it.key] = it.value }
        }
        if (params.filters.isNotEmpty()) {
            mutableMap = mutableMap.filter { params.filters.indexOf(it.key) >= 0 }.toMutableMap()
        }
        if (params.excepts.isNotEmpty()) {
            mutableMap = mutableMap.filter { return@filter params.excepts.indexOf(it.key) < 0 }.toMutableMap()
        }
        return mutableMap
    }

    fun convertFromObject(from: Any): Map<String, Any?> {
        if (Map::class.java.isAssignableFrom(from.javaClass)) {
            return (from as Map<*, *>).map { Pair(it.key.toString(), it.value) }.toMap()
        }
        val fromValueMap: MutableMap<String, Any> = mutableMapOf()
        from::class.memberProperties.forEach { it ->
            it.isAccessible = true
            val field = it.javaField?.get(from)
            if (field !== null) fromValueMap[it.name] = field
        }
        return fromValueMap
    }

    private fun <T : Any> copyValueField(it: KProperty1<out T, Any?>, fromValue: Any?, to: T) {
        try {
            val field = it.javaField!!
            if (fromValue is Number) {
                when (field.type.name.split(".").last().toLowerCase()) {
                    "number" -> field.set(to, fromValue)
                    "int" -> field.set(to, fromValue.toInt())
                    "long" -> field.set(to, fromValue.toLong())
                    "integer" -> field.set(to, fromValue.toInt())
                    "float" -> field.set(to, fromValue.toFloat())
                    "double" -> field.set(to, fromValue.toDouble())
                    "string" -> field.set(to, fromValue.toString())
                    "bigdecimal" -> field.set(to, BigDecimal(fromValue.toString()))
                    else -> println("JsonUtil: UnSupported Type: ${field.type.name}")
                }
            } else {
                field.set(to, fromValue)
            }
        } catch (e: Exception) {
            println("JsonUtil: Data Copy Value Failed!")
        }
    }

    private fun <T : Any> copyValueTo(from: Map<String, Any?>, to: T): T {
        to::class.memberProperties.forEach { it ->
            try {
                val fromValue = from[it.name]
                if (fromValue === null) return@forEach
                it.isAccessible = true
                if (it.javaField === null) return@forEach
                copyValueField(it, fromValue, to)
            } catch (e: Exception) {
                println("JsonUtil: Data Copy Value Failed!")
            }
        }
        return to
    }

    fun <T : Any> copy(from: Any, to: T, params: JsonUtilParams? = null): T {
        var fromValue = convertFromObject(from)
        if (params === null) return copyValueTo(fromValue, to)
        fromValue = convertFromMap(fromValue, params)
        return copyValueTo(fromValue, to)
    }

}
