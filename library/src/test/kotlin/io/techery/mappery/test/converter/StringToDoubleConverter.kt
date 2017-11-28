package io.techery.mappery.test.converter

import io.techery.mappery.Converter
import io.techery.mappery.MapperyContext

class StringToDoubleConverter : Converter<String, Double> {
    override fun convert(context: MapperyContext, source: String): Double = source.toDouble()
}
