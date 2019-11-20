package com.soiiy.platform.utils.json

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.SerializerProvider
import java.math.BigDecimal

/**
 *
 *@Author ChenRang
 *@Date  2019/11/16 20:44
 */
object DecimalJsonType {

    class CoinSerializer : DecimalJsonSerializer()

    class CoinDeserializer : DecimalJsonDeserializer()

    class RateSerializer : DecimalJsonSerializer() {
        override val radix = BigDecimal(10000)
    }

    class RateDeserializer : DecimalJsonDeserializer() {
        override val radix = BigDecimal(10000)
    }

}

abstract class DecimalJsonDeserializer : JsonDeserializer<Number>() {

    protected open val radix = BigDecimal(100)

    override fun deserialize(json: JsonParser, context: DeserializationContext): Number? {
        return try {
            BigDecimal(json.valueAsString).multiply(radix)
        } catch (e: Throwable) {
            return null
        }
    }

}

abstract class DecimalJsonSerializer : JsonSerializer<Number>() {

    protected open val radix = BigDecimal(100)

    override fun serialize(num: Number?, generator: JsonGenerator, provider: SerializerProvider) {
        if (num === null) return
        generator.writeNumber(BigDecimal(num.toString()).divide(radix))
    }

}
